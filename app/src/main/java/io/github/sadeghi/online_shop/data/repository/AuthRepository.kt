package io.github.sadeghi.online_shop.data.repository

import io.github.sadeghi.online_shop.database.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow

class AuthRepository(
    private val userPreferences: UserPreferences
) {

    // فیک: بررسی OTP
    suspend fun verifyOtp(email: String, otp: String): Boolean {
        return if (otp == "123456") {
            userPreferences.saveLogin(email)
            true
        } else {
            false
        }
    }

    fun isUserLoggedIn(): Flow<Boolean> {
        return userPreferences.isLoggedIn
    }

    suspend fun logout() {
        userPreferences.clearLogin()
    }
}