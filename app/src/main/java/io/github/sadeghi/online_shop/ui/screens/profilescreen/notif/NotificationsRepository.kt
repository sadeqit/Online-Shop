package io.github.sadeghi.online_shop.ui.screens.profilescreen.notif


interface NotificationsRepository {

    suspend fun getNotifications(): List<Notification>

    suspend fun markAsRead(notificationId: Long)
}