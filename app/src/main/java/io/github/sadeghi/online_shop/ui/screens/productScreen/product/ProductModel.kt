package io.github.sadeghi.online_shop.ui.screens.productScreen.product

data class Product(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val price: Long,
    val oldPrice: Long?,
    val discountPercent: Int,
    val description: String,
    val subCategoryId: Int
)