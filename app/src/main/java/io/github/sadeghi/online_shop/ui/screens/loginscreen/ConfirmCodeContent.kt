package io.github.sadeghi.online_shop.ui.screens.loginscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@SuppressLint("DefaultLocale")
@Composable
fun ConfirmCodeContent(
    email: String,
    code: String,
    timer: Int,
    isLoading: Boolean,
    errorMessage: String?,
    onCodeChange: (String) -> Unit,
    onVerifyCode: () -> Unit,
    onEditEmail: () -> Unit,
    onResendCode: () -> Unit,
    focusManager: FocusManager
) {

    val minutes = timer / 60
    val seconds = timer % 60
    val timeText = String.format("%02d:%02d", minutes, seconds)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        LogoHeader()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "کد تائید:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = " کد ارسال شده به ایمیل $email را وارد کنید ",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right,
                color = text,
                style = MaterialTheme.typography.bodyMedium
            )


            SpacerHeight(12)

            AppTextField(
                value = code,
                onValueChange = onCodeChange,
                placeholder = "کد تایید را وارد کنید",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
                trailingIcon = {
                    Text(
                        text = timeText,
                        color = if (timer == 0) Color.Red else Color.Gray
                    )
                },
                onImeAction = {
                    focusManager.clearFocus()
                    onVerifyCode()
                }
            )
            SpacerHeight(12)

            errorMessage?.let {
                Text(
                    text = it,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Right,
                    color = Color.Red,
                    style = MaterialTheme.typography.titleSmall
                )
            }

            SpacerHeight(12)

            GradientButton(
                text = "تایید کد و ادامه",
                enabled = code.isNotBlank() && !isLoading,
                onClick = {
                    focusManager.clearFocus()
                    onVerifyCode()
                }
            )

            SpacerHeight(12)
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
                            onEditEmail()
                        },
                    style = MaterialTheme.typography.titleSmall
                )

                Text(
                    text = "ارسال مجدد کد",
                    color = if (timer == 0) Color.Black else Color.Gray,
                    modifier = Modifier.clickable(
                        enabled = timer == 0
                    ) {
                        onResendCode()
                    },
                    style = MaterialTheme.typography.titleSmall
                )
            }


        }
    }
}