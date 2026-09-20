package io.github.sadeghi.online_shop.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.ui.screens.profilescreen.address.Address
import io.github.sadeghi.online_shop.data.repository.AddressRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val repository: AddressRepository
) : ViewModel() {

    val addresses: StateFlow<List<Address>> =
        repository.addresses
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    init {
        viewModelScope.launch {
            repository.loadAddresses()
        }
    }


    var fullName by mutableStateOf("")
        private set

    var address by mutableStateOf("")
        private set

    var postalCode by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var fullNameError by mutableStateOf<String?>(null)
        private set

    var addressError by mutableStateOf<String?>(null)
        private set

    var postalCodeError by mutableStateOf<String?>(null)
        private set

    var phoneNumberError by mutableStateOf<String?>(null)
        private set

    fun onFullNameChange(value: String) {
        fullName = value
        fullNameError = null
    }

    fun onAddressChange(value: String) {
        address = value
        addressError = null
    }

    fun onPostalCodeChange(value: String) {
        postalCode = value
        postalCodeError = null
    }

    fun onPhoneNumberChange(value: String) {
        phoneNumber = value
        phoneNumberError = null
    }

    fun loadAddress(address: Address) {

        fullName = address.receiver
        this.address = address.address
        postalCode = address.postalCode
        phoneNumber = address.phoneNumber

        fullNameError = null
        addressError = null
        postalCodeError = null
        phoneNumberError = null
    }

    fun clearForm() {

        fullName = ""
        address = ""
        postalCode = ""
        phoneNumber = ""

        fullNameError = null
        addressError = null
        postalCodeError = null
        phoneNumberError = null
    }


    fun addAddress(onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.addAddress(
                receiver = fullName,
                address = address,
                postalCode = postalCode,
                phoneNumber = phoneNumber
            )

            onSuccess()
        }
    }

    fun updateAddress(
        id: UUID,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            repository.updateAddress(
                id = id,
                receiver = fullName,
                address = address,
                postalCode = postalCode,
                phoneNumber = phoneNumber
            )

            onSuccess()
        }
    }

    fun deleteAddress(id: UUID) {

        viewModelScope.launch {
            repository.deleteAddress(id)
        }
    }

    fun setDefaultAddress(id: UUID) {

        viewModelScope.launch {
            repository.setDefaultAddress(id)
        }
    }

    fun validateForm(): Boolean {

        var isValid = true

        if (fullName.isBlank()) {
            fullNameError =
                "نام و نام خانوادگی را وارد کنید"

            isValid = false
        }

        if (address.isBlank()) {
            addressError =
                "آدرس را وارد کنید"

            isValid = false
        }

        if (postalCode.isBlank()) {

            postalCodeError =
                "کد پستی را وارد کنید"

            isValid = false

        } else if (postalCode.length != 10) {

            postalCodeError =
                "کد پستی باید ۱۰ رقم باشد"

            isValid = false
        }

        if (phoneNumber.isBlank()) {

            phoneNumberError =
                "شماره همراه را وارد کنید"

            isValid = false

        } else if (
            !phoneNumber.startsWith("09") ||
            phoneNumber.length != 11
        ) {

            phoneNumberError =
                "شماره همراه معتبر نیست"

            isValid = false
        }

        return isValid
    }
}