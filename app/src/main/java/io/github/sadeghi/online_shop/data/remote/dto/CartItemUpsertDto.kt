package io.github.sadeghi.online_shop.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItemUpsertDto(
    @SerialName("user_id")
    val userId: String,

    @SerialName("product_id")
    val productId: Int,

    val quantity: Int
)