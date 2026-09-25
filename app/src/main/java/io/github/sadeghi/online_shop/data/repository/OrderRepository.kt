package io.github.sadeghi.online_shop.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.sadeghi.online_shop.data.remote.dto.CreateNotificationDto
import io.github.sadeghi.online_shop.data.remote.dto.CreateOrderDto
import io.github.sadeghi.online_shop.data.remote.dto.CreateOrderItemDto
import io.github.sadeghi.online_shop.data.remote.dto.OrderDto
import io.github.sadeghi.online_shop.data.remote.dto.OrderItemDto
import io.github.sadeghi.online_shop.data.remote.dto.ProductDto
import io.github.sadeghi.online_shop.domain.model.CartItems
import io.github.sadeghi.online_shop.domain.model.Product
import io.github.sadeghi.online_shop.domain.model.Order
import java.time.OffsetDateTime
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val supabaseClient: SupabaseClient
)
{

    suspend fun getOrders(): List<Order> {

        val userId =
            supabaseClient.auth.currentUserOrNull()?.id
                ?: return emptyList()

        val orders = supabaseClient
            .from("orders")
            .select {
                filter {
                    eq("user_id", userId)
                }
            }
            .decodeList<OrderDto>()

        val orderItems = supabaseClient
            .from("order_items")
            .select()
            .decodeList<OrderItemDto>()

        val products = supabaseClient
            .from("products")
            .select()
            .decodeList<ProductDto>()

        val productMap =
            products.associateBy { it.id }

        return orders.map { orderDto ->

            val items =
                orderItems
                    .filter {
                        it.orderId == orderDto.id
                    }
                    .mapNotNull { itemDto ->

                        val productDto =
                            productMap[itemDto.productId]
                                ?: return@mapNotNull null

                        val product =
                            Product(
                                id = productDto.id,
                                title = productDto.title,
                                imageUrl = productDto.imageUrl,
                                price = productDto.price,
                                oldPrice = productDto.oldPrice,
                                discountPercent = productDto.discountPercent,
                                description = productDto.description,
                                subCategoryId = productDto.subCategoryId
                            )

                        CartItems(
                            product = product,
                            quantity = itemDto.quantity
                        )
                    }

            Order(
                id = orderDto.id,
                date = runCatching {
                    OffsetDateTime
                        .parse(orderDto.createdAt)
                        .toInstant()
                        .toEpochMilli()
                }.getOrDefault(0L),
                items = items,
                totalPrice = orderDto.totalPrice,
                status = orderDto.status
            )
        }
    }

    suspend fun createOrder(
        order: Order
    ): Long {

        val userId =
            supabaseClient.auth.currentUserOrNull()?.id
                ?: throw IllegalStateException(
                    "کاربر وارد حساب نشده است"
                )

        val createdOrder =
            supabaseClient
                .from("orders")
                .insert(
                    CreateOrderDto(
                        userId = userId,
                        totalPrice = order.totalPrice,
                        status = order.status
                    )
                ) {
                    select()
                }
                .decodeSingle<OrderDto>()

        val orderItems =
            order.items.map { item ->

                CreateOrderItemDto(
                    orderId = createdOrder.id,
                    productId = item.product.id,
                    quantity = item.quantity,
                    price = item.product.price
                )
            }

        if (orderItems.isNotEmpty()) {

            supabaseClient
                .from("order_items")
                .insert(orderItems)
        }

        val productsText =
            order.items.joinToString(
                separator = "، "
            ) { item ->

                "${item.product.title} × ${item.quantity}"
            }

        val notificationMessage =
            "$productsText با موفقیت ثبت شد."

        supabaseClient
            .from("notifications")
            .insert(
                CreateNotificationDto(
                    userId = userId,
                    subject = "سفارش شما با موفقیت ثبت شد",
                    message = notificationMessage
                )
            )

        return createdOrder.id
    }
}
