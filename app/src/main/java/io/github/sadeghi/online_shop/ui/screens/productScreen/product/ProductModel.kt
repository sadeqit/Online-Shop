package io.github.sadeghi.online_shop.ui.screens.productScreen.product

data class Product(
    val id: Int,
    val title: String,
    val image: Int,
    val price: String,
    val oldPrice: String,
    val description: String
)