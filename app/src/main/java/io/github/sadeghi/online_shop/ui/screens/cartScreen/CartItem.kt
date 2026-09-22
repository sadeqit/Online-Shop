package io.github.sadeghi.online_shop.ui.screens.cartScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.sadeghi.online_shop.ui.component.SpacerWidth

@Composable
fun CartItem(
    productName: String,
    productImage: String?,
    discountedPrice: String,
    originalPrice: String,
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                onClick()
            }
            .padding(10.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        // کنترل تعداد
        Column(
            modifier = Modifier
                .background(
                    color = Color(0xFFFCF3EC),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(vertical = 4.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            // افزایش
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        onIncrease()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Green
                )
            }

            // تعداد
            Text(
                text = quantity.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // کاهش
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        onDecrease()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "−",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }
        }

        SpacerWidth(12)

        // عکس محصول
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(
                    color = Color(0xFFEBEBEB),
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            AsyncImage(
                model = productImage,
                contentDescription = productName,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )
        }

        SpacerWidth(12)

        // اطلاعات محصول
        Column(
            modifier = Modifier
                .weight(1f)
                .height(100.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // اسم محصول - بالا
            Text(
                text = productName,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Right
            )

            // قیمت‌ها - پایین
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {

                // قیمت اصلی
                Text(
                    text = "$originalPrice تومان",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textDecoration = TextDecoration.LineThrough
                )

                SpacerWidth(8)

                // قیمت تخفیف‌خورده
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = discountedPrice,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    SpacerWidth(4)

                    Text(
                        text = "تومان",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}