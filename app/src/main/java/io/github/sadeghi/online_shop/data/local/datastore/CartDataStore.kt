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
import io.github.sadeghi.online_shop.ui.screens.cartScreen.CartItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.cartDataStore: DataStore<Preferences>
        by preferencesDataStore(name = "cart_preferences")

class CartDataStore @Inject constructor(
    @param:ApplicationContext
    private val context: Context,
    private val gson: Gson
) {

    companion object {
        private val CART_ITEMS =
            stringPreferencesKey("cart_items")
    }

    val cartItems: Flow<String> =
        context.cartDataStore.data.map { preferences ->
            preferences[CART_ITEMS] ?: "[]"
        }

    suspend fun saveCartItems(
        cartItems: String
    ) {
        context.cartDataStore.edit { preferences ->
            preferences[CART_ITEMS] = cartItems
        }
    }

    suspend fun clearCart() {
        context.cartDataStore.edit { preferences ->
            preferences.remove(CART_ITEMS)
        }
    }

    fun toJson(
        cartItems: List<CartItems>
    ): String {
        return gson.toJson(cartItems)
    }

    fun fromJson(
        json: String
    ): List<CartItems> {

        if (json.isBlank()) {
            return emptyList()
        }

        val type = object :
            TypeToken<List<CartItems>>() {}.type

        return gson.fromJson(json, type) ?: emptyList()
    }
}