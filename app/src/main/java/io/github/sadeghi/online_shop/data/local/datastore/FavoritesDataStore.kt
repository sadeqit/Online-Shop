package io.github.sadeghi.online_shop.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val FAVORITES_KEY =
            stringSetPreferencesKey("favorite_product_ids")
    }

    val favoriteProductIds: Flow<Set<Int>> =
        dataStore.data.map { preferences ->

            preferences[FAVORITES_KEY]
                ?.mapNotNull { it.toIntOrNull() }
                ?.toSet()
                ?: emptySet()
        }

    suspend fun addFavorite(productId: Int) {

        dataStore.edit { preferences ->

            val currentFavorites =
                preferences[FAVORITES_KEY]
                    ?: emptySet()

            preferences[FAVORITES_KEY] =
                currentFavorites + productId.toString()
        }
    }

    suspend fun removeFavorite(productId: Int) {

        dataStore.edit { preferences ->

            val currentFavorites =
                preferences[FAVORITES_KEY]
                    ?: emptySet()

            preferences[FAVORITES_KEY] =
                currentFavorites - productId.toString()
        }
    }

    suspend fun toggleFavorite(productId: Int) {

        dataStore.edit { preferences ->

            val currentFavorites =
                preferences[FAVORITES_KEY]
                    ?: emptySet()

            val id = productId.toString()

            preferences[FAVORITES_KEY] =
                if (id in currentFavorites) {
                    currentFavorites - id
                } else {
                    currentFavorites + id
                }
        }
    }
}