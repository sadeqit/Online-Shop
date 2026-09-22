package io.github.sadeghi.online_shop.data.repository

import io.github.sadeghi.online_shop.ui.screens.productScreen.product.Product


interface IProductRepository {

    suspend fun getProducts(): List<Product>
}