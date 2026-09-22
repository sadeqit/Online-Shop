package io.github.sadeghi.online_shop.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.sadeghi.online_shop.data.model.SubCategory
import io.github.sadeghi.online_shop.data.remote.model.SubCategoryDto
import javax.inject.Inject

class SubCategoryRepository @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ISubCategoryRepository {

    override suspend fun getSubCategories(): List<SubCategory> {

        val subCategories = supabaseClient
            .from("sub_categories")
            .select()
            .decodeList<SubCategoryDto>()

        return subCategories.map { dto ->
            SubCategory(
                id = dto.id,
                categoryId = dto.categoryId,
                title = dto.title,
                imageUrl = dto.imageUrl
            )
        }
    }
}