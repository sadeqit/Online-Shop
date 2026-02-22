package io.github.sadeghi.online_shop.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.component.BGShape
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.theme.greeny
import io.github.sadeghi.online_shop.viewModel.SplashViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val isConnected by viewModel.isConnected.collectAsState()

    val showLogo = remember { mutableStateOf(false) }
    val showTypography = remember { mutableStateOf(false) }
    val showStatus = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

        showLogo.value = true
        showTypography.value = true
        delay(1000)
        showStatus.value = true
    }


    Box(modifier = Modifier.fillMaxSize()) {
        BGShape()
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            AnimatedVisibility(
                visible = showLogo.value,
                enter = slideInVertically(
                    initialOffsetY = { -it },
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(
                    animationSpec = tween(600)
                )
            ) {

                Image(
                    painter = painterResource(R.drawable.logo1),
                    contentDescription = "logo",
                    modifier = Modifier.size(150.dp)
                )
            }

            AnimatedVisibility(
                visible = showTypography.value,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(
                    animationSpec = tween(600)
                )
            ) {
                Image(
                    painter = painterResource(R.drawable.typographi1),
                    contentDescription = "typography",
                    modifier = Modifier.size(150.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 130.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            if (showStatus.value) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (isConnected) {
                        null -> {
                            Text(
                                text = "...درحال بررسی اینترنت",
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium
                            )
                            SpacerHeight(20)
                            CircularProgressIndicator()

                        }

                        false -> {
                            Text(
                                text = "!اینترنت شما متصل نیست",
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium
                            )
                            SpacerHeight(50)

                            Button(onClick = { viewModel.checkInternet() })
                            {
                                Text("تلاش مجدد")
                            }

                        }

                        else -> {
                            Text(
                                text = "اینترنت وصل است",
                                textAlign = TextAlign.Center,
                                color = greeny,
                                style = MaterialTheme.typography.titleMedium

                            )

                            LaunchedEffect(Unit) {
                                delay(1000)
                                navController.navigate(Screens.Login.route) {
                                    popUpTo(Screens.Splash.route) {
                                        inclusive = true
                                    }
                                }
                            }

                        }

                    }
                }
            }


        }
    }
}