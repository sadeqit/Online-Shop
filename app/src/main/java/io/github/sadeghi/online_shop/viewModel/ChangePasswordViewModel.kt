package io.github.sadeghi.online_shop.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.data.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var currentPassword by mutableStateOf("")
        private set

    var newPassword by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set

    var currentPasswordError by mutableStateOf<String?>(null)
        private set

    var newPasswordError by mutableStateOf<String?>(null)
        private set

    var confirmPasswordError by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set


    fun onCurrentPasswordChange(value: String) {
        currentPassword = value
        currentPasswordError = null
    }

    fun onNewPasswordChange(value: String) {
        newPassword = value
        newPasswordError = null
    }

    fun onConfirmPasswordChange(value: String) {
        confirmPassword = value
        confirmPasswordError = null
    }


    fun changePassword(
        onSuccess: () -> Unit
    ) {

        var isValid = true

        // رمز فعلی
        if (currentPassword.isBlank()) {

            currentPasswordError =
                "رمز عبور فعلی را وارد کنید"

            isValid = false
        }

        // رمز جدید
        if (newPassword.isBlank()) {

            newPasswordError =
                "رمز عبور جدید را وارد کنید"

            isValid = false

        } else if (!isValidPassword(newPassword)) {

            newPasswordError =
                "رمز عبور باید حداقل ۸ کاراکتر و شامل حروف بزرگ و کوچک، عدد و علامت باشد"

            isValid = false
        }

        // تکرار رمز
        if (confirmPassword.isBlank()) {

            confirmPasswordError =
                "تکرار رمز عبور جدید را وارد کنید"

            isValid = false

        } else if (newPassword != confirmPassword) {

            confirmPasswordError =
                "رمز عبور جدید و تکرار آن یکسان نیست"

            isValid = false
        }

        // اگر اطلاعات فرم کامل نیست
        if (!isValid) {
            return
        }

        // رمز جدید نباید همان رمز قبلی باشد
        if (currentPassword == newPassword) {

            newPasswordError =
                "رمز عبور جدید باید با رمز فعلی متفاوت باشد"

            return
        }

        isLoading = true

        viewModelScope.launch {

            try {

                val savedPassword =
                    repository.getCurrentPassword()

                if (savedPassword == null) {

                    currentPasswordError =
                        "رمز عبور فعلی پیدا نشد"

                    isLoading = false

                    return@launch
                }

                // بررسی رمز فعلی
                if (currentPassword != savedPassword) {

                    currentPasswordError =
                        "رمز عبور فعلی اشتباه است"

                    isLoading = false

                    return@launch
                }

                delay(1200.milliseconds)

                repository.updatePassword(newPassword)

                isLoading = false

                onSuccess()

            } catch (e: Exception) {

                isLoading = false

                newPasswordError =
                    "خطایی رخ داد، دوباره تلاش کنید"
            }
        }
    }


    private fun isValidPassword(
        password: String
    ): Boolean {

        return password.length >= 8 &&
                password.any { it.isUpperCase() } &&
                password.any { it.isLowerCase() } &&
                password.any { it.isDigit() } &&
                password.any { !it.isLetterOrDigit() }
    }
}

