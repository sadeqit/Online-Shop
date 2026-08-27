package io.github.sadeghi.online_shop.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _hasNotification = MutableStateFlow(true)
    val hasNotification: StateFlow<Boolean> = _hasNotification.asStateFlow()

    fun onNotificationClick(onNavigate: () -> Unit) {
        // وقتی روش زده شد، false میشه و به صفحه نوتیف میریم
        _hasNotification.value = false
        onNavigate()
    }

    // وقتی از صفحه نوتیف برمیگردیم، دوباره true میشه
    fun resetNotification() {
        _hasNotification.value = true
    }
}