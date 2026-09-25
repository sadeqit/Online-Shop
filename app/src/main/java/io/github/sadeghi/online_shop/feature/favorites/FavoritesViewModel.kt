package io.github.sadeghi.online_shop.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.data.repository.FavoritesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesRepository
) : ViewModel() {

    val favoriteProductIds: StateFlow<Set<Int>> =
        repository.favoriteProductIds.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    fun toggleFavorite(productId: Int) {
        viewModelScope.launch {
            repository.toggleFavorite(productId)
        }
    }

    fun addFavorite(productId: Int) {
        viewModelScope.launch {
            repository.addFavorite(productId)
        }
    }

    fun removeFavorite(productId: Int) {
        viewModelScope.launch {
            repository.removeFavorite(productId)
        }
    }
}