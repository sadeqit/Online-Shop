package io.github.sadeghi.online_shop.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDto(
    val id: Long,
    @SerialName("user_id")
    val userId: String,
    @SerialName("total_price")
    val totalPrice: Long,
    val status: String,
    @SerialName("created_at")
    val createdAt: String
)