package io.github.sadeghi.online_shop.utils

sealed class NetworkState {
    data object Checking : NetworkState()
    data object Connected : NetworkState()
    data object Disconnected : NetworkState()
}