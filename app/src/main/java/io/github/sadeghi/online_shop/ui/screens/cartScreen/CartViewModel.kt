package io.github.sadeghi.online_shop.ui.screens.cartScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.data.repository.CartRepository
import io.github.sadeghi.online_shop.ui.screens.productScreen.product.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    private val _cartItems =
        MutableStateFlow<List<CartItems>>(emptyList())

    val cartItems: StateFlow<List<CartItems>> =
        _cartItems.asStateFlow()

    init {
        loadCart()
    }

    private fun loadCart() {
        viewModelScope.launch {
            repository.cartItems.collect { items ->
                _cartItems.value = items
            }
        }
    }

    fun addToCart(
        product: Product,
        quantity: Int = 1
    ) {
        val currentItems = _cartItems.value.toMutableList()

        val index = currentItems.indexOfFirst {
            it.product.id == product.id
        }

        if (index != -1) {

            val currentItem = currentItems[index]

            currentItems[index] = currentItem.copy(
                quantity = currentItem.quantity + quantity
            )

        } else {

            currentItems.add(
                CartItems(
                    product = product,
                    quantity = quantity
                )
            )
        }

        saveCart(currentItems)
    }

    fun increaseQuantity(productId: Int) {

        val currentItems = _cartItems.value.toMutableList()

        val index = currentItems.indexOfFirst {
            it.product.id == productId
        }

        if (index != -1) {

            val currentItem = currentItems[index]

            currentItems[index] = currentItem.copy(
                quantity = currentItem.quantity + 1
            )

            saveCart(currentItems)
        }
    }

    fun decreaseQuantity(productId: Int) {

        val currentItems = _cartItems.value.toMutableList()

        val index = currentItems.indexOfFirst {
            it.product.id == productId
        }

        if (index != -1) {

            val currentItem = currentItems[index]

            if (currentItem.quantity > 1) {

                currentItems[index] = currentItem.copy(
                    quantity = currentItem.quantity - 1
                )

            } else {

                currentItems.removeAt(index)
            }

            saveCart(currentItems)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }

    private fun saveCart(
        items: List<CartItems>
    ) {
        _cartItems.value = items

        viewModelScope.launch {
            repository.saveCartItems(items)
        }
    }
}