package io.github.sadeghi.online_shop.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order
import io.github.sadeghi.online_shop.data.remote.dto.CartItemDto
import io.github.sadeghi.online_shop.data.remote.dto.CartItemUpsertDto
import io.github.sadeghi.online_shop.domain.repository.IProductRepository
import io.github.sadeghi.online_shop.domain.model.CartItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val supabaseClient: SupabaseClient,
    private val productRepository: IProductRepository
)
{

    val cartItems: Flow<List<CartItems>> = flow {

        val userId =
            supabaseClient.auth.currentUserOrNull()?.id
                ?: run {
                    emit(emptyList())
                    return@flow
                }

        val cartItems = supabaseClient
            .from("cart_items")
            .select {
                filter {
                    eq("user_id", userId)
                }

                order(
                    column = "created_at",
                    order = Order.ASCENDING
                )
            }
            .decodeList<CartItemDto>()

        val products = productRepository.getProducts()

        val productMap =
            products.associateBy { it.id }

        val result = cartItems.mapNotNull { cartItem ->

            val product =
                productMap[cartItem.productId]
                    ?: return@mapNotNull null

            CartItems(
                product = product,
                quantity = cartItem.quantity
            )
        }

        emit(result)
    }

    suspend fun saveCartItems(
        cartItems: List<CartItems>
    ) {
        val userId =
            supabaseClient.auth.currentUserOrNull()?.id
                ?: return

        val currentCart = supabaseClient
            .from("cart_items")
            .select {
                filter {
                    eq("user_id", userId)
                }
            }
            .decodeList<CartItemDto>()

        val currentProductIds =
            currentCart
                .map { it.productId }
                .toSet()

        val newProductIds =
            cartItems
                .map { it.product.id }
                .toSet()

        val removedProductIds =
            currentProductIds - newProductIds

        if (removedProductIds.isNotEmpty()) {
            supabaseClient
                .from("cart_items")
                .delete {
                    filter {
                        eq("user_id", userId)
                        isIn(
                            "product_id",
                            removedProductIds.toList()
                        )
                    }
                }
        }

        cartItems.forEach { item ->

            val exists =
                currentCart.any {
                    it.productId == item.product.id
                }

            if (exists) {

                supabaseClient
                    .from("cart_items")
                    .update(
                        CartItemUpsertDto(
                            userId = userId,
                            productId = item.product.id,
                            quantity = item.quantity
                        )
                    ) {
                        filter {
                            eq("user_id", userId)
                            eq("product_id", item.product.id)
                        }
                    }

            } else {

                supabaseClient
                    .from("cart_items")
                    .insert(
                        CartItemUpsertDto(
                            userId = userId,
                            productId = item.product.id,
                            quantity = item.quantity
                        )
                    )
            }
        }
    }

    suspend fun clearCart() {

        val userId =
            supabaseClient.auth.currentUserOrNull()?.id
                ?: return

        supabaseClient
            .from("cart_items")
            .delete {
                filter {
                    eq("user_id", userId)
                }
            }
    }
}