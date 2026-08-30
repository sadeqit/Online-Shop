package io.github.sadeghi.online_shop.ui.screens.homeScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.ui.component.product.ProductItem
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.product.bestsellingProducts


@Composable
fun Bestselling() {

    var showAll by remember { mutableStateOf(false) }
    val itemCount = if (showAll) bestsellingProducts.size else 3

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.clickable { showAll = !showAll },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    if (showAll) Icons.AutoMirrored.Filled.ArrowForward
                    else Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color(0xFFEF472C),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = if (showAll) "بستن" else "مشاهده همه",
                    fontSize = 14.sp,
                )
            }
            Text(
                text = "پرفروش ترین ها",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // نمایش آیتم‌ها
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            reverseLayout = true,
            contentPadding = PaddingValues(end = 20.dp)
        ) {
            items(bestsellingProducts.take(itemCount)) { product ->
                ProductItem(product = product)
            }
        }
    }
}
/*
@Composable
fun Bestselling() {

    var showAll by remember { mutableStateOf(false) }

    // لیست آیتم‌هایی که باید نمایش داده شوند
    val displayItems = if (showAll) {
        bestsellingProducts
    } else {
        bestsellingProducts.take(2) // فقط دو آیتم اول
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp) ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Row(
                modifier = Modifier.clickable {
                    showAll = !showAll
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            )
            {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color(0xFFEF472C),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "مشاهده همه",
                    fontSize = 14.sp,

                    )

            }
            Text(
                text = "پرفروش ترین ها",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

        }

        SpacerHeight(12)

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            reverseLayout = true,
            contentPadding = PaddingValues(end = 20.dp)
        ) {
            items(bestsellingProducts) { product ->

                ProductItem(
                    product = product
                )
            }
        }
    }
}
*/
