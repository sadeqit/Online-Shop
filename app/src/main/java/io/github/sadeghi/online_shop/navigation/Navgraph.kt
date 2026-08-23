package io.github.sadeghi.online_shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.sadeghi.online_shop.ui.screens.HomeScreen
import io.github.sadeghi.online_shop.ui.screens.LoginScreen
import io.github.sadeghi.online_shop.ui.screens.ProfileScreen
import io.github.sadeghi.online_shop.ui.screens.SplashScreen


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {

        composable(Screens.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screens.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }

        composable(Screens.Profile.route) {
            ProfileScreen()
        }

    }
}