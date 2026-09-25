package io.github.sadeghi.online_shop.navigation

import android.net.Uri
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.sadeghi.online_shop.feature.cart.CartScreen
import io.github.sadeghi.online_shop.feature.profile.ProfileScreen
import io.github.sadeghi.online_shop.feature.cart.model.CartStep
import io.github.sadeghi.online_shop.feature.category.CategoryProductScreen
import io.github.sadeghi.online_shop.feature.category.CategoryScreen
import io.github.sadeghi.online_shop.feature.category.SubCategoryProductScreen
import io.github.sadeghi.online_shop.feature.home.HomeScreen
import io.github.sadeghi.online_shop.feature.home.SearchResultScreen
import io.github.sadeghi.online_shop.feature.main.drawerScreen.AboutUsScreen
import io.github.sadeghi.online_shop.feature.main.drawerScreen.ContactUsScreen
import io.github.sadeghi.online_shop.feature.main.drawerScreen.RulesScreen
import io.github.sadeghi.online_shop.feature.main.drawerScreen.SupportScreen
import io.github.sadeghi.online_shop.feature.product.ProductDetailScreen
import io.github.sadeghi.online_shop.feature.address.AddressesScreen
import io.github.sadeghi.online_shop.feature.profile.component.ChangePasswordScreen
import io.github.sadeghi.online_shop.feature.profile.EditProfileScreen
import io.github.sadeghi.online_shop.feature.favorites.FavoritesScreen
import io.github.sadeghi.online_shop.feature.orders.MyBuyScreen
import io.github.sadeghi.online_shop.feature.orders.MyOrdersScreen
import io.github.sadeghi.online_shop.feature.notifications.NotificationsScreen
import io.github.sadeghi.online_shop.feature.address.AddressFormScreen
import io.github.sadeghi.online_shop.feature.notifications.NotificationsViewModel
import io.github.sadeghi.online_shop.feature.profile.ProfileViewModel
import java.util.UUID

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
                onStepChange = onCartStepChange,
                notificationsViewModel = notificationsViewModel
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
                ?.let { UUID.fromString(it) }

            AddressFormScreen(
                navController = navController,
                addressId = addressId,
                profileViewModel = profileViewModel
            )
        }


    }
}
    }