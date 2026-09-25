package io.github.sadeghi.online_shop.domain.model

data class Notification(
    val id: Long,
    val subject: String,
    val message: String,
    val date: String,
    val isRead: Boolean
)