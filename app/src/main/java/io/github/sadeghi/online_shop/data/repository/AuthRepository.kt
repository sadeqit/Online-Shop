package io.github.sadeghi.online_shop.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.OTP
import io.github.sadeghi.online_shop.data.local.datastore.UserPreferences
import io.github.sadeghi.online_shop.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val userPreferences: UserPreferences,
    private val supabaseClient: SupabaseClient
) : IAuthRepository
{

    override suspend fun verifyOtp(
        email: String,
        otp: String
    ): Boolean {
        supabaseClient.auth.verifyEmailOtp(
            type = OtpType.Email.EMAIL,
            email = email,
            token = otp
        )
        return true
    }

    suspend fun sendOtp(email: String) {
        supabaseClient.auth.signInWith(OTP) {
            this.email = email
        }
    }

    suspend fun signIn(
        email: String,
        password: String
    ) {
        supabaseClient.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun saveLogin(email: String) {
        userPreferences.saveLogin(email)
    }

    override fun isUserLoggedIn(): Flow<Boolean> {
        return userPreferences.isLoggedIn
    }

    override suspend fun logout() {
        userPreferences.clearLogin()
    }

    override suspend fun saveFullName(fullName: String) {
        userPreferences.saveFullName(fullName)
    }

    override fun getUserEmail(): Flow<String?> {
        return userPreferences.userEmail
    }

    override suspend fun savePhoneNumber(phoneNumber: String) {
        userPreferences.savePhoneNumber(phoneNumber)
    }

    // ثبت رمز اولیه بعد از OTP
    suspend fun updatePassword(newPassword: String) {
        supabaseClient.auth.updateUser {
            password = newPassword
        }
    }

    // تغییر رمز از صفحه پروفایل
    suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    ) {
        supabaseClient.auth.updateUser {
            currentPassword = oldPassword
            password = newPassword
        }
    }
}
