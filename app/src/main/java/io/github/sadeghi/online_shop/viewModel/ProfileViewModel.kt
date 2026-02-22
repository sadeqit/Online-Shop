package io.github.sadeghi.online_shop.viewModel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val application: Application

) : ViewModel(){
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
}