package io.github.sadeghi.online_shop.ui.screens.categoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.ui.component.card.CardItem
import io.github.sadeghi.online_shop.ui.component.card.categories
import io.github.sadeghi.online_shop.ui.screens.homeScreen.component.BannerScreen

@Composable
fun CategoryScreen(
    onCategoryClick: (Int) -> Unit
) {

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                BannerScreen()
            }

            item {
                Text(
                    text = "دسته ‌بندی",
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right
                )
            }

            items(categories.chunked(3)) { rowItems ->

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowItems.forEach { category ->

                        CardItem(
                            image = category.image,
                            title = category.title,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onCategoryClick(category.id)
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
        }
    }
}