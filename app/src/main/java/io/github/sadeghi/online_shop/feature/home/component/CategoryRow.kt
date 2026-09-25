package io.github.sadeghi.online_shop.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.core.ui.card.CardItem
import io.github.sadeghi.online_shop.core.ui.card.categories

@Composable
fun CategoryRow(
    onCategoryClick: (Int) -> Unit
) {

    LazyRow(

        horizontalArrangement = Arrangement.spacedBy(12.dp),
        reverseLayout = true,
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(categories) { category ->

            CardItem(
                image = category.image,
                title = category.title,
                onClick = { onCategoryClick(category.id) }
            )
        }
    }
}