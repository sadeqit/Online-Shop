package io.github.sadeghi.online_shop.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotificationsDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val READ_NOTIFICATION_IDS =
            stringSetPreferencesKey("read_notification_ids")
    }

    // ---------------- READ NOTIFICATIONS ----------------

    val readNotificationIds: Flow<Set<Int>> =
        dataStore.data.map { preferences ->

            preferences[READ_NOTIFICATION_IDS]
                ?.mapNotNull { it.toIntOrNull() }
                ?.toSet()
                ?: emptySet()
        }

    // ---------------- SAVE ----------------

    suspend fun markAsRead(notificationId: Int) {

        dataStore.edit { preferences ->

            val currentReadNotifications =
                preferences[READ_NOTIFICATION_IDS]
                    ?: emptySet()

            preferences[READ_NOTIFICATION_IDS] =
                currentReadNotifications + notificationId.toString()
        }
    }

    // ---------------- REMOVE ----------------

    suspend fun markAsUnread(notificationId: Int) {

        dataStore.edit { preferences ->

            val currentReadNotifications =
                preferences[READ_NOTIFICATION_IDS]
                    ?: emptySet()

            preferences[READ_NOTIFICATION_IDS] =
                currentReadNotifications - notificationId.toString()
        }
    }
}