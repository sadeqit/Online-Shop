package io.github.sadeghi.online_shop.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationDto(
    val id: Long,

    @SerialName("user_id")
    val userId: String?,

    val subject: String,
    val message: String,

    @SerialName("created_at")
    val createdAt: String
)