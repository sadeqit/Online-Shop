package io.github.sadeghi.online_shop.viewModel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.data.repository.AuthRepository
import io.github.sadeghi.online_shop.ui.screens.loginscreen.LoginStep
import io.github.sadeghi.online_shop.ui.ui_utils.PasswordStrength
import io.github.sadeghi.online_shop.ui.ui_utils.calculatePasswordStrength
import io.github.sadeghi.online_shop.utils.isNetworkAvailable
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val application: Application,
    private val repository: AuthRepository

) : ViewModel() {

    var errorMessage by mutableStateOf<String?>(null)
        private set
    var email by mutableStateOf("")
        private set

    var isInternetAvailable by mutableStateOf(true)
        private set
    var isLoading by mutableStateOf(false)
        private set

    var fullName by mutableStateOf("")
        private set

    private val emailRegex = Regex(
        "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    )


    var code by mutableStateOf("")
        private set

    var step by mutableStateOf(LoginStep.SETUP)
        private set

    var timer by mutableIntStateOf(30)
        private set

    private var timerJob: Job? = null

    fun goToRegister() {
        step = LoginStep.ENTER_EMAIL
    }

    fun goToSignIn() {
        step = LoginStep.SIGN_IN
    }

    fun backToSetup() {
        step = LoginStep.SETUP
    }

    fun onEmailSubmit() {
        when {
            email.isBlank() -> {
                errorMessage = "ایمیل نمی‌تواند خالی باشد"
            }

            !isEmailValid() -> {
                errorMessage = "فرمت ایمیل صحیح نیست"
            }

            else -> {
                errorMessage = null
                sendEmail()
            }
        }
    }

    fun isEmailValid(): Boolean {
        return emailRegex.matches(email)
    }

    fun sendEmail() {
        if (!hasInternet()) {
            errorMessage = "اینترنت متصل نیست"
            return
        }
        isInternetAvailable = true
        isLoading = true

        viewModelScope.launch {
            delay(1200)
            startTimer()
            isLoading = false
            step = LoginStep.CONFIRM_CODE
        }
    }

    fun hasInternet(): Boolean {
        return isNetworkAvailable(application)
    }


    fun onEmailChange(value: String) {
        email = value.trim()
        if (errorMessage != "اینترنت متصل نیست") {
            errorMessage = null
        }

    }

    fun onCodeChange(value: String) {
        if (value.length <= 6)
            code = value
        errorMessage = null
    }

    fun verifyCode() {

        if (code.isBlank()) {
            errorMessage = "لطفا کد را وارد کنید"
            return
        }
        if (!hasInternet()) {
            errorMessage = "اینترنت متصل نیست"
            return
        }
        // سپس لودینگ و بررسی کد
        errorMessage = null
        isLoading = true

        viewModelScope.launch {
            val success = repository.verifyOtp(email, code)
            delay(1200) // شبیه‌سازی API

            isLoading = false

            if (success) {
                step = LoginStep.SET_PASSWORD
            } else {
                errorMessage = "کد وارد شده اشتباه است"
            }


        }
    }


    fun resendCode() {
        if (timer > 0) return

        if (!hasInternet()) {
            errorMessage = "اینترنت متصل نیست"
            return
        }

        errorMessage = null
        isLoading = true

        viewModelScope.launch {
            delay(1200)
            startTimer()
            isLoading = false

        }
    }

    fun editEmail() {
        code = ""
        timerJob?.cancel()
        step = LoginStep.ENTER_EMAIL
    }


    private fun startTimer() {
        timerJob?.cancel()
        timer = 30

        timerJob = viewModelScope.launch {
            while (timer > 0) {
                delay(1200)
                timer--
            }
        }
    }

    fun onSubmitInfo() {
        if (!hasInternet()) {
            errorMessage = "اینترنت متصل نیست"
            return
        }
        errorMessage = null
        isLoading = true

        viewModelScope.launch {
            delay(1200)
            isLoading = false

        }
    }

    fun onFullNameChange(value: String) {
        fullName = value.trim()
        if (errorMessage != "اینترنت متصل نیست") {
            errorMessage = null
        }
    }

    fun submitFullName(onSuccess: () -> Unit) {
        if (!hasInternet()) {
            errorMessage = "اینترنت متصل نیست"
            return
        }
        errorMessage = null
        isLoading = true

        viewModelScope.launch {

            delay(1200)

            repository.saveLogin(email)

            isLoading = false

            onSuccess()
        }
    }


    var password by mutableStateOf("")
        private set


    fun signIn(onSuccess: () -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "ایمیل و رمز عبور را وارد کنید"
            return
        }

        isLoading = true

        viewModelScope.launch {
            delay(1200)
            isLoading = false
            onSuccess()
        }
    }

    var confirmPassword by mutableStateOf("")
        private set

    var passwordError by mutableStateOf<String?>(null)


    fun submitPassword() {
        validatePassword()

        if (passwordError != null) return

        isLoading = true

        viewModelScope.launch {
            // شبیه‌سازی API
            delay(1200)

            isLoading = false
            step = LoginStep.SUBMIT_INFO
        }
    }

    var passwordStrength by mutableStateOf(PasswordStrength.NONE)
        private set


    private fun validatePassword() {
        passwordError = when {
            password.isNotEmpty() &&
                    confirmPassword.isNotEmpty() &&
                    password != confirmPassword ->
                "رمز عبور و تکرار آن یکسان نیست"

            else -> null
        }
    }


    fun onPasswordChange(value: String) {
        password = value
        passwordStrength = calculatePasswordStrength(value)
        validatePassword()
    }

    fun onConfirmPasswordChange(value: String) {
        confirmPassword = value
        passwordStrength = calculatePasswordStrength(value)
        validatePassword()
    }

}