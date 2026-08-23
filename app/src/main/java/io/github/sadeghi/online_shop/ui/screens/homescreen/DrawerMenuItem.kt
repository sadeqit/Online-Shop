package io.github.sadeghi.online_shop.ui.screens.homescreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerMenuItem(
    val title: String,
    val icon: ImageVector
)
val drawerItems = listOf(
    DrawerMenuItem("پروفایل کاربری", Icons.Default.Person),
    DrawerMenuItem("سفارشات من", Icons.Default.ShoppingCart),
    DrawerMenuItem("پشتیبانی", Icons.Default.SupportAgent),
    DrawerMenuItem("قوانین و مقررات", Icons.Default.Description),
    DrawerMenuItem("درباره ما", Icons.Default.Info),
    DrawerMenuItem("ارتباط با ما", Icons.Default.Call)
)
