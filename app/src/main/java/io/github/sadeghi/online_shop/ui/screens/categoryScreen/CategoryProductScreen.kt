package io.github.sadeghi.online_shop.ui.screens.categoryScreen

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
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.card.CardItem
import io.github.sadeghi.online_shop.ui.component.card.menItems
import io.github.sadeghi.online_shop.ui.screens.homeScreen.component.Bestselling
import io.github.sadeghi.online_shop.ui.screens.homeScreen.component.SearchBar

@Composable
fun CategoryProductScreen(
    categoryId: Int,
    navController: NavHostController

) {
    var selectedCategoryId by rememberSaveable { mutableIntStateOf(categoryId) }


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            SpacerHeight(20)
        }

        // دسته‌ها

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

        items(menItems.chunked(3)) { rowItems ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                rowItems.forEach { item ->

                    CardItem(
                        image = item.image,
                        title = item.title,
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
            Bestselling("پرفروش ترین های هفته گذشته",
                navController = navController)
        }
        item {
            SpacerHeight(20)
        }
    }
}
