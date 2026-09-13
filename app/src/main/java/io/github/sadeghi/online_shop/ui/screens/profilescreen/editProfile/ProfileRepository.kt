package io.github.sadeghi.online_shop.ui.screens.profilescreen.editProfile

interface ProfileRepository {

    suspend fun getProfile(): UserProfile

    suspend fun saveProfile(profile: UserProfile)

    suspend fun saveProfileImage(uri: String): String

    suspend fun deleteProfileImage()
}