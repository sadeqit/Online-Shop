package io.github.sadeghi.online_shop.data.repository

import io.github.sadeghi.online_shop.data.local.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val userPreferences: UserPreferences
) : IProfileRepository {

    override suspend fun saveProfileImage(uri: String) {
        userPreferences.saveProfileImage(uri)
    }

    override suspend fun saveProfile(
        fullName: String,
        phoneNumber: String,
        email: String,
        birthDate: String,
        gender: String
    ) {
        userPreferences.saveProfile(
            fullName = fullName,
            phoneNumber = phoneNumber,
            email = email,
            birthDate = birthDate,
            gender = gender
        )
    }

    override fun getFullName(): Flow<String> {
        return userPreferences.userFullName
    }

    override fun getPhoneNumber(): Flow<String> {
        return userPreferences.userPhone
    }

    override fun getEmail(): Flow<String?> {
        return userPreferences.userEmail
    }

    override fun getBirthDate(): Flow<String> {
        return userPreferences.userBirthDate
    }

    override fun getGender(): Flow<String> {
        return userPreferences.userGender
    }

    override fun getProfileImage(): Flow<String?> {
        return userPreferences.profileImageUri
    }
}