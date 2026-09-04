package io.github.sadeghi.online_shop.ui.component.product

data class Product(
    val id: Int,
    val title: String,
    val image: Int,
    val price: String,
    val oldPrice: String,
    val description: String
)