package io.github.sadeghi.online_shop.ui.screens.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.viewModel.DrawerViewModel

@Composable
fun MainScreen(
    drawerViewModel: DrawerViewModel = viewModel()
) {

    val selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CustomTopBar(
                onMenuClick = { drawerViewModel.openDrawer() }
            )
        },
        bottomBar = {
            CustomBottomBar(
                selectedIndex = selectedIndex,
                onItemSelected = { /* handle navigation */ }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Screen Content
        }

        CustomNavigationDrawer(
            isOpen = drawerViewModel.isDrawerOpen,
            onClose = drawerViewModel::closeDrawer
        )
    }
}