package io.github.sadeghi.online_shop.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderDto(
    @SerialName("user_id")
    val userId: String,
    @SerialName("total_price")
    val totalPrice: Long,
    val status: String
)