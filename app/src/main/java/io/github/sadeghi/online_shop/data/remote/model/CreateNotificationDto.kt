package io.github.sadeghi.online_shop.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateNotificationDto(
    @SerialName("user_id")
    val userId: String,
    val subject: String,
    val message: String
)