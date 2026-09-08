package io.github.sadeghi.online_shop.data.repository


interface IProfileRepository {

    suspend fun saveProfileImage(uri: String)
}