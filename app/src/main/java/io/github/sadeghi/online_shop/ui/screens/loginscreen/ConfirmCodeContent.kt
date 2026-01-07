package io.github.sadeghi.online_shop.ui.screens.loginscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.ui.theme.text
import io.github.sadeghi.online_shop.viewModel.LoginViewModel

@SuppressLint("DefaultLocale")
@Composable
fun ConfirmCodeContent(viewModel: LoginViewModel, focusManager: FocusManager) {

    val minutes = viewModel.timer / 60
    val seconds = viewModel.timer % 60
    val timeText = String.format("%02d:%02d", minutes, seconds)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LogoHeader()

        Text(
            text = "کد تائید:",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "کد ارسال شده به ایمیل ${viewModel.email} را وارد کنید",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Right,
            color = text,
            style = MaterialTheme.typography.bodyMedium
        )


        Spacer(Modifier.height(12.dp))

        AppTextField(
            value = viewModel.code,
            onValueChange = viewModel::onCodeChange,
            placeholder = "کد تایید را وارد کنید",
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
            trailingIcon = {
                Text(
                    text = timeText,
                    color = if (viewModel.timer == 0) Color.Red else Color.Gray
                )
            },
            onImeAction = {
                focusManager.clearFocus()
                viewModel.verifyCode() // ✅ درست
            }
        )
        Spacer(Modifier.height(12.dp))

        if (viewModel.errorMessage != null) {
            Text(
                text = viewModel.errorMessage!!,
                modifier = Modifier
                    .fillMaxWidth()
                    ,
                textAlign = TextAlign.Right,
                color = Color.Red,
                style = MaterialTheme.typography.titleSmall
            )
        }

        Spacer(Modifier.height(12.dp))
        GradientButton(
            text = "تایید کد و ادامه",
            enabled = viewModel.code.isNotBlank() && !viewModel.isLoading,
            onClick = {
                focusManager.clearFocus()
                viewModel.verifyCode()
            }
        )

        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "ویرایش ایمیل",
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable {
                        focusManager.clearFocus()
                        viewModel.editEmail()
                    },
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = "ارسال مجدد کد",
                color = if (viewModel.timer == 0) Color.Black else Color.Gray,
                modifier = Modifier.clickable(
                    enabled = viewModel.timer == 0
                ) {
                    viewModel.resendCode()
                },
                style = MaterialTheme.typography.titleSmall
            )
        }


    }
}
