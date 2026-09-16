package io.github.sadeghi.online_shop.data.repository

import kotlinx.coroutines.flow.Flow

interface IAuthRepository {

    suspend fun verifyOtp(email: String, otp: String): Boolean

    fun isUserLoggedIn(): Flow<Boolean>

    fun getUserEmail(): Flow<String?>

    suspend fun logout()

    suspend fun saveFullName(fullName: String)
    suspend fun savePhoneNumber(phoneNumber: String)
}