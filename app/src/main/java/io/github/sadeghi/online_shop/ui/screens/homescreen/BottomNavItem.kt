package io.github.sadeghi.online_shop.ui.screens.homescreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)
val bottomItems = listOf(
    BottomNavItem("خانه", Icons.Default.Home),
    BottomNavItem("دسته بندی", Icons.Default.GridView),
    BottomNavItem("سبد خرید", Icons.Default.ShoppingCart),
    BottomNavItem("پروفایل من", Icons.Default.Person)
)