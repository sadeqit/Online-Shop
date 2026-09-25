package io.github.sadeghi.online_shop.data.repository

import io.github.sadeghi.online_shop.data.local.datastore.FavoritesDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val favoritesDataStore: FavoritesDataStore
)
{

    val favoriteProductIds: Flow<Set<Int>>
        get() = favoritesDataStore.favoriteProductIds

    suspend fun addFavorite(productId: Int) {
        favoritesDataStore.addFavorite(productId)
    }

    suspend fun removeFavorite(productId: Int) {
        favoritesDataStore.removeFavorite(productId)
    }

    suspend fun toggleFavorite(productId: Int) {
        favoritesDataStore.toggleFavorite(productId)
    }
}