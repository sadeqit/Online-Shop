package io.github.sadeghi.online_shop.feature.category.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.github.sadeghi.online_shop.domain.model.SubCategory

@Composable
fun SubCategorySelector(
    subCategories: List<SubCategory>,
    selectedSubCategoryId: Int,
    onSubCategoryClick: (Int) -> Unit = {}
) {
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            subCategories.forEach { item ->

                val isSelected =
                    item.id == selectedSubCategoryId

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(14.dp))
                        .border(
                            width = if (isSelected) 3.dp else 0.dp,
                            color = if (isSelected) {
                                Color.Red
                            } else {
                                Color.Transparent
                            },
                            shape = RoundedCornerShape(14.dp)
                        )
                        .background(Color.White)
                        .padding(6.dp)
                        .clickable {
                            if (!isSelected) {
                                onSubCategoryClick(item.id)
                            }
                        }
                ) {

                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = item.title,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop,
                        alpha = if (isSelected) 1f else 0.4f
                    )
                }
            }
        }
    }
}
