package io.github.sadeghi.online_shop.feature.category

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import io.github.sadeghi.online_shop.feature.category.component.CategorySelector
import io.github.sadeghi.online_shop.feature.category.component.SubCategoryCardItem

@Composable
fun CategoryProductScreen(
    categoryId: Int,
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    var selectedCategoryId by rememberSaveable {
        mutableIntStateOf(categoryId)
    }

    val subCategoryViewModel: SubCategoryViewModel = hiltViewModel()

    val subCategories by subCategoryViewModel.subCategories
        .collectAsStateWithLifecycle()

    val categorySubCategories = subCategories.filter {
        it.categoryId == selectedCategoryId
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            SpacerHeight(20)
        }

        item {
            CategorySelector(
                selectedCategoryId = selectedCategoryId,
                onCategoryClick = { newId ->
                    selectedCategoryId = newId
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

        items(categorySubCategories.chunked(3)) { rowItems ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                rowItems.forEach { item ->

                    SubCategoryCardItem(
                        subCategory = item,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            navController.navigate(
                                Screens.SubCategoryProduct.createRoute(item.id)
                            )
                        }
                    )
                }

                repeat(3 - rowItems.size) {
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // پرفروش‌ترین‌ها
        item {
            Bestselling(
                "پرفروش ترین های هفته گذشته",
                navController = navController,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }

        item {
            SpacerHeight(20)
        }
    }
}