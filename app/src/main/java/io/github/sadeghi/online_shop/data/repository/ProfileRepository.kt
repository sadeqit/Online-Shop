package io.github.sadeghi.online_shop.data.repository

import io.github.sadeghi.online_shop.data.local.datastore.UserPreferences
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val userPreferences: UserPreferences
) : IProfileRepository {

    override suspend fun saveProfileImage(uri: String) {
        userPreferences.saveProfileImage(uri)
    }
}