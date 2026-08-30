package io.github.sadeghi.online_shop.ui.screens.categoryScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.ui.component.card.categories

@Composable
fun CategorySelector(
    selectedCategoryId: Int,
    onCategoryClick: (Int) -> Unit = {}
) {
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            categories.forEach { category ->

                val isSelected = category.id == selectedCategoryId

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(14.dp))
                        .border(
                            width = if (isSelected) 2.dp else 0.dp,
                            color = if (isSelected) Color.Red else Color.Transparent,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .background(Color.White)
                        .padding(6.dp)
                        .clickable {
                            onCategoryClick(category.id)
                        }
                ) {

                    Image(
                        painter = painterResource(id = category.image),
                        contentDescription = category.title,
                        modifier = Modifier.fillMaxSize(),
                        alpha = if (isSelected) 1f else 0.4f
                    )
                }
            }
        }
    }

    }