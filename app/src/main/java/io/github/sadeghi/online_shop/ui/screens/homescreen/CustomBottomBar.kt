package io.github.sadeghi.online_shop.ui.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.ui.component.SpacerHeight

@Composable
fun CustomBottomBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {

    BottomAppBar(
        containerColor = Color.White
    ) {

        bottomItems.forEachIndexed { index, item ->

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onItemSelected(index) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 🔥 خط نارنجی بالا
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(
                            if (selectedIndex == index)
                                Color(0xFFFF6F00)
                            else
                                Color.Transparent
                        )
                )

                SpacerHeight(6)

                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = if (selectedIndex == index)
                        Color(0xFFFF6F00)
                    else
                        Color.Gray
                )

                Text(
                    text = item.title,
                    fontSize = 12.sp,
                    color = if (selectedIndex == index)
                        Color(0xFFFF6F00)
                    else
                        Color.Gray
                )

                SpacerHeight(6)
            }
        }
    }
}