package io.github.sadeghi.online_shop.data.repository

import io.github.sadeghi.online_shop.ui.screens.productScreen.screen.ProductReview
import kotlinx.coroutines.flow.Flow

interface IProductReviewRepository {

    fun getReviews(productId: Int): Flow<List<ProductReview>>

    suspend fun addReview(review: ProductReview)

    suspend fun getUserReviews(
        productId: Int,
        userId: String
    ): List<ProductReview>
}