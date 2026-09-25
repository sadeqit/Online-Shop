package io.github.sadeghi.online_shop.feature.category

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.core.ui.SpacerHeight
import io.github.sadeghi.online_shop.feature.home.component.Bestselling
import io.github.sadeghi.online_shop.feature.home.component.SearchBar
import io.github.sadeghi.online_shop.feature.category.component.SubCategorySelector


@Composable
fun SubCategoryProductScreen(
    subCategoryId: Int,
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val subCategoryViewModel: SubCategoryViewModel = hiltViewModel()

    val subCategories by subCategoryViewModel.subCategories
        .collectAsStateWithLifecycle()

    var selectedSubCategoryId by rememberSaveable {
        mutableIntStateOf(subCategoryId)
    }
    val selectedItem = subCategories
        .firstOrNull { it.id == selectedSubCategoryId }
    val categorySubCategories = selectedItem?.let { selected ->
        subCategories.filter {
            it.categoryId == selected.categoryId
        }
    } ?: emptyList()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            SpacerHeight(20)
        }

        // زیر‌دسته‌ها
        item {
            SubCategorySelector(
                subCategories = categorySubCategories,
                selectedSubCategoryId = selectedSubCategoryId,
                onSubCategoryClick = { newId ->
                    selectedSubCategoryId = newId
                }
            )
        }

        // جستجو
        item {
            SearchBar(
                text = "پوشاک مردانه",
                onSearch = { query ->
                    navController.navigate(
                        Screens.SearchResult.createRoute(query)
                    )
                }
            )
        }


        item {
            selectedItem?.let { item ->

                Bestselling(
                    text = item.title,
                    subCategoryId = item.id,
                    grid = true,
                    showAllButton = false,
                    navController = navController,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }
        }

        item {
            SpacerHeight(20)
        }
    }
}