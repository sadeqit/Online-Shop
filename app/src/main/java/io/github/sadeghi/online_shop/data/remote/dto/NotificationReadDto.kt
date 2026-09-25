package io.github.sadeghi.online_shop.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationReadDto(
    @SerialName("notification_id")
    val notificationId: Long,
    @SerialName("user_id")
    val userId: String
)