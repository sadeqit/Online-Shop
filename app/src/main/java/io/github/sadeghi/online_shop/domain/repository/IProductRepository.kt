package io.github.sadeghi.online_shop.domain.repository

import io.github.sadeghi.online_shop.domain.model.Product


interface IProductRepository {

    suspend fun getProducts(): List<Product>
}