package io.github.sadeghi.online_shop.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.sadeghi.online_shop.data.remote.dto.ProductDto
import io.github.sadeghi.online_shop.domain.repository.IProductRepository
import io.github.sadeghi.online_shop.domain.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val supabaseClient: SupabaseClient
) : IProductRepository {

    override suspend fun getProducts(): List<Product> {

        val products = supabaseClient
            .from("products")
            .select()
            .decodeList<ProductDto>()

        return products.map { dto ->
            Product(
                id = dto.id,
                title = dto.title,
                imageUrl = dto.imageUrl,
                price = dto.price,
                oldPrice = dto.oldPrice,
                discountPercent = dto.discountPercent,
                description = dto.description,
                subCategoryId = dto.subCategoryId
            )
        }
    }
}