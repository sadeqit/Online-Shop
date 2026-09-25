package io.github.sadeghi.online_shop.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,

    @SerialName("sub_category_id")
    val subCategoryId: Int,

    val title: String,

    @SerialName("image_url")
    val imageUrl: String?,

    val price: Long,

    @SerialName("old_price")
    val oldPrice: Long?,

    @SerialName("discount_percent")
    val discountPercent: Int,

    val description: String
)