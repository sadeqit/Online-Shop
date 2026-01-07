package io.github.sadeghi.online_shop.ui.screens.loginscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.ui.component.BGShape


@Composable
fun LoginScreen(navController: NavController) {

    val state = remember { mutableStateOf("") }
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFFE593E),Color(0xFFE02508))
    )

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BGShape()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp,
                        end = 16.dp,
                        bottom = 80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                /*Image(
                    painter = painterResource(R.drawable.logo1),
                    contentDescription = "logo",
                    modifier = Modifier.size(80.dp),
                    )
                Image(
                    painter = painterResource(R.drawable.typographi1),
                    contentDescription = "logo",
                    modifier = Modifier.size(80.dp),
                    )*/
                Image(
                    painter = painterResource(R.drawable.icc),
                    contentDescription = "logo",
                 )
                Text(
                    text = buildAnnotatedString {
                        append("فروشگاه اینترنتی ") // متن معمولی
                        withStyle(style = SpanStyle(color = Color(0xFFEF472C))) { // متن قرمز
                            append("آنلاین شاپ")
                        }
                    },
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(50.dp))

                Text("جهت ورود به فروشگاه اینترنتی آنلاین شاپ ایمیل خود را در کادر زیر وارد کرده و کد ارسالی به ایمیل خود را در مرحله بعد وارد کنید.")

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "ایمیل:",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Right
                )
                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = state.value,
                    onValueChange = { state.value = it },
                    placeholder = { Text(text = "ایمیل خود را وارد کنید") },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedPlaceholderColor = Color(0XFFA1A0A0),
                        disabledPlaceholderColor = Color(0XFFA1A0A0),
                        errorPlaceholderColor = Color(0XFFA1A0A0),
                        unfocusedPlaceholderColor = Color(0XFFA1A0A0)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = gradient,
                            shape = RoundedCornerShape(16.dp)
                        ), // اضافه کردن Brush
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent, // حذف رنگ پیش‌فرض
                        contentColor = Color.White
                    )
                ) {
                    Text("تائید و ادامه")
                }


            }

        }
    }
}

