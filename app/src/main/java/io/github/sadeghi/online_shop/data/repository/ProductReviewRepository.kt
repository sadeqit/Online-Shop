package io.github.sadeghi.online_shop.data.repository

import io.github.sadeghi.online_shop.data.local.datastore.ProductReviewDataStore
import io.github.sadeghi.online_shop.ui.screens.productScreen.screen.ProductReview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductReviewRepository @Inject constructor(
    private val dataStore: ProductReviewDataStore
) : IProductReviewRepository {

    override fun getReviews(
        productId: Int
    ): Flow<List<ProductReview>> {

        return dataStore.reviews.map { json ->

            dataStore.fromJson(json)
                .filter { review ->
                    review.productId == productId
                }
                .sortedByDescending { review ->
                    review.createdAt
                }
        }
    }

    override suspend fun addReview(
        review: ProductReview
    ) {

        val currentReviews =
            dataStore.fromJson(
                dataStore.reviews.first()
            )

        val updatedReviews =
            currentReviews + review

        dataStore.saveReviews(
            dataStore.toJson(updatedReviews)
        )
    }

    override suspend fun getUserReviews(
        productId: Int,
        userId: String
    ): List<ProductReview> {

        val currentReviews =
            dataStore.fromJson(
                dataStore.reviews.first()
            )

        return currentReviews.filter {
            it.productId == productId &&
                    it.userId == userId
        }
    }
}