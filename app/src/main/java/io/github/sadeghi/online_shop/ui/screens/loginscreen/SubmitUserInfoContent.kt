package io.github.sadeghi.online_shop.ui.screens.loginscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.ui.component.AppTextField
import io.github.sadeghi.online_shop.ui.component.GradientButton
import io.github.sadeghi.online_shop.ui.component.SpacerHeight

@Composable
fun SubmitUserInfoContent(
    fullName: String,
    phoneNumber: String,
    errorMessage: String?,
    isLoading: Boolean,
    isInternetAvailable: Boolean,
    onFullNameChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onSkip: () -> Unit,
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
                .padding(start = 16.dp, end = 16.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "اطلاعات کاربری:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.titleLarge
            )


            SpacerHeight(12)

            AppTextField(
                value = fullName,
                onValueChange = onFullNameChange,
                placeholder = "نام و نام خانوادگی خود را وارد کنید",
                imeAction = ImeAction.Next,
                onImeAction = {
                    focusManager.clearFocus()
                },
                trailingIcon = {
                    Icon(Icons.Default.Person,
                        contentDescription = null)
                }
            )
            SpacerHeight(16)

            Text(
                text = "شماره همراه:",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right
            )

            SpacerHeight(10)

            AppTextField(
                value = phoneNumber,
                onValueChange = {
                    if (it.length <= 11) {
                        onPhoneNumberChange(it)
                    }
                },
                placeholder = "*********09",
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done,
                onImeAction = {
                    focusManager.clearFocus()
                },
                trailingIcon = {
                    Icon(Icons.Default.Phone,
                        contentDescription = null)
                }
            )

            errorMessage?.let { message ->
                Text(
                    text = message,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            SpacerHeight(20)

            GradientButton(
                text = "ثبت اطلاعات",
                enabled = fullName.isNotBlank() &&
                        phoneNumber.length == 11 &&
                        !isLoading &&
                        isInternetAvailable,
                onClick = {
                    focusManager.clearFocus()
                    onSubmit()
                }
            )

            SpacerHeight(20)

            Text(
                text = "عبور از این مرحله",
                modifier = Modifier
                    .clickable {
                        focusManager.clearFocus()
                        onSkip()
                    }
                    .align(Alignment.Start),
                style = MaterialTheme.typography.titleMedium
            )

        }

    }
}