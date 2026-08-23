package io.github.sadeghi.online_shop.ui.screens.loginscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.ui.component.AppTextField
import io.github.sadeghi.online_shop.ui.component.GradientButton
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.ui_utils.PasswordStrength


@Composable
fun SetPasswordContent(
    password: String,
    confirmPassword: String,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    passwordError: String?,
    onSubmitClick: () -> Unit,
    passwordStrength: PasswordStrength,
    focusManager: FocusManager

) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LogoHeader()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 110.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "لطفاً یک رمز عبور مناسب وارد کنید",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.titleLarge
            )

            SpacerHeight(32)

            // 🔐 Password
            AppTextField(
                value = password,
                onValueChange = onPasswordChange,
                placeholder = "رمز عبور",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
                visualTransformation =
                    if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector =
                                if (passwordVisible)
                                    Icons.Outlined.Visibility
                                else
                                    Icons.Outlined.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            )

            SpacerHeight(16)

            // 🔐 Confirm Password
            AppTextField(
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                placeholder = "تکرار رمز عبور",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                visualTransformation =
                    if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                onImeAction = {
                    focusManager.clearFocus()
                    onSubmitClick()
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector =
                                if (passwordVisible)
                                    Icons.Outlined.Visibility
                                else
                                    Icons.Outlined.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            )

            // ❌ Error
            if (passwordStrength != PasswordStrength.NONE) {

                SpacerHeight(8)

                Text(
                    text = when (passwordStrength) {
                        PasswordStrength.TOO_SHORT -> "رمز عبور باید حداقل ۸ کاراکتر باشد"
                        PasswordStrength.WEAK -> "رمز عبور ضعیف است"
                        PasswordStrength.MEDIUM -> "رمز عبور متوسط است"
                        PasswordStrength.STRONG -> "رمز عبور قوی است"
                    },
                    color = when (passwordStrength) {
                        PasswordStrength.TOO_SHORT,
                        PasswordStrength.WEAK -> Color.Red
                        PasswordStrength.MEDIUM -> Color(0xFFFFA000)
                        PasswordStrength.STRONG -> Color(0xFF2E7D32)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            passwordError?.let {
                SpacerHeight(8)
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            SpacerHeight(24)

            GradientButton(
                text = "تأیید رمز عبور",
                enabled = password.length >= 8 && password == confirmPassword,
                onClick = {
                    focusManager.clearFocus()
                    onSubmitClick()
                }
            )
        }
    }
}