package io.github.sadeghi.online_shop.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.data.local.datastore.UserPreferences
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
    private val userPreferences: UserPreferences
) : ViewModel() {

    var phoneNumber by mutableStateOf("")
        private set

    fun onPhoneNumberChange(value: String) {
        phoneNumber = value.trim()
    }

    var data by mutableStateOf("")
        private set

    fun onDataChange(value: String) {
        data = value.trim()
    }

    val profileImageUri: Flow<String?> =
        userPreferences.profileImageUri

    fun saveProfileImage(uri: String) {
        viewModelScope.launch {
            profileRepository.saveProfileImage(uri)
        }
    }
}

