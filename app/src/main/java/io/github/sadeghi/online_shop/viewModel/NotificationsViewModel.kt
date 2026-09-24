package io.github.sadeghi.online_shop.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.ui.screens.profilescreen.notif.Notification
import io.github.sadeghi.online_shop.ui.screens.profilescreen.notif.NotificationsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val repository: NotificationsRepository
) : ViewModel() {

    var notifications by mutableStateOf<List<Notification>>(emptyList())
        private set

    init {
        loadNotifications()
    }

    private fun loadNotifications() {

        viewModelScope.launch {

            try {

                val result = repository.getNotifications()

                println("NOTIFICATIONS RESULT = $result")

                notifications = result

            } catch (e: Exception) {

                println("NOTIFICATIONS ERROR = ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun markAsRead(notificationId: Long) {
        viewModelScope.launch {
            try {
                repository.markAsRead(notificationId)

                notifications = repository.getNotifications()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    val hasNotification: Boolean
        get() = notifications.any { !it.isRead }
}