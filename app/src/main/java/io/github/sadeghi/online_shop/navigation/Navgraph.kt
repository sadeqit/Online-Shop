package io.github.sadeghi.online_shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
 import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.sadeghi.online_shop.ui.screens.HomeScreen
import io.github.sadeghi.online_shop.ui.screens.loginscreen.LoginScreen
import io.github.sadeghi.online_shop.ui.screens.SplashScreen
import io.github.sadeghi.online_shop.ui.screens.loginscreen.LoginScreens


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ){
        composable(Screens.Splash.route){
            SplashScreen(navController)
        }
        composable(Screens.Login.route){
            LoginScreens()
        }
        composable(Screens.Home.route){
            HomeScreen(navController)
        }

    }
}