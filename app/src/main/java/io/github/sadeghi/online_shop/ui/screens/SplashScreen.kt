package io.github.sadeghi.online_shop.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.component.BGShape
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.theme.greeny
import io.github.sadeghi.online_shop.ui.ui_utils.SplashState
import io.github.sadeghi.online_shop.viewModel.SplashViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val splashState by viewModel.splashState.collectAsState()


    LaunchedEffect(splashState) {
        when (splashState) {
            SplashState.NavigateToHome -> {
                navController.navigate(Screens.Home.route) {
                    popUpTo(Screens.Splash.route) { inclusive = true }
                }
            }

            SplashState.NavigateToAuth -> {
                navController.navigate(Screens.Login.route) {
                    popUpTo(Screens.Splash.route) { inclusive = true }
                }
            }

            else -> Unit
        }
    }

    val showStatus = remember { mutableStateOf(false) }

    // گرفتن ارتفاع صفحه به پیکسل
    val density = LocalDensity.current
    val screenHeightPx = with(density) {
        LocalConfiguration.current.screenHeightDp.dp.roundToPx()
    }

    // کنترل شروع انیمیشن
    var startAnimation by remember { mutableStateOf(false) }

    // آفست متحرک عکس (از بالا)
    val logoOffset by animateIntAsState(
        targetValue = if (startAnimation) 0 else -screenHeightPx,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "logoAnimation"
    )

    // آفست متحرک متن (از پایین)
    val textOffset by animateIntAsState(
        targetValue = if (startAnimation) 0 else screenHeightPx,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "textAnimation"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BGShape()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            // 🔵 عکس
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .offset { IntOffset(0, logoOffset) }
                    .size(150.dp)
            )

            SpacerHeight(16)

            // 🔴 متن
            Text(
                text = buildAnnotatedString {
                    append("فروشگاه اینترنتی ")
                    withStyle(style = SpanStyle(color = Color(0xFFEF472C))) {
                        append("آنلاین شاپ")
                    }
                },
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.offset { IntOffset(0, textOffset) }
            )

        }


        LaunchedEffect(Unit) {
            startAnimation = true
            delay(1200)
            showStatus.value = true
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 150.dp),
            contentAlignment = Alignment.BottomCenter,
        )
        {
            if (showStatus.value) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    when (splashState) {

                        SplashState.Loading -> {
                            Text(
                                text = "...درحال بررسی اینترنت",
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium
                            )
                            SpacerHeight(20)

                            CircularProgressIndicator()
                        }

                        SplashState.NoInternet -> {
                            Text(
                                text = "!اینترنت شما متصل نیست",
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium
                            )
                            SpacerHeight(30)
                            Button(onClick = { viewModel.retry() }) {
                                Text("تلاش مجدد")
                            }
                        }

                        SplashState.InternetConnected -> {
                            Text(
                                text = "اینترنت وصل است",
                                textAlign = TextAlign.Center,
                                color = greeny,
                                style = MaterialTheme.typography.titleMedium

                            )
                        }

                        else -> Unit
                    }


                }
            }

        }

    }
}
