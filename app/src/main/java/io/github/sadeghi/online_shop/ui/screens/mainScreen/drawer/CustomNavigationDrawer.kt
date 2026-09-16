package io.github.sadeghi.online_shop.ui.screens.mainScreen.drawer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.component.SpacerHeight


@Composable
fun CustomNavigationDrawer(
    isOpen: Boolean,
    onClose: () -> Unit,
    navController: NavController
) {
    AnimatedVisibility(
        visible = isOpen,
        enter = slideInVertically (
            initialOffsetY = { -it },
            animationSpec = tween(300)
        ) + fadeIn(),
        exit = slideOutVertically (
            targetOffsetY = { -it },
            animationSpec = tween(300)
        ) + fadeOut()
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(12.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onClose()
                    }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFFF6F61)
                    )
                    .align(Alignment.TopEnd)
            ) {

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(16.dp)
                ) {

                    DrawerHeader()

                    SpacerHeight(16)

                    drawerItems.forEach { item ->
                        DrawerItem(
                            item = item,
                            onClick = {
                                onClose()

                                navController.navigate(item.route) {
                                    popUpTo(Screens.Home.route) {
                                        inclusive = false
                                    }
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }

            }
        }
    }
}
