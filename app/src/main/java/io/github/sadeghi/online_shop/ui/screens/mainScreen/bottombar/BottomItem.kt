package io.github.sadeghi.online_shop.ui.screens.mainScreen.bottombar

import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.navigation.Screens

data class BottomItem(
    val title: String,
    val icon: Int,
    val route: String
)

val bottomItems = listOf(
    BottomItem(
        title = "پروفایل",
        icon = R.drawable.userpctagon,
        route = Screens.Profile.route
    ),
    BottomItem(
        title = "سبد خرید",
        icon = R.drawable.shoppingbag,
        route = Screens.Cart.route
    ),
    BottomItem(
        title = "دسته‌بندی",
        icon = R.drawable.category,
        route = Screens.Category.route
    ),
    BottomItem(
        title = "خانه",
        icon = R.drawable.home,
        route = Screens.Home.route
    )
)