package io.github.sadeghi.online_shop.ui.screens.loginscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.ui.component.AppTextField
import io.github.sadeghi.online_shop.ui.component.GradientButton
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.viewModel.LoginViewModel

@Composable
fun SubmitInfoContent(
    viewModel: LoginViewModel,
    focusManager: FocusManager,
    onNavigateToHome: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoHeader()

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
            value = viewModel.fullName,
            onValueChange = viewModel::onFullNameChange,
            placeholder = "نام و نام خانوادگی خود را وارد کنید",
            imeAction = ImeAction.Done,
            onImeAction = {
                focusManager.clearFocus()
            }
        )

        viewModel.errorMessage?.let { message ->
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
            enabled = viewModel.fullName.isNotBlank() && !viewModel.isLoading && viewModel.isInternetAvailable,
            onClick = {
                focusManager.clearFocus()
                viewModel.submitFullName {

                    onNavigateToHome()
                }
            }
        )

        SpacerHeight(20)

        Text(
            text = "عبور از این مرحله",
            modifier = Modifier
                .clickable {
                    focusManager.clearFocus()
                    viewModel.onSubmitInfo()

                    // viewModel.saveFullNameAndProceed()

                    onNavigateToHome()
                }.align(Alignment.Start),
            style = MaterialTheme.typography.titleMedium
        )

    }

}
