package io.github.sadeghi.online_shop.feature.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.feature.auth.component.ConfirmCodeContent
import io.github.sadeghi.online_shop.feature.auth.component.EnterEmailContent
import io.github.sadeghi.online_shop.feature.auth.component.LoadingOverlay
import io.github.sadeghi.online_shop.feature.auth.model.LoginStep
import io.github.sadeghi.online_shop.feature.auth.component.SetPasswordContent
import io.github.sadeghi.online_shop.feature.auth.component.SetupContent
import io.github.sadeghi.online_shop.feature.auth.component.SignInContent
import io.github.sadeghi.online_shop.feature.auth.component.SubmitUserInfoContent


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val focusManager = LocalFocusManager.current

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    focusManager.clearFocus()
                }) {

            val navigateToHome: () -> Unit = {
                navController.navigate(Screens.Main.route) {
                    popUpTo(Screens.Login.route) {
                        inclusive = true
                    }
                }
            }

            when (viewModel.step) {

                LoginStep.SETUP -> SetupContent(
                    onRegisterClick = { viewModel.goToRegister() },
                    onSignInClick = { viewModel.goToSignIn() }
                )

                LoginStep.SIGN_IN -> SignInContent(
                    email = viewModel.email,
                    password = viewModel.password,
                    errorMessage = viewModel.errorMessage,
                    isLoading = viewModel.isLoading,
                    onEmailChange = viewModel::onEmailChange,
                    onPasswordChange = viewModel::onPasswordChange,
                    onLoginClick = {
                        viewModel.signIn {
                            navController.navigate(Screens.Main.route) {
                                popUpTo(Screens.Login.route) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    onBack = viewModel::backToSetup,
                    focusManager = focusManager
                )


                LoginStep.ENTER_EMAIL -> EnterEmailContent(
                    email = viewModel.email,
                    errorMessage = viewModel.errorMessage,
                    isLoading = viewModel.isLoading,
                    isInternetAvailable = viewModel.isInternetAvailable,
                    onEmailChange = viewModel::onEmailChange,
                    onSubmit = viewModel::onEmailSubmit,
                    onBack = viewModel::backToSetup,
                    focusManager = focusManager
                )

                LoginStep.CONFIRM_CODE -> ConfirmCodeContent(
                    email = viewModel.email,
                    code = viewModel.code,
                    timer = viewModel.timer,
                    isLoading = viewModel.isLoading,
                    errorMessage = viewModel.errorMessage,
                    onCodeChange = viewModel::onCodeChange,
                    onVerifyCode = viewModel::verifyCode,
                    onEditEmail = viewModel::editEmail,
                    onResendCode = viewModel::resendCode,
                    focusManager = focusManager
                )


                LoginStep.SET_PASSWORD -> SetPasswordContent(
                    password = viewModel.password,
                    confirmPassword = viewModel.confirmPassword,
                    onPasswordChange = viewModel::onPasswordChange,
                    onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
                    passwordError = viewModel.passwordError,
                    onSubmitClick = viewModel::submitPassword,
                    passwordStrength = viewModel.passwordStrength,
                    focusManager = focusManager
                )

                LoginStep.SUBMIT_INFO -> SubmitUserInfoContent(
                    fullName = viewModel.fullName,
                    phoneNumber = viewModel.phoneNumber,
                    errorMessage = viewModel.errorMessage,
                    isLoading = viewModel.isLoading,
                    isInternetAvailable = viewModel.isInternetAvailable,
                    onFullNameChange = viewModel::onFullNameChange,
                    onPhoneNumberChange = viewModel::onPhoneNumberChange,
                    onSubmit = {
                        viewModel.submitFullName {
                            navigateToHome()
                        }
                    },
                    onSkip = {
                        viewModel.onSubmitInfo()
                        navigateToHome()
                    },
                    focusManager = focusManager
                )
            }

            if (viewModel.isLoading) {
                LoadingOverlay()
            }

        }
    }

}

