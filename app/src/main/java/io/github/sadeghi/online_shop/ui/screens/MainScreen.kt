package io.github.sadeghi.online_shop.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.sadeghi.online_shop.navigation.MainNavGraph
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.screens.mainScreen.bottombar.CustomBottomBar
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawer.CustomNavigationDrawer
import io.github.sadeghi.online_shop.ui.screens.mainScreen.topbar.CustomTopBar
import io.github.sadeghi.online_shop.ui.screens.profilescreen.notif.NotificationsViewModel
import io.github.sadeghi.online_shop.viewModel.DrawerViewModel

@Composable
fun MainScreen(
    drawerViewModel: DrawerViewModel = hiltViewModel(),
    notificationsViewModel: NotificationsViewModel = hiltViewModel()
) {

    val navController = rememberNavController()

    val currentBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        currentBackStackEntry?.destination?.route

    val hasNotification = notificationsViewModel.hasNotification



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
                    navController.navigate(Screens.Notifications.route) {
                        popUpTo(Screens.Home.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
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
                    // اگر روی صفحه فعلی کلیک شد، کاری نکن
                    if (route == currentRoute) return@CustomBottomBar

                    // نویگیشن به صفحه جدید
                    navController.navigate(route) {
                        // پاپ کردن تا ریشه (Home) برای جلوگیری از انباشته شدن
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // جلوگیری از ایجاد چندین instance
                        launchSingleTop = true
                        // بازیابی state قبلی
                        restoreState = true
                    }
                }
            )
        }


    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            MainNavGraph(
                navController = navController,
                notificationsViewModel = notificationsViewModel
            )

            CustomNavigationDrawer(
                isOpen = drawerViewModel.isDrawerOpen,
                onClose = drawerViewModel::closeDrawer,
                navController = navController
            )
        }
    }
}
