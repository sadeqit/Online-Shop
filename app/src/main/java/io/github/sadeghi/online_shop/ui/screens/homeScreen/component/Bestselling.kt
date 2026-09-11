package io.github.sadeghi.online_shop.ui.screens.homeScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.screens.productScreen.product.ProductItem
import io.github.sadeghi.online_shop.ui.screens.productScreen.product.bestsellingProducts
import io.github.sadeghi.online_shop.ui.theme.orange


@Composable
fun Bestselling(
    text: String,
    grid: Boolean = false,
    showAllButton: Boolean = true,
    navController: NavHostController
) {

    var showAll by remember { mutableStateOf(false) }

    val itemCount = if (showAllButton) {
        if (showAll) bestsellingProducts.size else 3
    } else {
        bestsellingProducts.size
    }


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = if (showAllButton) {
                Arrangement.SpaceBetween
            } else {
                Arrangement.End
            },
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (showAllButton) {

                Row(
                    modifier = Modifier.clickable {
                        showAll = !showAll
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Icon(
                        if (showAll)
                            Icons.AutoMirrored.Filled.ArrowForward
                        else
                            Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                        tint = orange,
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = if (showAll) "بستن" else "مشاهده همه",
                        fontSize = 14.sp
                    )
                }
            }

            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right
            )
        }

        SpacerHeight(12)

        if (grid) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                bestsellingProducts
                    .take(itemCount)
                    .chunked(2)
                    .forEach { rowItems ->

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            rowItems.forEach { product ->

                                ProductItem(
                                    product = product,
                                    modifier = Modifier.weight(1f),
                                    onClick = {
                                        navController.navigate(
                                            Screens.ProductDetail.createRoute(product.id)
                                        )
                                    }
                                )
                            }

                            if (rowItems.size == 1) {
                                Spacer(
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
            }

        } else {

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                reverseLayout = true,
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {

                items(
                    bestsellingProducts.take(itemCount)
                ) { product ->

                    ProductItem(
                        product = product,
                        onClick = {
                            navController.navigate(
                                Screens.ProductDetail.createRoute(product.id)
                            )
                        }
                    )
                }
            }
        }
    }
}
