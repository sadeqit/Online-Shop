package io.github.sadeghi.online_shop.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.github.sadeghi.online_shop.ui.screens.profilescreen.address.Address
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddressDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {

        private val ADDRESS_IDS =
            stringPreferencesKey("address_ids")

        private fun receiverKey(id: Int) =
            stringPreferencesKey("address_${id}_receiver")

        private fun addressKey(id: Int) =
            stringPreferencesKey("address_${id}_address")

        private fun postalCodeKey(id: Int) =
            stringPreferencesKey("address_${id}_postal_code")

        private fun phoneNumberKey(id: Int) =
            stringPreferencesKey("address_${id}_phone_number")

        private fun defaultKey(id: Int) =
            booleanPreferencesKey("address_${id}_is_default")
    }

    val addresses: Flow<List<Address>> =
        dataStore.data.map { preferences ->

            val ids = preferences[ADDRESS_IDS]
                ?.split(",")
                ?.mapNotNull { it.toIntOrNull() }
                ?: emptyList()

            ids.mapNotNull { id ->

                val receiver = preferences[receiverKey(id)]
                val address = preferences[addressKey(id)]
                val postalCode = preferences[postalCodeKey(id)]
                val phoneNumber = preferences[phoneNumberKey(id)]

                if (
                    receiver == null ||
                    address == null ||
                    postalCode == null ||
                    phoneNumber == null
                ) {
                    null
                } else {
                    Address(
                        id = id,
                        receiver = receiver,
                        address = address,
                        postalCode = postalCode,
                        phoneNumber = phoneNumber,
                        isDefault =
                            preferences[defaultKey(id)] ?: false
                    )
                }
            }
        }

    suspend fun addAddress(address: Address) {

        dataStore.edit { preferences ->

            val currentIds = preferences[ADDRESS_IDS]
                ?.split(",")
                ?.mapNotNull { it.toIntOrNull() }
                ?: emptyList()

            val newIds = currentIds + address.id

            preferences[ADDRESS_IDS] =
                newIds.joinToString(",")

            preferences[receiverKey(address.id)] =
                address.receiver

            preferences[addressKey(address.id)] =
                address.address

            preferences[postalCodeKey(address.id)] =
                address.postalCode

            preferences[phoneNumberKey(address.id)] =
                address.phoneNumber

            preferences[defaultKey(address.id)] =
                address.isDefault
        }
    }

    suspend fun updateAddress(address: Address) {

        dataStore.edit { preferences ->

            preferences[receiverKey(address.id)] =
                address.receiver

            preferences[addressKey(address.id)] =
                address.address

            preferences[postalCodeKey(address.id)] =
                address.postalCode

            preferences[phoneNumberKey(address.id)] =
                address.phoneNumber

            preferences[defaultKey(address.id)] =
                address.isDefault
        }
    }

    suspend fun deleteAddress(id: Int) {

        dataStore.edit { preferences ->

            val currentIds = preferences[ADDRESS_IDS]
                ?.split(",")
                ?.mapNotNull { it.toIntOrNull() }
                ?: emptyList()

            val newIds = currentIds.filterNot {
                it == id
            }

            if (newIds.isEmpty()) {
                preferences.remove(ADDRESS_IDS)
            } else {
                preferences[ADDRESS_IDS] =
                    newIds.joinToString(",")
            }

            preferences.remove(receiverKey(id))
            preferences.remove(addressKey(id))
            preferences.remove(postalCodeKey(id))
            preferences.remove(phoneNumberKey(id))
            preferences.remove(defaultKey(id))
        }
    }

    suspend fun setDefaultAddress(id: Int) {

        dataStore.edit { preferences ->

            val ids = preferences[ADDRESS_IDS]
                ?.split(",")
                ?.mapNotNull { it.toIntOrNull() }
                ?: emptyList()

            ids.forEach { addressId ->

                preferences[defaultKey(addressId)] =
                    addressId == id
            }
        }
    }
}