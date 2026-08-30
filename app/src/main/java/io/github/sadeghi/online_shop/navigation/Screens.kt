package io.github.sadeghi.online_shop.navigation

sealed class Screens(val route: String) {
    data object Splash : Screens("splash")
    data object Login : Screens("login")
    data object Home : Screens("home")

    data object Main : Screens("main")
    data object Profile : Screens("profile")

    data object Favorites : Screens("favorites")
    object Notifications : Screens("notifications")


    object Cart : Screens("cart")
    object Orders : Screens("orders")
    object Support : Screens("support")
    object Rules : Screens("rules")
    object About : Screens("about")
    object ContactUs : Screens("contactus")

    object Category : Screens("category")


    object CategoryProduct : Screens("category_product/{categoryId}") {
        fun createRoute(categoryId: Int) = "category_product/$categoryId"
    }

    object SubCategoryProduct : Screens("sub_category_product/{subCategoryId}") {
        fun createRoute(subCategoryId: Int) = "sub_category_product/$subCategoryId"
    }
}