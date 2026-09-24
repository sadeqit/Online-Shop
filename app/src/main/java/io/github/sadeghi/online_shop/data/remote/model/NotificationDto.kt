package io.github.sadeghi.online_shop.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationDto(
    val id: Long,
    val subject: String,
    val message: String,
    @SerialName("created_at")
    val createdAt: String
)