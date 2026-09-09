package io.github.sadeghi.online_shop.ui.screens.profilescreen.notif

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.data.local.datastore.NotificationsDataStore
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val repository: NotificationsRepository,
    private val notificationsDataStore: NotificationsDataStore
) : ViewModel() {

    var notifications by mutableStateOf<List<Notification>>(emptyList())
        private set

    init {
        loadNotifications()
    }

    private fun loadNotifications() {

        viewModelScope.launch {

            notificationsDataStore.readNotificationIds.collect { readIds ->

                notifications = repository.getNotifications().map { notification ->

                    notification.copy(
                        isRead = notification.id in readIds
                    )
                }
            }
        }
    }

    fun markAsRead(notificationId: Int) {

        viewModelScope.launch {
            notificationsDataStore.markAsRead(notificationId)
        }
    }

    val hasNotification: Boolean
        get() = notifications.any { !it.isRead }
}