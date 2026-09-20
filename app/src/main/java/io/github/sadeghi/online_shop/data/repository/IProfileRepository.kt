package io.github.sadeghi.online_shop.data.repository

import kotlinx.coroutines.flow.Flow

interface IProfileRepository {

    suspend fun saveProfileImage(uri: String)

    suspend fun saveProfile(
        fullName: String,
        phoneNumber: String,
        birthDate: String,
        gender: String
    )

    fun getFullName(): Flow<String>

    fun getPhoneNumber(): Flow<String>

    fun getEmail(): Flow<String?>

    fun getBirthDate(): Flow<String>

    fun getGender(): Flow<String>

    fun getProfileImage(): Flow<String?>
}

