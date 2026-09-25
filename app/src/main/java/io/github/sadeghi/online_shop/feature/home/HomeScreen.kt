package io.github.sadeghi.online_shop.feature.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.core.ui.SpacerHeight
import io.github.sadeghi.online_shop.feature.home.component.BannerScreen
import io.github.sadeghi.online_shop.feature.home.component.Bestselling
import io.github.sadeghi.online_shop.feature.home.component.CategoryRow
import io.github.sadeghi.online_shop.feature.home.component.SearchBar


@Composable
fun HomeScreen(
    onCategoryClick: (Int) -> Unit,
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            },

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { SpacerHeight(20) }

        item { BannerScreen() }

        item {
            SearchBar(
                text = "تنها با یک کلیک خرید کن!",
                onSearch = { query ->
                    navController.navigate(
                        Screens.SearchResult.createRoute(query)
                    )
                }
            )
        }

        item {
            CategoryRow(onCategoryClick = onCategoryClick)
        }

        item { SpacerHeight(30) }

        item {
            Bestselling(
                "پرفروش ترین ها",
                navController = navController,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }

        item { SpacerHeight(20) }

    }
}
