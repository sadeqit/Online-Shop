package io.github.sadeghi.online_shop.navigation

import android.net.Uri
import com.google.gson.Gson

sealed class Screens(val route: String) {
    data object Splash : Screens("splash")

    data object Login : Screens("login")

    data object Home : Screens("home")

    data object Profile : Screens("profile")


    data object Favorites : Screens("favorites")
}