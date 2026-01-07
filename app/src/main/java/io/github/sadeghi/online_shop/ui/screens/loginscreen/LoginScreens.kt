package io.github.sadeghi.online_shop.ui.screens.loginscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.sadeghi.online_shop.ui.component.BGShape
import io.github.sadeghi.online_shop.viewModel.LoginViewModel

@Composable
fun LoginScreens(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Box(modifier = Modifier.fillMaxSize().clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            focusManager.clearFocus()
        }) {
            BGShape()

            when (viewModel.step) {
                LoginStep.ENTER_EMAIL -> EnterEmailContent(
                    viewModel = viewModel,
                    focusManager = focusManager
                )
                LoginStep.CONFIRM_CODE -> ConfirmCodeContent(
                    viewModel = viewModel,
                    focusManager = focusManager
                )
                LoginStep.LOADING ->  LoadingOverlay( )

                LoginStep.SUBMIT_INFO -> SubmitInfoContent()

            }
        }
    }
}
