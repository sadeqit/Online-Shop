package io.github.sadeghi.online_shop.feature.profile.component

import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.navigation.Screens

data class ProfileCard(
    val image: Int,
    val title: String,
    val route: String
)

val profileCards = listOf(
    ProfileCard(
        image = R.drawable.orders,
        title = "سفارش‌ های من",
        route = Screens.MyOrders.route
    ),
    ProfileCard(
        image = R.drawable.mybuy,
        title = "تجربه های خرید من",
        route = Screens.MyBuy.route
    ),
    ProfileCard(
        image = R.drawable.password,
        title = "تغییر رمز عبور",
        route = Screens.ChangePassword.route
    ),
    ProfileCard(
        image = R.drawable.notification,
        title = "اعلانات من",
        route = Screens.Notifications.route
    ),
    ProfileCard(
        image = R.drawable.bookmark,
        title = "علاقه مندی های من",
        route = Screens.Favorites.route
    ),
    ProfileCard(
        image = R.drawable.address,
        title = "آدرس های من",
        route = Screens.Addresses.route
    )
)