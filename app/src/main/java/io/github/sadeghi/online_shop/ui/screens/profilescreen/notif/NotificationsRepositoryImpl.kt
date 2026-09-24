package io.github.sadeghi.online_shop.ui.screens.profilescreen.notif

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.sadeghi.online_shop.data.remote.model.NotificationDto
import io.github.sadeghi.online_shop.data.remote.model.NotificationReadDto
import javax.inject.Inject

class NotificationsRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : NotificationsRepository {

    override suspend fun getNotifications(): List<Notification> {

        val userId = supabaseClient.auth.currentUserOrNull()?.id
            ?: throw IllegalStateException("کاربر وارد نشده است")

        // اعلان‌ها
        val notifications = supabaseClient
            .from("notifications")
            .select()
            .decodeList<NotificationDto>()

        // اعلان‌هایی که این کاربر قبلاً خوانده
        val readNotifications = supabaseClient
            .from("notification_reads")
            .select {
                filter {
                    eq("user_id", userId)
                }
            }
            .decodeList<NotificationReadDto>()

        val readNotificationIds = readNotifications
            .map { it.notificationId }
            .toSet()

        return notifications
            .sortedByDescending { it.createdAt }
            .map { notification ->

                Notification(
                    id = notification.id,
                    subject = notification.subject,
                    message = notification.message,
                    date = notification.createdAt,
                    isRead = notification.id in readNotificationIds
                )
            }
    }

    override suspend fun markAsRead(notificationId: Long) {

        val userId = supabaseClient.auth.currentUserOrNull()?.id
            ?: throw IllegalStateException("کاربر وارد نشده است")

        println("MARK AS READ - notificationId = $notificationId")
        println("MARK AS READ - userId = $userId")

        try {

            supabaseClient
                .from("notification_reads")
                .upsert(
                    NotificationReadDto(
                        notificationId = notificationId,
                        userId = userId
                    )
                )

            println("MARK AS READ - SUCCESS")

        } catch (e: Exception) {

            println("MARK AS READ - ERROR = ${e.message}")
            e.printStackTrace()

            throw e
        }
    }
}