package io.github.sadeghi.online_shop.ui.screens.loginscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import io.github.sadeghi.online_shop.ui.component.AppTextField
import io.github.sadeghi.online_shop.ui.component.GradientButton
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.theme.text
import io.github.sadeghi.online_shop.viewModel.LoginViewModel

@Composable
fun EnterEmailContent(viewModel: LoginViewModel, focusManager: FocusManager) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 110.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LogoHeader()

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
            value = viewModel.email,
            onValueChange = viewModel::onEmailChange,
            placeholder = "ایمیل خود را وارد کنید",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done,
            onImeAction = {
                focusManager.clearFocus()
                viewModel.onEmailSubmit()
            }

        )

        viewModel.errorMessage?.let { message ->
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
            enabled = viewModel.email.isNotBlank() && !viewModel.isLoading && viewModel.isInternetAvailable,
            onClick = {
                focusManager.clearFocus()
                viewModel.onEmailSubmit()
            }
        )
    }
}
