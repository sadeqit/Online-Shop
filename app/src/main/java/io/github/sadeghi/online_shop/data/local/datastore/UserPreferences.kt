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
)
{

    companion object {
        private val IS_LOGGED_IN =
            booleanPreferencesKey("is_logged_in")

        private val USER_EMAIL =
            stringPreferencesKey("user_email")

        private val IS_FULLNAME_SET =
            booleanPreferencesKey("is_fullname_set")

        private val USER_PHONE =
            stringPreferencesKey("user_phone")

        private val USER_FULL_NAME =
            stringPreferencesKey("user_full_name")

    }


    // ---------------- SAVE ----------------

    suspend fun saveLogin(email: String) {
        dataStore.edit {
            it[IS_LOGGED_IN] = true
            it[USER_EMAIL] = email
        }
    }



    suspend fun saveFullName(fullName: String) {
        dataStore.edit {
            it[USER_FULL_NAME] = fullName
            it[IS_FULLNAME_SET] = fullName.isNotBlank()
        }
    }
    suspend fun savePhoneNumber(phoneNumber: String) {
        dataStore.edit {
            it[USER_PHONE] = phoneNumber
        }
    }


    suspend fun clearLogin() {
        dataStore.edit {
            it[IS_LOGGED_IN] = false
        }
    }


    // ---------------- FLOWS ----------------

    val isLoggedIn: Flow<Boolean> =
        dataStore.data.map {
            it[IS_LOGGED_IN] ?: false
        }

    val userEmail: Flow<String?> =
        dataStore.data.map {
            it[USER_EMAIL]
        }


}
