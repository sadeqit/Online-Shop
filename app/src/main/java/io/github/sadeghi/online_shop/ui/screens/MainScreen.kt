package io.github.sadeghi.online_shop.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.sadeghi.online_shop.navigation.MainNavGraph
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.screens.cartScreen.CartStep
import io.github.sadeghi.online_shop.ui.screens.mainScreen.bottombar.CustomBottomBar
import io.github.sadeghi.online_shop.ui.screens.mainScreen.drawer.CustomNavigationDrawer
import io.github.sadeghi.online_shop.ui.screens.mainScreen.topbar.CustomTopBar
import io.github.sadeghi.online_shop.viewModel.NotificationsViewModel
import io.github.sadeghi.online_shop.viewModel.DrawerViewModel
import io.github.sadeghi.online_shop.viewModel.ProfileViewModel

@Composable
fun MainScreen(
    drawerViewModel: DrawerViewModel = hiltViewModel(),
    notificationsViewModel: NotificationsViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {
    val hasNotification = notificationsViewModel.hasNotification
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {

        val observer = LifecycleEventObserver { _, event ->

            if (event == Lifecycle.Event.ON_RESUME) {
                notificationsViewModel.loadNotifications()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


    var cartStep by rememberSaveable {
        mutableStateOf(CartStep.CART)
    }
    val navController = rememberNavController()

    val currentBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        currentBackStackEntry?.destination?.route

    Scaffold(
        containerColor = Color.Transparent,

        contentWindowInsets = WindowInsets(0, 0, 0, 0),

        topBar = {
            CustomTopBar(
                profileViewModel = profileViewModel,
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
                    if (currentRoute == Screens.Cart.route) {

                        when (cartStep) {
                            CartStep.PAYMENT -> {
                                cartStep = CartStep.ADDRESS
                            }

                            CartStep.ADDRESS -> {
                                cartStep = CartStep.CART
                            }

                            CartStep.CART -> {
                                navController.popBackStack()
                            }
                        }

                    } else {
                        navController.popBackStack()
                    }
                },

                hasNotification = hasNotification
            )

        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .blur(
                        if (drawerViewModel.isDrawerOpen) {
                            12.dp
                        } else {
                            0.dp
                        }
                    )
            ) {
                CustomBottomBar(
                    selectedRoute = currentRoute,
                    onItemSelected = { route ->

                        if (drawerViewModel.isDrawerOpen) {
                            drawerViewModel.closeDrawer()
                        }

                        if (route == currentRoute) {
                            return@CustomBottomBar
                        }

                        navController.navigate(route) {
                            popUpTo(
                                navController.currentBackStackEntry
                                    ?.destination
                                    ?.id
                                    ?: return@navigate
                            ) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                )
            }
        }

    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(
                        if (drawerViewModel.isDrawerOpen) {
                            12.dp
                        } else {
                            0.dp
                        }
                    )
            ) {
                MainNavGraph(
                    navController = navController,
                    notificationsViewModel = notificationsViewModel,
                    profileViewModel = profileViewModel,
                    onLogout = onLogout,
                    cartStep = cartStep,
                    onCartStepChange = {
                        cartStep = it
                    }
                )
            }

            CustomNavigationDrawer(
                isOpen = drawerViewModel.isDrawerOpen,
                onClose = drawerViewModel::closeDrawer,
                navController = navController,
                profileViewModel = profileViewModel
            )
        }
    }
}
