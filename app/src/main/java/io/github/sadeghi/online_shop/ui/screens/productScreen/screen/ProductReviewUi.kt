package io.github.sadeghi.online_shop.ui.screens.productScreen.screen

data class ProductReviewUi(
    val id: Long,
    val productId: Int,
    val userName: String,
    val rating: Int,
    val comment: String,
    val createdAt: Long
)
