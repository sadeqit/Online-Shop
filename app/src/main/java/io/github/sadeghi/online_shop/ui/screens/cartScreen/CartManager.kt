package io.github.sadeghi.online_shop.ui.screens.cartScreen

import androidx.compose.runtime.mutableStateListOf
import io.github.sadeghi.online_shop.ui.screens.productScreen.product.Product

object CartManager {

    private val _cartItems = mutableStateListOf<CartItems>()

    val cartItems: List<CartItems>
        get() = _cartItems

    fun addToCart(
        product: Product,
        quantity: Int = 1
    ) {

        val index = _cartItems.indexOfFirst {
            it.product.id == product.id
        }

        if (index != -1) {

            val currentItem = _cartItems[index]

            _cartItems[index] = currentItem.copy(
                quantity = currentItem.quantity + quantity
            )

        } else {

            _cartItems.add(
                CartItems(
                    product = product,
                    quantity = quantity
                )
            )
        }
    }

    fun increaseQuantity(productId: Int) {

        val index = _cartItems.indexOfFirst {
            it.product.id == productId
        }

        if (index != -1) {

            val currentItem = _cartItems[index]

            _cartItems[index] = currentItem.copy(
                quantity = currentItem.quantity + 1
            )
        }
    }

    fun decreaseQuantity(productId: Int) {

        val index = _cartItems.indexOfFirst {
            it.product.id == productId
        }

        if (index != -1) {

            val currentItem = _cartItems[index]

            if (currentItem.quantity > 1) {

                _cartItems[index] = currentItem.copy(
                    quantity = currentItem.quantity - 1
                )

            } else {

                _cartItems.removeAt(index)
            }
        }
    }

    fun getQuantity(productId: Int): Int {

        return _cartItems
            .find { it.product.id == productId }
            ?.quantity
            ?: 0
    }
}