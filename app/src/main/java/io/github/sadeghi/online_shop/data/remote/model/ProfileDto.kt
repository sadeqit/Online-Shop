package io.github.sadeghi.online_shop.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProfileDto(
    val id: String,

    @SerialName("full_name")
    val fullName: String? = null,

    @SerialName("phone_number")
    val phoneNumber: String? = null,

    @SerialName("birth_date")
    val birthDate: String? = null,

    val gender: String? = null,

    @SerialName("avatar_url")
    val avatarUrl: String? = null
)