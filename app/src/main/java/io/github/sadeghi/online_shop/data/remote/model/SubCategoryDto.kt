package io.github.sadeghi.online_shop.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubCategoryDto(
    val id: Int,

    @SerialName("category_id")
    val categoryId: Int,

    val title: String,

    @SerialName("image_url")
    val imageUrl: String?
)