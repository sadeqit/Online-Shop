package io.github.sadeghi.online_shop.ui.screens.loginscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.ui.theme.text
import io.github.sadeghi.online_shop.viewModel.LoginViewModel

@Composable
fun EnterEmailContent(viewModel: LoginViewModel,focusManager: FocusManager) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 110.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LogoHeader()

        Text(text = "جهت ورود به فروشگاه اینترنتی آنلاین شاپ ایمیل خود را در کادر زیر وارد کرده و کد ارسالی به ایمیل خود را در مرحله بعد وارد کنید.",
            color = text,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(50.dp))
        Text(
            text = "ایمیل:",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        AppTextField(
            value = viewModel.email,
            onValueChange = viewModel::onEmailChange,
            placeholder = "ایمیل خود را وارد کنید",
            imeAction = ImeAction.Done,
            onImeAction = {
                focusManager.clearFocus()
                viewModel.onEmailSubmit()
            }

        )



        Spacer(Modifier.height(24.dp))

        GradientButton(
            text = "تایید و ادامه",
            enabled = viewModel.email.isNotBlank() && !viewModel.isLoading,
            onClick = {

                focusManager.clearFocus()
                viewModel.onEmailSubmit() }
        )
    }
}
