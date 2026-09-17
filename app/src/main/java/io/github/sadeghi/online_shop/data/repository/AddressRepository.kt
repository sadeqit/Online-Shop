package io.github.sadeghi.online_shop.data.repository

import io.github.sadeghi.online_shop.data.local.datastore.AddressDataStore
import io.github.sadeghi.online_shop.ui.screens.profilescreen.address.Address
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddressRepository @Inject constructor(
    private val addressDataStore: AddressDataStore
) {

    val addresses: Flow<List<Address>> =
        addressDataStore.addresses

    suspend fun addAddress(
        receiver: String,
        address: String,
        postalCode: String,
        phoneNumber: String
    ) {

        val currentAddresses =
            addressDataStore.addresses.first()

        val newId =
            (currentAddresses.maxOfOrNull { it.id } ?: 0) + 1

        val newAddress = Address(
            id = newId,
            receiver = receiver,
            address = address,
            postalCode = postalCode,
            phoneNumber = phoneNumber,
            isDefault = currentAddresses.isEmpty()
        )

        addressDataStore.addAddress(newAddress)
    }

    suspend fun updateAddress(
        id: Int,
        receiver: String,
        address: String,
        postalCode: String,
        phoneNumber: String
    ) {

        val currentAddresses =
            addressDataStore.addresses.first()

        val currentAddress =
            currentAddresses.find {
                it.id == id
            } ?: return

        val updatedAddress = currentAddress.copy(
            receiver = receiver,
            address = address,
            postalCode = postalCode,
            phoneNumber = phoneNumber
        )

        addressDataStore.updateAddress(updatedAddress)
    }

    suspend fun deleteAddress(id: Int) {

        val currentAddresses =
            addressDataStore.addresses.first()

        val addressToDelete =
            currentAddresses.find {
                it.id == id
            } ?: return

        addressDataStore.deleteAddress(id)

        if (
            addressToDelete.isDefault &&
            currentAddresses.size > 1
        ) {

            val newDefault =
                currentAddresses
                    .filterNot { it.id == id }
                    .first()

            addressDataStore.setDefaultAddress(
                newDefault.id
            )
        }
    }

    suspend fun setDefaultAddress(id: Int) {
        addressDataStore.setDefaultAddress(id)
    }
}