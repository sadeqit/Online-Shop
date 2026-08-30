package io.github.sadeghi.online_shop.ui.screens.homeScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.ui.component.card.CardItem
import io.github.sadeghi.online_shop.ui.component.card.categories

@Composable
fun CategoryRow() {


    LazyRow(

        horizontalArrangement = Arrangement.spacedBy(12.dp),
        reverseLayout = true,
        contentPadding = PaddingValues(end = 20.dp)
    ) {
        items(categories) { category ->

            CardItem(
                image = category.image,
                title = category.title,
                onClick = {

                }
            )
        }
    }
}