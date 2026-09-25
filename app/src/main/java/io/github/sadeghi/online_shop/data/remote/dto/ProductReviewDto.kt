package io.github.sadeghi.online_shop.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductReviewDto(
    val id: Long,

    @SerialName("product_id")
    val productId: Int,

    @SerialName("user_id")
    val userId: String,

    val rating: Int,

    val comment: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("admin_reply")
    val adminReply: String? = null,

    @SerialName("admin_reply_at")
    val adminReplyAt: String? = null
)

@Serializable
data class CreateProductReviewDto(

    @SerialName("product_id")
    val productId: Int,

    @SerialName("user_id")
    val userId: String,

    val rating: Int,

    val comment: String
)

@Serializable
data class UpdateProductReviewDto(
    val rating: Int,
    val comment: String
)