package io.github.sadeghi.online_shop.ui.screens.loginscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Key
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.sadeghi.online_shop.ui.component.AppTextField
import io.github.sadeghi.online_shop.ui.component.GradientButton
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.viewModel.LoginViewModel

@Composable
fun SignInContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onBack: () -> Unit,
    focusManager: FocusManager
) {


    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LogoHeader()

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "لطفا ایمیل و پسورد خود را وارد نمایید",
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 16.dp),
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.titleMedium
            )
            SpacerHeight(40)

            AppTextField(
                value = email,
                onValueChange = onEmailChange,
                placeholder = "ایمیل",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null
                    )
                },
                onImeAction = { focusManager.moveFocus(FocusDirection.Down) }
            )

            SpacerHeight(16)

            AppTextField(
                value = password,
                onValueChange = onPasswordChange,
                placeholder = "رمز عبور",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                onImeAction = {
                    focusManager.clearFocus()
                    onLoginClick()
                },
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible }
                    ) {
                        Icon(
                            imageVector =
                                if (passwordVisible)
                                    Icons.Outlined.Visibility
                                else
                                    Icons.Outlined.VisibilityOff,
                            contentDescription =
                                if (passwordVisible)
                                    "Hide password"
                                else
                                    "Show password"
                        )
                    }
                }
            )

            SpacerHeight(24)

            GradientButton(
                text = "ورود",
                enabled = email.isNotBlank() && password.isNotBlank(),
                onClick = {
                    focusManager.clearFocus()
                    onLoginClick()
                }
            )

            SpacerHeight(12)
            Text(
                text = "بازگشت",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
                    .clickable {
                        focusManager.clearFocus()
                        onBack()
                    },
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}