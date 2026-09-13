package io.github.sadeghi.online_shop.ui.screens.profilescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import io.github.sadeghi.online_shop.ui.component.AppTextField
import io.github.sadeghi.online_shop.ui.component.GradientButton
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.HeaderProfile
import io.github.sadeghi.online_shop.viewModel.ChangePasswordViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun ChangePasswordScreen(
    viewModel: ChangePasswordViewModel = hiltViewModel(),
    onSuccess: () -> Unit
) {

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    var currentPasswordVisible by remember { mutableStateOf(false) }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }


    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Ltr
            ) {
                HeaderProfile(true)
            }

            SpacerHeight(40)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {

                Text(
                    text = "تغییر رمز",
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)

                ) {

                    SpacerHeight(20)
                    Text(
                        text = "در انتخاب رمز عبور موارد زیر را در نظر بگیرید:\n" +
                                "رمز عبور باید خداقل 8 کارکتر باشد\n" +
                                "شامل حروف و عدد باشد\n" +
                                "شامل علامت باشد(!@#\$%)\n" +
                                "از حروف بزرگ و کوچک استفاده شود.",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Right
                    )
                    SpacerHeight(20)

                    Text(
                        text = buildAnnotatedString {
                            append("رمز عبور فعلی:")
                            withStyle(
                                SpanStyle(color = Color.Red)
                            ) {
                                append("*")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Right
                    )
                    SpacerHeight(10)

                    AppTextField(
                        value = viewModel.currentPassword,
                        onValueChange = viewModel::onCurrentPasswordChange,
                        placeholder = "رمز عبور فعلی خود را وارد کنید",
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                        visualTransformation =
                            if (currentPasswordVisible)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),
                        isError = viewModel.currentPasswordError != null,
                        supportingText = viewModel.currentPasswordError,
                        onImeAction = {
                            focusManager.moveFocus(FocusDirection.Down)
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    currentPasswordVisible = !currentPasswordVisible
                                }
                            ) {
                                Icon(
                                    imageVector =
                                        if (currentPasswordVisible)
                                            Icons.Outlined.Visibility
                                        else
                                            Icons.Outlined.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                    SpacerHeight(25)
                    Text(
                        text = buildAnnotatedString {
                            append("رمز عبور جدید:")
                            withStyle(
                                SpanStyle(color = Color.Red)
                            ) {
                                append("*")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Right
                    )
                    SpacerHeight(10)
                    AppTextField(
                        value = viewModel.newPassword,
                        onValueChange = viewModel::onNewPasswordChange,
                        placeholder = "رمز عبور جدید خود را وارد کنید",
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                        visualTransformation =
                            if (newPasswordVisible)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),
                        isError = viewModel.newPasswordError != null,
                        supportingText = viewModel.newPasswordError,
                        onImeAction = {
                            focusManager.moveFocus(FocusDirection.Down)
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    newPasswordVisible = !newPasswordVisible
                                }
                            ) {
                                Icon(
                                    imageVector =
                                        if (newPasswordVisible)
                                            Icons.Outlined.Visibility
                                        else
                                            Icons.Outlined.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                    SpacerHeight(25)
                    Text(
                        text = buildAnnotatedString {
                            append("تکرار رمز عبور جدید:")
                            withStyle(
                                SpanStyle(color = Color.Red)
                            ) {
                                append("*")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Right
                    )
                    SpacerHeight(10)
                    AppTextField(
                        value = viewModel.confirmPassword,
                        onValueChange = viewModel::onConfirmPasswordChange,
                        placeholder = "تکرار رمز عبور جدید خود را وارد کنید",
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                        visualTransformation =
                            if (confirmPasswordVisible)
                                VisualTransformation.None
                            else
                                PasswordVisualTransformation(),
                        isError = viewModel.confirmPasswordError != null,
                        supportingText = viewModel.confirmPasswordError,
                        onImeAction = {
                            focusManager.clearFocus()

                            scope.launch {
                                delay(100.milliseconds)

                                scrollState.animateScrollTo(
                                    scrollState.maxValue
                                )
                            }
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    confirmPasswordVisible = !confirmPasswordVisible
                                }
                            ) {
                                Icon(
                                    imageVector =
                                        if (confirmPasswordVisible)
                                            Icons.Outlined.Visibility
                                        else
                                            Icons.Outlined.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                    SpacerHeight(30)

                    // دکمه تغییر رمز
                    GradientButton(
                        text = "تغییر رمز",
                        enabled = !viewModel.isLoading,
                        onClick = {
                            viewModel.changePassword(
                                onSuccess = onSuccess
                            )
                        }
                    )
                    SpacerHeight(50)
                }
            }
        }
    }
}
