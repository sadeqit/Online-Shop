package io.github.sadeghi.online_shop.navigation

sealed class Screens(val route: String) {
    data object Splash : Screens("splash")
    data object Login : Screens("login")
    data object Home : Screens("home")

    data object Main : Screens("main")
    data object Profile : Screens("profile")

    data object Favorites : Screens("favorites")
    object Notifications : Screens("notifications")

    object Category : Screens("category")

    object Cart : Screens("cart")
    object Orders : Screens("orders")
    object Support : Screens("support")
    object Rules : Screens("rules")
    object About : Screens("about")
    object ContactUs : Screens("contactus")
}