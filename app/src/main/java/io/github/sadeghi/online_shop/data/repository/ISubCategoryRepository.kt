package io.github.sadeghi.online_shop.data.repository

import io.github.sadeghi.online_shop.data.model.SubCategory

interface ISubCategoryRepository {

    suspend fun getSubCategories(): List<SubCategory>
}