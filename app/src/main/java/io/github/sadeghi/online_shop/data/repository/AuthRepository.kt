package io.github.sadeghi.online_shop.data.repository

import io.github.sadeghi.online_shop.data.local.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val userPreferences: UserPreferences
) : IAuthRepository {

    override suspend fun verifyOtp(email: String, otp: String): Boolean {
        return (otp == "123456")

    }

    override fun isUserLoggedIn(): Flow<Boolean> {
        return userPreferences.isLoggedIn
    }

    override suspend fun logout() {
        userPreferences.clearLogin()
    }

    suspend fun saveLogin(email: String) {
        userPreferences.saveLogin(email)
    }

    override suspend fun saveFullName(fullName: String) {
        userPreferences.saveFullName(fullName)
    }

    suspend fun savePassword(password: String) {
        userPreferences.savePassword(password)
    }


    suspend fun getCurrentPassword(): String? {
        return userPreferences.userPassword.first()
    }

    suspend fun getCurrentEmail(): String? {
        return userPreferences.userEmail.first()
    }

    suspend fun updatePassword(newPassword: String) {
        userPreferences.updatePassword(newPassword)
    }
    override fun getUserEmail(): Flow<String?> {
        return userPreferences.userEmail
    }
    override suspend fun savePhoneNumber(phoneNumber: String) {
        userPreferences.savePhoneNumber(phoneNumber)
    }
}
