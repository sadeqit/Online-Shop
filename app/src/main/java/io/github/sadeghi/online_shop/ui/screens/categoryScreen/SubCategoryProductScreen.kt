package io.github.sadeghi.online_shop.ui.screens.categoryScreen

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
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.card.menItems
import io.github.sadeghi.online_shop.ui.screens.homeScreen.component.Bestselling
import io.github.sadeghi.online_shop.ui.screens.homeScreen.component.SearchBar


@Composable
fun SubCategoryProductScreen(
    subCategoryId: Int
) {
    val selectedItem =
        menItems.first { it.id == subCategoryId }

    var selectedSubCategoryId by rememberSaveable {
        mutableIntStateOf(subCategoryId)
    }

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
                selectedSubCategoryId = selectedSubCategoryId,
                onSubCategoryClick = { newId ->
                    selectedSubCategoryId = newId
                }
            )
        }

        // جستجو
        item {
            SearchBar("پوشاک مردانه")
        }

        // پرفروش‌ترین‌ها
        item {
            Bestselling(selectedItem.title, grid = true,
                showAllButton = false)
        }

        item {
            SpacerHeight(20)
        }
    }
}