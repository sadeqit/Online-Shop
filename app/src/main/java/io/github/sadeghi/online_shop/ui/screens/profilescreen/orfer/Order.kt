package io.github.sadeghi.online_shop.ui.screens.profilescreen.orfer

import io.github.sadeghi.online_shop.ui.screens.cartScreen.CartItems

data class Order(
    val id: Long,
    val date: Long,
    val items: List<CartItems>,
    val totalPrice: Long,
    val status: String
)