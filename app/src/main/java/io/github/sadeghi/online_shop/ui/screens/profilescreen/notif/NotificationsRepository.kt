package io.github.sadeghi.online_shop.ui.screens.profilescreen.notif

interface NotificationsRepository {

    fun getNotifications(): List<Notification>
}