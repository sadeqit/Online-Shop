package io.github.sadeghi.online_shop.domain.repository

import io.github.sadeghi.online_shop.domain.model.SubCategory

interface ISubCategoryRepository {

    suspend fun getSubCategories(): List<SubCategory>
}