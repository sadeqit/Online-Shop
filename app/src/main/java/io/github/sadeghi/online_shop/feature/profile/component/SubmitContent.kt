package io.github.sadeghi.online_shop.feature.profile.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.alirezajavan.shamsipicker.calendar.CalendarType
import io.github.alirezajavan.shamsipicker.format.DateFormatter
import io.github.alirezajavan.shamsipicker.model.ShamsiDate
import io.github.alirezajavan.shamsipicker.model.ShamsiDatePickerConfig
import io.github.alirezajavan.shamsipicker.model.ShamsiDatePickerStyle
import io.github.alirezajavan.shamsipicker.ui.ShamsiDatePickerDialog
import io.github.sadeghi.online_shop.core.ui.component.AppTextField
import io.github.sadeghi.online_shop.core.ui.SpacerHeight
import io.github.sadeghi.online_shop.feature.profile.ProfileViewModel

@Composable
fun SubmitContent(
    viewModel: ProfileViewModel,
    focusManager: FocusManager
) {

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var selectedDate by remember {
        mutableStateOf(ShamsiDate.Now)
    }

    if (showDatePicker) {
        ShamsiDatePickerDialog(
            onConfirm = { date ->
                selectedDate = date

                viewModel.onBirthDateChange(
                    DateFormatter.short(
                        date,
                        CalendarType.Shamsi
                    )
                )

                showDatePicker = false
            },
            onDismiss = {
                showDatePicker = false
            },
            config = ShamsiDatePickerConfig(
                initialDate = selectedDate,
                style = ShamsiDatePickerStyle.Wheel,
                calendarType = CalendarType.Shamsi
            )
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "نام و نام خانوادگی:*",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right
        )

        SpacerHeight(10)
        AppTextField(
            value = viewModel.fullName,
            onValueChange = viewModel::onFullNameChange,
            placeholder = "نام و نام خانوادگی خود را وارد کنید",
            imeAction = ImeAction.Next,
            onImeAction = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )

        SpacerHeight(25)

        Text(
            text = "شماره همراه:*",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right
        )
        SpacerHeight(10)
        AppTextField(
            value = viewModel.phoneNumber,
            onValueChange = {
                if (it.length <= 11) {
                    viewModel.onPhoneNumberChange(it)
                }
            },
            placeholder = "*********09",
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next,
            isError = viewModel.phoneNumberError != null,
            supportingText = viewModel.phoneNumberError,
            onImeAction = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )

        SpacerHeight(25)
        Text(
            text = "ایمیل :",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right
        )
        SpacerHeight(10)
        AppTextField(
            value = viewModel.email,
            onValueChange = {},
            placeholder = "ایمیل خود را وارد کنید",
            keyboardType = KeyboardType.Email,
            readOnly = true,
            imeAction = ImeAction.Done,
            onImeAction = {}
        )

        SpacerHeight(25)
        Text(
            text = "تاریخ تولد :",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right
        )
        SpacerHeight(10)
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            AppTextField(
                value = viewModel.birthDate,
                onValueChange = viewModel::onBirthDateChange,
                placeholder = "تاریخ تولد خود را وارد کنید",
                readOnly = true,
                isError = viewModel.birthDateError != null,
                supportingText = viewModel.birthDateError,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "انتخاب تاریخ",
                        tint = Color.Black
                    )
                }
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) {
                        showDatePicker = true
                    }
            )
        }


        SpacerHeight(15)

        Text(
            text = "جنسیت :",
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right
        )
        SpacerHeight(10)
        RadioButton(
            gender = viewModel.gender,
            onGenderChange = viewModel::onGenderChange,
            hasError = viewModel.genderError != null
        )

        if (viewModel.genderError != null) {
            Text(
                text = viewModel.genderError!!,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
            )
        }
    }
}