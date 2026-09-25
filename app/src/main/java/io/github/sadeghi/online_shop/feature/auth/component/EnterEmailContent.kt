package io.github.sadeghi.online_shop.feature.auth.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.core.ui.component.AppTextField
import io.github.sadeghi.online_shop.core.ui.GradientButton
import io.github.sadeghi.online_shop.core.ui.SpacerHeight
import io.github.sadeghi.online_shop.core.theme.text

@Composable
fun EnterEmailContent(

    email: String,
    errorMessage: String?,
    isLoading: Boolean,
    isInternetAvailable: Boolean,
    onEmailChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    focusManager: FocusManager
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
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
                text = "جهت ورود به فروشگاه اینترنتی آنلاین شاپ ایمیل خود را در کادر زیر وارد کرده و کد ارسالی به ایمیل خود را در مرحله بعد وارد کنید.",
                color = text,
                style = MaterialTheme.typography.bodyMedium
            )

            SpacerHeight(50)
            Text(
                text = "ایمیل:",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.titleLarge
            )

            SpacerHeight(12)

            AppTextField(
                value = email,
                onValueChange = onEmailChange,
                placeholder = "ایمیل خود را وارد کنید",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = null
                    )
                },
                onImeAction = {
                    focusManager.clearFocus()
                    onSubmit()
                }

            )

            errorMessage?.let { message ->
                Text(
                    text = message,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.titleSmall
                )
            }

            SpacerHeight(24)

            GradientButton(
                text = "تایید و ادامه",
                enabled = email.contains("@")
                        && email.isNotBlank()
                        && !isLoading &&
                        isInternetAvailable,
                onClick = {
                    focusManager.clearFocus()
                    onSubmit()
                }
            )
            SpacerHeight(12)
            Text(
                text = "بازگشت",
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable {
                        focusManager.clearFocus()
                        onBack()
                    }
                    .align(Alignment.End),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}