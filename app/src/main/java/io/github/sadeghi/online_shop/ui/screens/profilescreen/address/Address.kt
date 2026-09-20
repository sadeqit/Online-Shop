package io.github.sadeghi.online_shop.ui.screens.profilescreen.address

import java.util.UUID

data class Address(
    val id: UUID,
    val receiver: String,
    val address: String,
    val postalCode: String,
    val phoneNumber: String,
    val isDefault: Boolean
)
/*data class Address(
    val id: Int,
    val receiver: String,
    val address: String,
    val postalCode: String,
    val phoneNumber: String,
    val isDefault: Boolean
)*/

