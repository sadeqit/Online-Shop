package io.github.sadeghi.online_shop.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel



class DrawerViewModel : ViewModel() {

    var isDrawerOpen by mutableStateOf(false)
        private set

    fun openDrawer() {
        isDrawerOpen = true
    }

    fun closeDrawer() {
        isDrawerOpen = false
    }

    fun toggleDrawer() {
        isDrawerOpen = !isDrawerOpen
    }
}