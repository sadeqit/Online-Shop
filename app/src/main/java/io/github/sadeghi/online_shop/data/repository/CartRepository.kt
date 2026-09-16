package io.github.sadeghi.online_shop.data.repository

import io.github.sadeghi.online_shop.data.local.datastore.CartDataStore
import io.github.sadeghi.online_shop.ui.screens.cartScreen.CartItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartDataStore: CartDataStore
) {

    val cartItems: Flow<List<CartItems>> =
        cartDataStore.cartItems.map { json ->
            cartDataStore.fromJson(json)
        }

    suspend fun saveCartItems(
        cartItems: List<CartItems>
    ) {
        val json = cartDataStore.toJson(cartItems)
        cartDataStore.saveCartItems(json)
    }

    suspend fun clearCart() {
        cartDataStore.clearCart()
    }
}