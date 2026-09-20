package io.github.sadeghi.online_shop.navigation

import android.net.Uri
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.sadeghi.online_shop.ui.screens.CartScreen
import io.github.sadeghi.online_shop.ui.screens.ProfileScreen
import io.github.sadeghi.online_shop.ui.screens.cartScreen.CartStep
import io.github.sadeghi.online_shop.ui.screens.categoryScreen.CategoryProductScreen
import io.github.sadeghi.online_shop.ui.screens.categoryScreen.CategoryScreen
import io.github.sadeghi.online_shop.ui.screens.categoryScreen.SubCategoryProductScreen
import io.github.sadeghi.online_shop.ui.screens.homeScreen.HomeScreen
import io.github.sadeghi.online_shop.ui.screens.homeScreen.SearchResultScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.AboutUsScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.ContactUsScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.RulesScreen
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawerScreen.SupportScreen
import io.github.sadeghi.online_shop.ui.screens.productScreen.screen.ProductDetailScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.AddressesScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.ChangePasswordScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.EditProfileScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.FavoritesScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.MyBuyScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.MyOrdersScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.NotificationsScreen
import io.github.sadeghi.online_shop.ui.screens.profilescreen.address.AddressFormScreen
import io.github.sadeghi.online_shop.viewModel.NotificationsViewModel
import io.github.sadeghi.online_shop.viewModel.ProfileViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
    notificationsViewModel: NotificationsViewModel,
    onLogout: () -> Unit,
    profileViewModel: ProfileViewModel,
    cartStep: CartStep,
    onCartStepChange: (CartStep) -> Unit
) {
    SharedTransitionLayout {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {

        composable(Screens.Home.route) {
            HomeScreen(
                navController = navController,
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this,
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
            route = Screens.SearchResult.route
        ) { backStackEntry ->

            val query = backStackEntry.arguments
                ?.getString("query")
                ?.let { Uri.decode(it) }
                ?: ""

            SearchResultScreen(
                query = query,
                navController = navController,
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this
            )
        }


        composable(Screens.Profile.route) {
            ProfileScreen(
                navController = navController,
                profileViewModel = profileViewModel,
                onLogout = onLogout
            )
        }

        composable(Screens.Notifications.route) {
            NotificationsScreen(
                viewModel = notificationsViewModel,
                profileViewModel = profileViewModel
            )
        }

        composable(Screens.Cart.route) {
            CartScreen(
                navController = navController,
                currentStep = cartStep,
                onStepChange = onCartStepChange
            )
        }


        composable(Screens.Orders.route) {
            MyOrdersScreen(
                profileViewModel = profileViewModel)
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

            val categoryId =
                backStackEntry.arguments?.getInt("categoryId") ?: 0

            CategoryProductScreen(
                categoryId = categoryId,
                navController = navController,
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this
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
                navController = navController,
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this
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
                backStackEntry.arguments?.getInt("productId")
                    ?: return@composable

            ProductDetailScreen(
                productId = productId,
                navController = navController,
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this
            )
        }

        composable(Screens.MyOrders.route) {
            MyOrdersScreen(
                profileViewModel = profileViewModel)
        }
        composable(Screens.MyBuy.route) {
            MyBuyScreen(navController = navController,
                profileViewModel = profileViewModel)
        }

        composable(Screens.ChangePassword.route) {
            ChangePasswordScreen(
                onSuccess = {
                    navController.popBackStack()
                },
                profileViewModel = profileViewModel
            )
        }


        composable(Screens.Addresses.route) {
            AddressesScreen(navController,profileViewModel)
        }

        composable(Screens.Favorites.route) {
            FavoritesScreen(
                navController = navController,
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this,
                profileViewModel = profileViewModel
            )
        }

        composable(Screens.EditProfile.route) {
            EditProfileScreen(
                onSaveSuccess = {
                    navController.popBackStack()
                },
                profileViewModel = profileViewModel
            )
        }
        composable(Screens.AddressFormScreen.route) {
            AddressFormScreen(
                navController = navController,
                addressId = null,
                profileViewModel = profileViewModel
            )
        }
        composable(
            route = "${Screens.AddressFormScreen.route}/{addressId}"
        ) { backStackEntry ->

            val addressId = backStackEntry
                .arguments
                ?.getString("addressId")
                ?.toIntOrNull()

            AddressFormScreen(
                navController = navController,
                addressId = addressId,
                profileViewModel = profileViewModel
            )
        }


    }
}
    }