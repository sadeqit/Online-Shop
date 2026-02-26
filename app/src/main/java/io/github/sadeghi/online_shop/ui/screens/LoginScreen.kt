package io.github.sadeghi.online_shop.ui.screens

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
import io.github.sadeghi.online_shop.ui.component.BGShape
import io.github.sadeghi.online_shop.ui.screens.loginscreen.ConfirmCodeContent
import io.github.sadeghi.online_shop.ui.screens.loginscreen.EnterEmailContent
import io.github.sadeghi.online_shop.ui.screens.loginscreen.LoadingOverlay
import io.github.sadeghi.online_shop.ui.screens.loginscreen.LoginStep
import io.github.sadeghi.online_shop.ui.screens.loginscreen.SetupContent
import io.github.sadeghi.online_shop.ui.screens.loginscreen.SignInContent
import io.github.sadeghi.online_shop.ui.screens.loginscreen.SubmitInfoContent
import io.github.sadeghi.online_shop.ui.screens.loginscreen.SubmitPassword
import io.github.sadeghi.online_shop.viewModel.LoginViewModel


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

            BGShape()

            when (viewModel.step) {

                LoginStep.SETUP -> SetupContent(
                    onRegisterClick = { viewModel.goToRegister() },
                    onSignInClick = { viewModel.goToSignIn() }
                )

                LoginStep.SIGN_IN -> SignInContent(
                    email = viewModel.email,
                    password = viewModel.password,
                    onEmailChange = { viewModel.onEmailChange(it) },
                    onPasswordChange = { viewModel.onPasswordChange(it) },
                    onLoginClick = {
                        viewModel.signIn {
                            navController.navigate(Screens.Home.route) {
                                popUpTo(Screens.Login.route) { inclusive = true }
                            }
                        }
                    },
                    focusManager = focusManager
                )


                LoginStep.ENTER_EMAIL -> EnterEmailContent(
                    viewModel = viewModel,
                    focusManager = focusManager
                )

                LoginStep.CONFIRM_CODE -> ConfirmCodeContent(
                    viewModel = viewModel,
                    focusManager = focusManager
                )


                LoginStep.SET_PASSWORD -> SubmitPassword(
                    password = viewModel.password,
                    confirmPassword = viewModel.confirmPassword,
                    onPasswordChange = viewModel::onPasswordChange,
                    onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
                    passwordError = viewModel.passwordError,
                    onSubmitClick = viewModel::submitPassword,
                    passwordStrength = viewModel.passwordStrength,
                    focusManager = focusManager
                )

                LoginStep.SUBMIT_INFO -> SubmitInfoContent(
                    viewModel, focusManager, onNavigateToHome = {
                        navController.navigate(Screens.Home.route) {
                            popUpTo(Screens.Login.route) { inclusive = true }
                        }
                    })

            }

            if (viewModel.isLoading) {
                LoadingOverlay()
            }

        }
    }

}

