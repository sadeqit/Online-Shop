package io.github.sadeghi.online_shop.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val IS_LOGGED_IN =
            booleanPreferencesKey("is_logged_in")

        private val USER_EMAIL =
            stringPreferencesKey("user_email")

        private val IS_PASSWORD_SET =
            booleanPreferencesKey("is_password_set")

        private val IS_FULLNAME_SET =
            booleanPreferencesKey("is_fullname_set")

        private val PROFILE_IMAGE_URI =
            stringPreferencesKey("profile_image_uri")
    }

    // ---------------- SAVE ----------------

    suspend fun saveLogin(email: String) {
        dataStore.edit {
            it[IS_LOGGED_IN] = true
            it[USER_EMAIL] = email
        }
    }

    suspend fun savePasswordSet() {
        dataStore.edit {
            it[IS_PASSWORD_SET] = true
        }
    }

    suspend fun saveFullNameSet() {
        dataStore.edit {
            it[IS_FULLNAME_SET] = true
        }
    }

    suspend fun saveProfileImage(uri: String) {
        dataStore.edit {
            it[PROFILE_IMAGE_URI] = uri
        }
    }

    suspend fun clearLogin() {
        dataStore.edit {
            it[IS_LOGGED_IN] = false
            it[IS_PASSWORD_SET] = false
            it[IS_FULLNAME_SET] = false

            it.remove(USER_EMAIL)
            it.remove(PROFILE_IMAGE_URI)
        }
    }

    // ---------------- FLOWS ----------------

    val isLoggedIn: Flow<Boolean> =
        dataStore.data.map {
            it[IS_LOGGED_IN] ?: false
        }

    val isPasswordSet: Flow<Boolean> =
        dataStore.data.map {
            it[IS_PASSWORD_SET] ?: false
        }

    val isFullNameSet: Flow<Boolean> =
        dataStore.data.map {
            it[IS_FULLNAME_SET] ?: false
        }

    val userEmail: Flow<String?> =
        dataStore.data.map {
            it[USER_EMAIL]
        }

    val profileImageUri: Flow<String?> =
        dataStore.data.map {
            it[PROFILE_IMAGE_URI]
        }
}
/*

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
}*/
