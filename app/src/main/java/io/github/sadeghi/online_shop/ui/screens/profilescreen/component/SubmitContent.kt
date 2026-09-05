package io.github.sadeghi.online_shop.ui.screens.profilescreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.ui.component.AppTextField
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.viewModel.LoginViewModel
import io.github.sadeghi.online_shop.viewModel.ProfileViewModel

@Composable
fun SubmitContent(
    viewModel: LoginViewModel,
    viewModel1: ProfileViewModel
) {

    Column(modifier = Modifier.padding(16.dp)) {

        Text("اسم و فامیل :")
        SpacerHeight(10)
        AppTextField(
            value = viewModel.fullName,
            onValueChange = viewModel::onFullNameChange,
            placeholder = "نام و نام خانوادگی خود را وارد کنید",
            imeAction = ImeAction.Done,
            onImeAction = {

            })

        SpacerHeight(25)

        Text("شماره همراه :")
        SpacerHeight(10)
        AppTextField(
            value = viewModel1.phoneNumber,
            onValueChange = viewModel1::onPhoneNumberChange,
            placeholder = "*********09",
            imeAction = ImeAction.Done,
            onImeAction = {}
        )

        SpacerHeight(25)

        Text("ایمیل :")
        SpacerHeight(10)
        AppTextField(
            value = viewModel.email,
            onValueChange = viewModel::onEmailChange,
            placeholder = "ایمیل خود را وارد کنید",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done,
            onImeAction = {
                viewModel.onEmailSubmit()
            })

        SpacerHeight(25)

        Text("تاریخ تولد :")
        SpacerHeight(10)
        AppTextField(
            value = viewModel1.data,
            onValueChange = viewModel1::onDataChange,
            placeholder = "تاریخ تولد خود را وارد کنید",
            imeAction = ImeAction.Done,
            onImeAction = {}
        )

        SpacerHeight(15)

        Text("جنسیت :")
        SpacerHeight(10)
        RadioButton()
    }
}