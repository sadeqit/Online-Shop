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
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val application: Application,
    private val repository: AuthRepository) : ViewModel()
{

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

    var phoneNumber by mutableStateOf("")
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

    var password by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set

    var passwordError by mutableStateOf<String?>(null)


    var passwordStrength by mutableStateOf(PasswordStrength.NONE)
        private set

    fun onPhoneNumberChange(value: String) {
        phoneNumber = value
        errorMessage = null
    }


    // ====================
    // صفحه Setup
    // ====================
    fun goToRegister() {
        step = LoginStep.ENTER_EMAIL
    }

    fun goToSignIn() {
        step = LoginStep.SIGN_IN
    }

    // ====================
    // صفحه Sign In
    // ====================

    fun signIn(onSuccess: () -> Unit) {
        if (email.isBlank()) {
            errorMessage = "ایمیل را وارد کنید"
            return
        }
        if (!isEmailValid()) {
            errorMessage = "فرمت ایمیل صحیح نیست"
            return
        }
        if (password.isBlank()) {
            errorMessage = "رمز عبور را وارد کنید"
            return
        }
        if (!hasInternet()) {
            errorMessage = "اینترنت متصل نیست"
            return
        }
        errorMessage = null
        isLoading = true
        viewModelScope.launch {
            try {
                val savedEmail = repository.getCurrentEmail()
                val savedPassword = repository.getCurrentPassword()
                delay(500.milliseconds)
                when {
                    savedEmail == null -> {
                        errorMessage = "حسابی با این ایمیل پیدا نشد"
                    }

                    savedEmail != email -> {
                        errorMessage = "ایمیل یا رمز عبور اشتباه است"
                    }

                    savedPassword == null -> {
                        errorMessage = "برای این حساب رمز عبوری ثبت نشده است"
                    }

                    savedPassword != password -> {
                        errorMessage = "ایمیل یا رمز عبور اشتباه است"
                    }

                    else -> {
                        repository.saveLogin(savedEmail)
                        onSuccess()
                    }
                }
            } catch (e: Exception) {
                errorMessage = "خطایی در ورود رخ داد"
            } finally {
                isLoading = false
            }
        }
    }


    // ====================
    // صفحه Enter Email
    // ====================
    fun onEmailChange(value: String) {
        email = value.trim()
        if (errorMessage != "اینترنت متصل نیست") {
            errorMessage = null
        }

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

    fun sendEmail() {
        if (!hasInternet()) {
            errorMessage = "اینترنت متصل نیست"
            return
        }
        isInternetAvailable = true
        isLoading = true

        viewModelScope.launch {
            delay(1200.milliseconds)
            startTimer()
            isLoading = false
            step = LoginStep.CONFIRM_CODE
        }
    }

    // ====================
    // صفحه Confirm Code
    // ====================
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
            delay(1200.milliseconds) // شبیه‌سازی API

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
            delay(1200.milliseconds)
            startTimer()
            isLoading = false

        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timer = 30

        timerJob = viewModelScope.launch {
            while (timer > 0) {
                delay(1200.milliseconds)
                timer--
            }
        }
    }

    fun editEmail() {
        code = ""
        timerJob?.cancel()
        step = LoginStep.ENTER_EMAIL
    }

    // ====================
    // صفحه Set Password
    // ====================
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

    fun submitPassword() {
        validatePassword()

        if (passwordError != null) return

        isLoading = true

        viewModelScope.launch {

            delay(1200.milliseconds)

            repository.savePassword(password)

            isLoading = false
            step = LoginStep.SUBMIT_INFO
        }
    }

    private fun validatePassword() {
        passwordError = when {
            password.isNotEmpty() &&
                    confirmPassword.isNotEmpty() &&
                    password != confirmPassword ->
                "رمز عبور و تکرار آن یکسان نیست"

            else -> null
        }
    }

    // ====================
    // صفحه Submit User Info
    // ====================
    fun onFullNameChange(value: String) {
        fullName = value
        if (errorMessage != "اینترنت متصل نیست") {
            errorMessage = null
        }
    }

    fun submitFullName(onSuccess: () -> Unit) {
        if (!hasInternet()) {
            errorMessage = "اینترنت متصل نیست"
            return
        }

        if (fullName.isBlank()) {
            errorMessage = "نام و نام خانوادگی را وارد کنید"
            return
        }

        if (phoneNumber.length != 11 || !phoneNumber.startsWith("09")) {
            errorMessage = "شماره همراه معتبر نیست"
            return
        }

        errorMessage = null
        isLoading = true

        viewModelScope.launch {
            delay(1200.milliseconds)

            repository.saveLogin(email)
            repository.saveFullName(fullName)
            repository.savePhoneNumber(phoneNumber)

            isLoading = false

            onSuccess()
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
            delay(1200.milliseconds)
            isLoading = false

        }
    }

    // ====================
    // توابع مشترک و کمکی
    // ====================
    fun backToSetup() {
        step = LoginStep.SETUP
    }

    fun isEmailValid(): Boolean {
        return emailRegex.matches(email)
    }

    fun hasInternet(): Boolean {
        return isNetworkAvailable(application)
    }


}