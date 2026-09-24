package io.github.sadeghi.online_shop.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderItemDto(
    val id: Long,
    @SerialName("order_id")
    val orderId: Long,
    @SerialName("product_id")
    val productId: Int,
    val quantity: Int,
    val price: Long
)