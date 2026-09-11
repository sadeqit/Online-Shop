package io.github.sadeghi.online_shop.ui.screens.profilescreen.address

data class Address(
    val id: Int,
    val receiver: String,
    val address: String,
    val postalCode: String,
    val phoneNumber: String,
    val isDefault: Boolean
)
