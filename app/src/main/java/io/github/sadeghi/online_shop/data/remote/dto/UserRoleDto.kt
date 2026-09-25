package io.github.sadeghi.online_shop.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRoleDto(
    @SerialName("user_id")
    val userId: String,

    val role: String
)