package io.github.sadeghi.online_shop.domain.repository

import io.github.sadeghi.online_shop.domain.model.Notification


interface INotificationsRepository {

    suspend fun getNotifications(): List<Notification>

    suspend fun markAsRead(notificationId: Long)
}