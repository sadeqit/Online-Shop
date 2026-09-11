package io.github.sadeghi.online_shop.ui.screens.profilescreen.address

import io.github.sadeghi.online_shop.data.local.datastore.AddressDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

/*
@Singleton
class AddressRepository @Inject constructor() {

    private val _addresses = MutableStateFlow(fakeAddresses)

    val addresses: StateFlow<List<Address>> = _addresses.asStateFlow()

    fun addAddress(
        receiver: String,
        address: String,
        postalCode: String,
        phoneNumber: String
    ) {

        val newId = (_addresses.value.maxOfOrNull { it.id } ?: 0) + 1

        val newAddress = Address(
            id = newId,
            receiver = receiver,
            address = address,
            postalCode = postalCode,
            phoneNumber = phoneNumber,
            isDefault = _addresses.value.isEmpty()
        )

        _addresses.value += newAddress
    }

    fun updateAddress(
        id: Int,
        receiver: String,
        address: String,
        postalCode: String,
        phoneNumber: String
    ) {

        _addresses.value = _addresses.value.map { item ->

            if (item.id == id) {
                item.copy(
                    receiver = receiver,
                    address = address,
                    postalCode = postalCode,
                    phoneNumber = phoneNumber
                )
            } else {
                item
            }
        }
    }

    fun deleteAddress(id: Int) {

        _addresses.value = _addresses.value.filterNot {
            it.id == id
        }
    }

    fun setDefaultAddress(id: Int) {

        _addresses.value = _addresses.value.map { item ->
            item.copy(
                isDefault = item.id == id
            )
        }
    }
}*/
