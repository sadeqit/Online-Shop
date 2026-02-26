package io.github.sadeghi.online_shop.ui.screens.loginscreen

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.ui.component.GradientButton
import io.github.sadeghi.online_shop.ui.component.SpacerHeight


@Composable
fun SetupContent(
    onRegisterClick: () -> Unit,
    onSignInClick: () -> Unit
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
                .padding(start = 16.dp, end = 16.dp, bottom = 110.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "به آنلاین شاپ خوش آمدید!",
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge
            )


            SpacerHeight(100)

            Text(
                text = "در صورت دارا بودن حساب کاربری، وارد شوید؛\nدر غیر این صورت ثبت نام نمایید.",
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 16.dp),
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.bodyMedium
            )
            SpacerHeight(40)

            GradientButton(
                text = "ورود",
                onClick = onSignInClick
            )
            SpacerHeight(20)

            GradientButton(
                text = "ثبت نام",
                onClick = onRegisterClick
            )
        }

    }
}