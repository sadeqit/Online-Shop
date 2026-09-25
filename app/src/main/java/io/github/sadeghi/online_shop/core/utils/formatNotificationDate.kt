package io.github.sadeghi.online_shop.core.utils

import java.time.OffsetDateTime

fun formatNotificationDate(date: String): String {
    return try {

        val dateTime = OffsetDateTime.parse(date)

        val timestamp = dateTime.toInstant().toEpochMilli()

        formatPersianDate(timestamp)

    } catch (e: Exception) {
        date
    }
}