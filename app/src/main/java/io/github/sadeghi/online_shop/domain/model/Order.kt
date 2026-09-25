package io.github.sadeghi.online_shop.domain.model

data class Order(
    val id: Long,
    val date: Long,
    val items: List<CartItems>,
    val totalPrice: Long,
    val status: String
)