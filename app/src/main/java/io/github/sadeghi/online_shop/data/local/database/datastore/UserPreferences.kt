package io.github.sadeghi.online_shop.data.local.database.datastore

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
        private val IS_PASSWORD_SET = booleanPreferencesKey("is_password_set")
        private val IS_FULLNAME_SET = booleanPreferencesKey("is_fullname_set")
    }

    // وقتی OTP تایید شد
    suspend fun saveLogin(email: String) {
        context.dataStore.edit {
            it[IS_LOGGED_IN] = true
            it[USER_EMAIL] = email
        }
    }
    // وقتی پسورد ست شد
    suspend fun savePasswordSet() {
        context.dataStore.edit {
            it[IS_PASSWORD_SET] = true
        }
    }
    // وقتی نام کامل ثبت شد
    suspend fun saveFullNameSet() {
        context.dataStore.edit {
            it[IS_FULLNAME_SET] = true
        }
    }

    suspend fun clearLogin() {
        context.dataStore.edit {
            it[IS_LOGGED_IN] = false
            it[IS_PASSWORD_SET] = false
            it[IS_FULLNAME_SET] = false
            it.remove(USER_EMAIL)
        }
    }

    // ---------------- FLOWS ----------------

    val isLoggedIn: Flow<Boolean> =
        context.dataStore.data.map {
            it[IS_LOGGED_IN] ?: false
        }

    val isPasswordSet: Flow<Boolean> =
        context.dataStore.data.map {
            it[IS_PASSWORD_SET] ?: false
        }

    val isFullNameSet: Flow<Boolean> =
        context.dataStore.data.map {
            it[IS_FULLNAME_SET] ?: false
        }

    val userEmail: Flow<String?> =
        context.dataStore.data.map {
            it[USER_EMAIL]
        }
}