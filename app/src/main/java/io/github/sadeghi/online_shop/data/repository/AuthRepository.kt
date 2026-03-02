package io.github.sadeghi.online_shop.data.repository

import io.github.sadeghi.online_shop.database.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val userPreferences: UserPreferences
) : IAuthRepository {

    override suspend fun verifyOtp(email: String, otp: String): Boolean {
        return  (otp == "123456")



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
}
