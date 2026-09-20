package io.github.sadeghi.online_shop.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(
    val id: String,

    @SerialName("user_id")
    val userId: String,

    val receiver: String,
    val address: String,

    @SerialName("postal_code")
    val postalCode: String,

    @SerialName("phone_number")
    val phoneNumber: String,

    @SerialName("is_default")
    val isDefault: Boolean
)