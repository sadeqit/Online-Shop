package io.github.sadeghi.online_shop.data.model

data class SubCategory(
    val id: Int,
    val categoryId: Int,
    val title: String,
    val imageUrl: String?
)