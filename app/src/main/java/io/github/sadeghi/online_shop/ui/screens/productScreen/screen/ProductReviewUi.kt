package io.github.sadeghi.online_shop.ui.screens.productScreen.screen

data class ProductReviewUi(
    val id: Long,
    val productId: Int,
    val userId: String,
    val userName: String,
    val rating: Int,
    val comment: String,
    val createdAt: Long,
    val adminReply: String? = null,
    val adminReplyAt: Long? = null
)