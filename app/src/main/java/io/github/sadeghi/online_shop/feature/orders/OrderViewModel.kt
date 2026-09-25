package io.github.sadeghi.online_shop.feature.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.domain.model.Order
import io.github.sadeghi.online_shop.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository
) : ViewModel() {

    private val _orders =
        MutableStateFlow<List<Order>>(emptyList())

    val orders: StateFlow<List<Order>> =
        _orders.asStateFlow()

    private val _isLoading =
        MutableStateFlow(false)

    val isLoading: StateFlow<Boolean> =
        _isLoading.asStateFlow()

    private val _isSubmitting =
        MutableStateFlow(false)

    val isSubmitting: StateFlow<Boolean> =
        _isSubmitting.asStateFlow()

    init {
        loadOrders()
    }

    fun loadOrders() {

        viewModelScope.launch {

            _isLoading.value = true

            try {

                _orders.value =
                    repository.getOrders()

            } catch (e: Exception) {

                e.printStackTrace()

            } finally {

                _isLoading.value = false
            }
        }
    }

    fun addOrder(
        order: Order,
        onSuccess: (Long) -> Unit,
        onError: (Exception) -> Unit = {}
    ) {

        if (_isSubmitting.value) {
            return
        }

        _isSubmitting.value = true

        viewModelScope.launch {

            try {

                val orderId =
                    repository.createOrder(order)

                onSuccess(orderId)

            } catch (e: Exception) {

                e.printStackTrace()

                onError(e)

            } finally {

                _isSubmitting.value = false
            }
        }
    }
}