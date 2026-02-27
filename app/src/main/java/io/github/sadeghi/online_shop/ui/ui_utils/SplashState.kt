package io.github.sadeghi.online_shop.ui.ui_utils

sealed class SplashState {

    data object Loading : SplashState()

    data object NoInternet : SplashState()

    data object InternetConnected : SplashState()

    data object NavigateToHome : SplashState()

    data object NavigateToAuth : SplashState()
}