package io.github.sadeghi.online_shop.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.ui.screens.loginscreen.LoginStep
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {


    var email by mutableStateOf("")
        private set

    var code by mutableStateOf("")
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var step by mutableStateOf(LoginStep.ENTER_EMAIL)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var timer by mutableIntStateOf(30)
        private set

    private var timerJob: Job? = null


    /* ---------------- input handlers ---------------- */
    fun onEmailSubmit() {
        if (email.isBlank()) {
            errorMessage = "ایمیل نمی ‌تواند خالی باشد"
            return
        }

        sendEmail()
    }

    fun onEmailChange(value: String) {
        email = value
    }

    fun onCodeChange(value: String) {
        if (value.length <= 6)
            code = value
    }

    fun sendEmail() {
        errorMessage = null
        isLoading = true
        step = LoginStep.LOADING

        viewModelScope.launch {
            delay(5500)
            startTimer()
            isLoading = false
            step = LoginStep.CONFIRM_CODE
        }
    }
    fun verifyCode() {
        // اول اعتبارسنجی
        if (code.isBlank()) {
            errorMessage = "لطفا کد را وارد کنید"
            return
        }

        // سپس لودینگ و بررسی کد
        errorMessage = null
        isLoading = true
        step = LoginStep.LOADING

        viewModelScope.launch {
            delay(500) // شبیه‌سازی API

            isLoading = false

            if (code == "1234") {
                step = LoginStep.SUBMIT_INFO
            } else {
                errorMessage = "کد وارد شده اشتباه است"
                step = LoginStep.CONFIRM_CODE
            }
        }
    }



    fun resendCode() {
        if (timer > 0) return

        errorMessage = null
        isLoading = true
        step = LoginStep.LOADING

        viewModelScope.launch {
            delay(1200)
            startTimer()
            isLoading = false
            step = LoginStep.CONFIRM_CODE
        }
    }

    fun editEmail() {
        code = ""
        timerJob?.cancel()
        step = LoginStep.ENTER_EMAIL
    }

    /* ---------------- timer ---------------- */

    private fun startTimer() {
        timerJob?.cancel()
        timer = 10

        timerJob = viewModelScope.launch {
            while (timer > 0) {
                delay(1000)
                timer--
            }
        }
    }


    fun skipProfile() {
        // ورود نهایی
    }
}