package io.github.sadeghi.online_shop.data.local.datastore

import kotlinx.serialization.Serializable

@Serializable
data class AddressData(
    val id: Int,
    val receiver: String,
    val address: String,
    val postalCode: String,
    val phoneNumber: String,
    val isDefault: Boolean
)