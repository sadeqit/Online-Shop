package io.github.sadeghi.online_shop.ui.screens.profilescreen.notif

import io.github.sadeghi.online_shop.ui.screens.profilescreen.orfer.formatPersianDate
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