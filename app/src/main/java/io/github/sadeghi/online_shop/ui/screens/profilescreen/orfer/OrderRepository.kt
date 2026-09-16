package io.github.sadeghi.online_shop.ui.screens.profilescreen.orfer

import io.github.sadeghi.online_shop.data.local.datastore.OrderDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val orderDataStore: OrderDataStore
) {

    val orders: Flow<List<Order>> =
        orderDataStore.orders.map { json ->
            orderDataStore.fromJson(json)
        }

    suspend fun saveOrders(
        orders: List<Order>
    ) {
        val json = orderDataStore.toJson(orders)

        orderDataStore.saveOrders(json)
    }

    suspend fun clearOrders() {
        orderDataStore.clearOrders()
    }
}