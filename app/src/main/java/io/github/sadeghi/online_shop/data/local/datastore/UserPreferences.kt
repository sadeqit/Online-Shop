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

        private val USER_PHONE =
            stringPreferencesKey("user_phone")

        private val USER_BIRTH_DATE =
            stringPreferencesKey("user_birth_date")

        private val USER_GENDER =
            stringPreferencesKey("user_gender")

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

    suspend fun savePasswordSet() {
        dataStore.edit {
            it[IS_PASSWORD_SET] = true
        }
    }

    suspend fun saveFullName(fullName: String) {
        dataStore.edit {
            it[USER_FULL_NAME] = fullName
            it[IS_FULLNAME_SET] = fullName.isNotBlank()
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
            it.remove(USER_FULL_NAME)
            it.remove(USER_PHONE)
            it.remove(USER_BIRTH_DATE)
            it.remove(USER_GENDER)
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

    suspend fun saveProfile(
        fullName: String,
        phoneNumber: String,
        email: String,
        birthDate: String,
        gender: String
    ) {
        dataStore.edit {

            it[USER_FULL_NAME] = fullName
            it[USER_PHONE] = phoneNumber
            it[USER_EMAIL] = email
            it[USER_BIRTH_DATE] = birthDate
            it[USER_GENDER] = gender

            it[IS_FULLNAME_SET] = fullName.isNotBlank()
        }
    }
    val userFullName: Flow<String> =
        dataStore.data.map {
            it[USER_FULL_NAME] ?: ""
        }

    val userPhone: Flow<String> =
        dataStore.data.map {
            it[USER_PHONE] ?: ""
        }

    val userBirthDate: Flow<String> =
        dataStore.data.map {
            it[USER_BIRTH_DATE] ?: ""
        }

    val userGender: Flow<String> =
        dataStore.data.map {
            it[USER_GENDER] ?: ""
        }
}
