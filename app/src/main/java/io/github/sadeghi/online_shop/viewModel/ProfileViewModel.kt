package io.github.sadeghi.online_shop.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.data.local.datastore.UserPreferences
import io.github.sadeghi.online_shop.data.repository.IAuthRepository
import io.github.sadeghi.online_shop.data.repository.IProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: IProfileRepository,
    private val authRepository: IAuthRepository
) : ViewModel()
{
    fun logout(
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                authRepository.logout()
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    var fullName by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var birthDate by mutableStateOf("")
        private set

    var gender by mutableStateOf("")
        private set

    var phoneNumberError by mutableStateOf<String?>(null)
        private set

    var birthDateError by mutableStateOf<String?>(null)
        private set

    var genderError by mutableStateOf<String?>(null)
        private set

    val profileImageUri: Flow<String?> =
        profileRepository.getProfileImage()

    var isSaving by mutableStateOf(false)
        private set

    init {
        loadProfile()
    }

    private fun loadProfile() {

        viewModelScope.launch {

            launch {
                profileRepository.getFullName()
                    .collect { value ->
                        fullName = value
                    }
            }

            launch {
                profileRepository.getPhoneNumber()
                    .collect { value ->
                        phoneNumber = value
                    }
            }

            launch {
                profileRepository.getEmail()
                    .collect { value ->
                        email = value ?: ""
                    }
            }

            launch {
                profileRepository.getBirthDate()
                    .collect { value ->
                        birthDate = value
                    }
            }

            launch {
                profileRepository.getGender()
                    .collect { value ->
                        gender = value
                    }
            }
        }
    }

    fun onPhoneNumberChange(value: String) {
        phoneNumber = value.trim()
        phoneNumberError = null
    }

    fun onBirthDateChange(value: String) {
        birthDate = value.trim()
        birthDateError = null
    }

    fun onGenderChange(value: String) {
        gender = value
        genderError = null
    }

    fun onFullNameChange(value: String) {
        fullName = value
    }


    fun saveProfile(
        onSuccess: () -> Unit = {}
    ) {

        if (isSaving) return

        viewModelScope.launch {

            isSaving = true

            try {

                profileRepository.saveProfile(
                    fullName = fullName,
                    phoneNumber = phoneNumber,
                    email = email,
                    birthDate = birthDate,
                    gender = gender
                )

                onSuccess()

            } finally {

                isSaving = false
            }
        }
    }

    fun saveProfileImage(uri: String) {

        viewModelScope.launch {
            profileRepository.saveProfileImage(uri)
        }
    }

    fun validateProfile(): Boolean {

        var isValid = true

        if (phoneNumber.isBlank()) {
            phoneNumberError = "شماره همراه را وارد کنید"
            isValid = false
        } else if (
            !phoneNumber.startsWith("09") ||
            phoneNumber.length != 11
        ) {
            phoneNumberError = "شماره همراه معتبر نیست"
            isValid = false
        }

        if (birthDate.isBlank()) {
            birthDateError = "تاریخ تولد را انتخاب کنید"
            isValid = false
        }

        if (gender.isBlank()) {
            genderError = "جنسیت را انتخاب کنید"
            isValid = false
        }

        return isValid
    }
}
