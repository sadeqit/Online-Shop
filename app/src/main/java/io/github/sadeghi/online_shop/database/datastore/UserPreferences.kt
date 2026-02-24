package io.github.sadeghi.online_shop.database.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        private val USER_EMAIL = stringPreferencesKey("user_email")
    }

    suspend fun saveLogin(email: String) {
        context.dataStore.edit {
            it[IS_LOGGED_IN] = true
            it[USER_EMAIL] = email
        }
    }

    suspend fun clearLogin() {
        context.dataStore.edit {
            it[IS_LOGGED_IN] = false
            it.remove(USER_EMAIL)
        }
    }

    val isLoggedIn: Flow<Boolean> =
        context.dataStore.data.map {
            it[IS_LOGGED_IN] ?: false
        }

    val userEmail: Flow<String?> =
        context.dataStore.data.map {
            it[USER_EMAIL]
        }
}