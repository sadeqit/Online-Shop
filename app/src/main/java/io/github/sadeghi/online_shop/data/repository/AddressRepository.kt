package io.github.sadeghi.online_shop.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import io.github.sadeghi.online_shop.data.remote.model.AddressDto
import io.github.sadeghi.online_shop.ui.screens.profilescreen.address.Address
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddressRepository @Inject constructor(
    private val supabaseClient: SupabaseClient
) {

    private val _addresses = MutableStateFlow<List<Address>>(emptyList())

    val addresses: Flow<List<Address>> = _addresses.asStateFlow()

    suspend fun loadAddresses() {
        val result = supabaseClient
            .from("addresses")
            .select {
                order("created_at", Order.ASCENDING)
            }
            .decodeList<AddressDto>()

        _addresses.value = result.map { it.toAddress() }
    }

    suspend fun addAddress(
        receiver: String,
        address: String,
        postalCode: String,
        phoneNumber: String
    ) {
        val isFirstAddress = _addresses.value.isEmpty()

        supabaseClient
            .from("addresses")
            .insert(
                buildJsonObject {
                    put("receiver", receiver)
                    put("address", address)
                    put("postal_code", postalCode)
                    put("phone_number", phoneNumber)
                    put("is_default", isFirstAddress)
                }
            )

        loadAddresses()
    }

    suspend fun updateAddress(
        id: UUID,
        receiver: String,
        address: String,
        postalCode: String,
        phoneNumber: String
    ) {
        supabaseClient
            .from("addresses")
            .update(
                buildJsonObject {
                    put("receiver", receiver)
                    put("address", address)
                    put("postal_code", postalCode)
                    put("phone_number", phoneNumber)
                }
            ) {
                filter {
                    eq("id", id.toString())
                }
            }

        loadAddresses()
    }

    suspend fun deleteAddress(id: UUID) {
        val addressToDelete = _addresses.value.find { it.id == id }

        supabaseClient
            .from("addresses")
            .delete {
                filter {
                    eq("id", id.toString())
                }
            }

        if (addressToDelete?.isDefault == true) {
            val remainingAddresses = _addresses.value.filterNot { it.id == id }

            if (remainingAddresses.isNotEmpty()) {
                setDefaultAddress(remainingAddresses.first().id)
            }
        }

        loadAddresses()
    }

    suspend fun setDefaultAddress(id: UUID) {
        supabaseClient
            .postgrest
            .rpc(
                function = "set_default_address",
                parameters = buildJsonObject {
                    put("address_id", id.toString())
                }
            )

        loadAddresses()
    }

    private fun AddressDto.toAddress(): Address {
        return Address(
            id = UUID.fromString(id),
            receiver = receiver,
            address = address,
            postalCode = postalCode,
            phoneNumber = phoneNumber,
            isDefault = isDefault
        )
    }
}
