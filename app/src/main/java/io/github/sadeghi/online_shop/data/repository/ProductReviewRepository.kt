package io.github.sadeghi.online_shop.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.sadeghi.online_shop.data.remote.model.CreateProductReviewDto
import io.github.sadeghi.online_shop.data.remote.model.ProductReviewDto
import io.github.sadeghi.online_shop.data.remote.model.UpdateProductReviewDto
import io.github.sadeghi.online_shop.ui.screens.productScreen.screen.ProductReview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject


class ProductReviewRepository @Inject constructor(
    private val supabaseClient: SupabaseClient
) : IProductReviewRepository {

    private fun parseCreatedAt(value: String): Long {
        return try {
            java.time.OffsetDateTime
                .parse(
                    value,
                    java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME
                )
                .toInstant()
                .toEpochMilli()
        } catch (e: Exception) {
            0L
        }
    }

    override fun getReviews(
        productId: Int
    ): Flow<List<ProductReview>> = flow {

        val reviews =
            supabaseClient
                .from("product_reviews")
                .select {
                    filter {
                        eq("product_id", productId)
                    }
                }
                .decodeList<ProductReviewDto>()

        emit(
            reviews
                .map { dto ->
                    ProductReview(
                        id = dto.id,
                        productId = dto.productId,
                        userId = dto.userId,
                        rating = dto.rating,
                        comment = dto.comment,
                        createdAt = parseCreatedAt(dto.createdAt),
                        adminReply = dto.adminReply,
                        adminReplyAt = dto.adminReplyAt?.let { parseCreatedAt(it) }
                    )
                }
                .sortedByDescending { it.createdAt }
        )
    }

    override suspend fun addReview(
        review: ProductReview
    ) {

        val dto = CreateProductReviewDto(
            productId = review.productId,
            userId = review.userId,
            rating = review.rating,
            comment = review.comment
        )

        supabaseClient
            .from("product_reviews")
            .insert(dto)
    }

    override suspend fun getUserReviews(
        productId: Int,
        userId: String
    ): List<ProductReview> {

        val reviews =
            supabaseClient
                .from("product_reviews")
                .select {
                    filter {
                        eq("product_id", productId)
                        eq("user_id", userId)
                    }
                }
                .decodeList<ProductReviewDto>()

        return reviews.map { dto ->
            ProductReview(
                id = dto.id,
                productId = dto.productId,
                userId = dto.userId,
                rating = dto.rating,
                comment = dto.comment,
                createdAt = parseCreatedAt(dto.createdAt),
                adminReply = dto.adminReply,
                adminReplyAt = dto.adminReplyAt?.let { parseCreatedAt(it) }
            )
        }
    }
    override suspend fun updateReview(
        reviewId: Long,
        rating: Int,
        comment: String
    ) {
        supabaseClient.postgrest.rpc(
            function = "update_product_review",
            parameters = buildJsonObject {
                put("p_review_id", reviewId)
                put("p_rating", rating)
                put("p_comment", comment.trim())
            }
        )
    }

    /*override suspend fun updateReview(
        reviewId: Long,
        rating: Int,
        comment: String
    ) {
        supabaseClient
            .from("product_reviews")
            .update(
                UpdateProductReviewDto(
                    rating = rating,
                    comment = comment.trim()
                )
            ) {
                filter {
                    eq("id", reviewId)
                }
            }
    }*/

    override suspend fun deleteReview(
        reviewId: Long
    ) {
        supabaseClient
            .from("product_reviews")
            .delete {
                filter {
                    eq("id", reviewId)
                }
            }
    }

    override suspend fun replyToReview(
        reviewId: Long,
        reply: String
    ) {
        supabaseClient.postgrest.rpc(
            function = "reply_to_product_review",
            parameters = buildJsonObject {
                put("p_review_id", reviewId)
                put("p_reply", reply.trim())
            }
        )
    }
}
