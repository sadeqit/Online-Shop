package io.github.sadeghi.online_shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.sadeghi.online_shop.ui.screens.productScreen.screen.ProductDetailScreen
import io.github.sadeghi.online_shop.ui.screens.ProfileScreen
import io.github.sadeghi.online_shop.ui.screens.categoryScreen.CategoryProductScreen
import io.github.sadeghi.online_shop.ui.screens.categoryScreen.CategoryScreen
import io.github.sadeghi.online_shop.ui.screens.categoryScreen.SubCategoryProductScreen
import io.github.sadeghi.online_shop.ui.screens.homeScreen.HomeScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.bottombar.CartScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.AboutUsScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.ContactUsScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.OrdersScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.RulesScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.SupportScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.NotificationsScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.AddressesScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.ChangePasswordScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.EditProfileScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.FavoritesScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.MyBuyScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.MyOrdersScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.notif.NotificationsViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
    notificationsViewModel: NotificationsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {

        composable(Screens.Home.route) {
            HomeScreen(
                navController = navController,
                onCategoryClick = { categoryId ->
                    navController.navigate(
                        Screens.CategoryProduct.createRoute(categoryId)) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Screens.Profile.route) {
            ProfileScreen(
                navController = navController
            )
        }

        composable(Screens.Notifications.route) {
            NotificationsScreen(
                viewModel = notificationsViewModel
            )
        }

        composable(Screens.Cart.route) {
            CartScreen()
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


        composable(Screens.Category.route) {
            CategoryScreen(
                onCategoryClick = { categoryId ->
                    navController.navigate(
                        Screens.CategoryProduct.createRoute(categoryId)
                    ) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(
            route = Screens.CategoryProduct.route,
            arguments = listOf(
                navArgument("categoryId") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0

            CategoryProductScreen(
                categoryId = categoryId,
                navController = navController
            )
        }

        composable(
            route = Screens.SubCategoryProduct.route,
            arguments = listOf(
                navArgument("subCategoryId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val subCategoryId =
                backStackEntry.arguments?.getInt("subCategoryId") ?: 0

            SubCategoryProductScreen(
                subCategoryId = subCategoryId,
                navController = navController
            )
        }

        composable(
            route = Screens.ProductDetail.route,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val productId =
                backStackEntry.arguments?.getInt("productId") ?: return@composable

            ProductDetailScreen(
                productId = productId,
                navController = navController
            )
        }

        composable(Screens.MyOrders.route) {
            MyOrdersScreen()
        }
        composable(Screens.MyBuy.route) {
            MyBuyScreen()
        }

        composable(Screens.ChangePassword.route) {
            ChangePasswordScreen()
        }


        composable(Screens.Addresses.route) {
            AddressesScreen()
        }

        composable(Screens.Favorites.route) {
            FavoritesScreen(
                navController = navController

            )
        }

        composable(Screens.EditProfile.route) {
            EditProfileScreen(
            )
        }



    }
}