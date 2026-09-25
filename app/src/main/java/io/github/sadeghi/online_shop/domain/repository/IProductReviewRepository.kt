package io.github.sadeghi.online_shop.domain.repository

import io.github.sadeghi.online_shop.domain.model.ProductReview
import kotlinx.coroutines.flow.Flow

interface IProductReviewRepository {

    fun getReviews(productId: Int): Flow<List<ProductReview>>

    suspend fun addReview(review: ProductReview)

    suspend fun getUserReviews(
        productId: Int,
        userId: String
    ): List<ProductReview>

    suspend fun updateReview(
        reviewId: Long,
        rating: Int,
        comment: String
    )

    suspend fun deleteReview(
        reviewId: Long
    )

    suspend fun replyToReview(
        reviewId: Long,
        reply: String
    )
}