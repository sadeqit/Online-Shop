package io.github.sadeghi.online_shop.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.sadeghi.online_shop.ui.screens.profilescreen.orfer.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.orderDataStore: DataStore<Preferences>
        by preferencesDataStore(name = "order_preferences")

class OrderDataStore @Inject constructor(
    @param:ApplicationContext
    private val context: Context,
    private val gson: Gson
) {

    companion object {
        private val ORDERS =
            stringPreferencesKey("orders")
    }

    val orders: Flow<String> =
        context.orderDataStore.data.map { preferences ->
            preferences[ORDERS] ?: "[]"
        }

    suspend fun saveOrders(
        orders: String
    ) {
        context.orderDataStore.edit { preferences ->
            preferences[ORDERS] = orders
        }
    }

    suspend fun clearOrders() {
        context.orderDataStore.edit { preferences ->
            preferences.remove(ORDERS)
        }
    }

    fun toJson(
        orders: List<Order>
    ): String {
        return gson.toJson(orders)
    }

    fun fromJson(
        json: String
    ): List<Order> {

        if (json.isBlank()) {
            return emptyList()
        }

        val type = object :
            TypeToken<List<Order>>() {}.type

        return gson.fromJson(json, type) ?: emptyList()
    }
}