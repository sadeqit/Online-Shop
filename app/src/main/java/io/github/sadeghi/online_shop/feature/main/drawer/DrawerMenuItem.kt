package io.github.sadeghi.online_shop.feature.main.drawer

import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.navigation.Screens

data class DrawerMenuItem(
    val title: String,
    val icon: Int,
    val route: String
)

val drawerItems = listOf(
    DrawerMenuItem(
        "پروفایل کاربری",
        R.drawable.userpctagon,
        Screens.Profile.route
    ),
    DrawerMenuItem(
        "سفارشات من",
        R.drawable.order_dr,
        Screens.Orders.route
    ),
    DrawerMenuItem(
        "پشتیبانی",
        R.drawable.support_dr,
        Screens.Support.route
    ),
    DrawerMenuItem(
        "قوانین و مقررات",
        R.drawable.low_dr,
        Screens.Rules.route
    ),
    DrawerMenuItem(
        "درباره ما",
        R.drawable.about_dr,
        Screens.About.route
    ),
    DrawerMenuItem(
        "ارتباط با ما",
        R.drawable.contact_dr,
        Screens.ContactUs.route
    )
)