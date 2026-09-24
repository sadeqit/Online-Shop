package io.github.sadeghi.online_shop.ui.screens.profilescreen.orfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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

/*
@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository
) : ViewModel()
{

    private val _orders =
        MutableStateFlow<List<Order>>(emptyList())

    val orders: StateFlow<List<Order>> =
        _orders.asStateFlow()

    init {
        loadOrders()
    }

    private fun loadOrders() {
        viewModelScope.launch {
            repository.orders.collect { orders ->
                _orders.value = orders
            }
        }
    }

    fun addOrder(
        order: Order,
        onSuccess: () -> Unit
    ) {
        val currentOrders =
            _orders.value.toMutableList()

        currentOrders.add(0, order)

        _orders.value = currentOrders

        viewModelScope.launch {
            repository.saveOrders(currentOrders)
            onSuccess()
        }
    }

    fun clearOrders() {
        viewModelScope.launch {
            repository.clearOrders()
        }
    }
}*/
