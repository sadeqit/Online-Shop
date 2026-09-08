package io.github.sadeghi.online_shop.ui.screens.profilescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import io.github.sadeghi.online_shop.ui.component.GradientButton
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.HeaderProfile
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.SubmitContent
import io.github.sadeghi.online_shop.viewModel.LoginViewModel
import io.github.sadeghi.online_shop.viewModel.ProfileViewModel

@Composable
fun EditProfileScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    viewModel1: ProfileViewModel = hiltViewModel(),
) {
    val focusManager = LocalFocusManager.current

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { focusManager.clearFocus() }
        ) {
            // ✅ هدر ثابت در بالا
            HeaderProfile(
                iconEdit = false
            )

            // ✅ بقیه محتوا با اسکرول
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                SpacerHeight(25)
                SubmitContent(viewModel, viewModel1)
                SpacerHeight(15)
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    GradientButton(
                        text = "ثبت تغییرات",
                        enabled = true
                    ) {}
                }
                SpacerHeight(25)
            }
        }
    }
}