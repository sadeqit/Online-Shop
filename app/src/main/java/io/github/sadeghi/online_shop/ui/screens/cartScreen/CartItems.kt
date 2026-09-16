package io.github.sadeghi.online_shop.ui.screens.cartScreen

import io.github.sadeghi.online_shop.ui.screens.productScreen.product.Product


data class CartItems(
    val product: Product,
    val quantity: Int

)