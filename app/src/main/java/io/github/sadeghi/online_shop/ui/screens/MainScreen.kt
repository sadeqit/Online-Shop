package io.github.sadeghi.online_shop.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.sadeghi.online_shop.navigation.MainNavGraph
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.screens.mainScreen.bottombar.CustomBottomBar
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawer.CustomNavigationDrawer
import io.github.sadeghi.online_shop.ui.screens.mainScreen.topbar.CustomTopBar
import io.github.sadeghi.online_shop.viewModel.DrawerViewModel
import io.github.sadeghi.online_shop.viewModel.HomeViewModel

@Composable
fun MainScreen(
    drawerViewModel: DrawerViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val navController = rememberNavController()

    val currentBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        currentBackStackEntry?.destination?.route

    val hasNotification by
    homeViewModel.hasNotification.collectAsState()

    Scaffold(
        containerColor = Color.Transparent,

        contentWindowInsets = WindowInsets(0, 0, 0, 0),

        topBar = {
            CustomTopBar(
                isDrawerOpen = drawerViewModel.isDrawerOpen,

                onMenuClick = {
                    if (drawerViewModel.isDrawerOpen) {
                        drawerViewModel.closeDrawer()
                    } else {
                        drawerViewModel.openDrawer()
                    }
                },

                onNotificationClick = {
                    homeViewModel.onNotificationClick {
                        navController.navigate(Screens.Notifications.route) {
                            popUpTo(Screens.Home.route) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    }
                },

                showBackButton = currentRoute != Screens.Home.route,

                onBackClick = {
                    navController.popBackStack()
                },

                hasNotification = hasNotification
            )

        },
        bottomBar = {
            CustomBottomBar(
                selectedRoute = currentRoute,
                onItemSelected = { route ->

                    if (route == currentRoute) {
                        return@CustomBottomBar
                    }

                    when (route) {

                        Screens.Home.route -> {
                            navController.popBackStack(
                                Screens.Home.route,
                                inclusive = false
                            )
                        }

                        Screens.Profile.route -> {

                            val popped = navController.popBackStack(
                                Screens.Profile.route,
                                inclusive = false
                            )

                            if (!popped) {
                                navController.navigate(Screens.Profile.route) {
                                    launchSingleTop = true
                                }
                            }
                        }

                        else -> {
                            navController.navigate(route) {
                                popUpTo(Screens.Home.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                }
            )
        }

        /*bottomBar = {
            CustomBottomBar(
                selectedRoute = currentRoute,
                onItemSelected = { route ->

                    if (route == Screens.Home.route) {
                        navController.popBackStack(
                            Screens.Home.route,
                            inclusive = false
                        )
                        return@CustomBottomBar
                    }

                    if (route == currentRoute) return@CustomBottomBar

                    navController.navigate(route) {
                        popUpTo(Screens.Home.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }*/


    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            MainNavGraph(
                navController = navController
            )

            CustomNavigationDrawer(
                isOpen = drawerViewModel.isDrawerOpen,
                onClose = drawerViewModel::closeDrawer,
                navController = navController
            )
        }
    }
}
