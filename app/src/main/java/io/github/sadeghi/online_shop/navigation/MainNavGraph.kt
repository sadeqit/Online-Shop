package io.github.sadeghi.online_shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.sadeghi.online_shop.ui.screens.mainScreen.bottombar.CartScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.bottombar.CategoryScreen
import io.github.sadeghi.online_shop.ui.screens.homeScreen.HomeScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.topbar.NotificationScreen
import io.github.sadeghi.online_shop.ui.screens.ProfileScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.AboutUsScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.ContactUsScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.OrdersScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.RulesScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.SupportScreen

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {

        composable(Screens.Home.route) {
            HomeScreen()
        }

        composable(Screens.Profile.route) {
            ProfileScreen()
        }

        composable(Screens.Notifications.route) {
            NotificationScreen(

            )
        }

        composable(Screens.Cart.route) {
            CartScreen()
        }

        composable(Screens.Category.route) {
            CategoryScreen()
        }

        composable(Screens.Orders.route) {
            OrdersScreen()
        }

        composable(Screens.Support.route) {
            SupportScreen()
        }

        composable(Screens.Rules.route) {
            RulesScreen()
        }

        composable(Screens.About.route) {
            AboutUsScreen()
        }

        composable(Screens.ContactUs.route) {
            ContactUsScreen()
        }
    }
}