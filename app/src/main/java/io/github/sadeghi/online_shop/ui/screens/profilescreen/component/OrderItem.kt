package io.github.sadeghi.online_shop.ui.screens.profilescreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.ui.component.SpacerHeight

@Composable
fun OrderItem() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .width(300.dp)
            .height(IntrinsicSize.Min)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // عکس محصول
        Box(
            modifier = Modifier
                .padding(12.dp)
                .width(90.dp)
                .fillMaxHeight()
                .background(
                    color = Color(0xFFEBEBEB),
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.lebas),
                contentDescription = "product",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )
        }

        // اطلاعات سفارش
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        )
        {

            // کد سفارش و تاریخ
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = "کد سفارش: 12364",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )

                Text(
                    text = "26 مهر 1402",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }

            // نام محصول
            Text(
                text = "ست سویشرت و شلوار مردانه",
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right,
                color = Color.Black
            )

            SpacerHeight(20)

            // قیمت و وضعیت پرداخت
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = "1,550,000   تومان",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )



                Text(
                    text = "پرداخت موفق",
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color(0xFFDFF1CF))
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    fontSize = 14.sp,
                    color = Color(0xFF22A447)
                )
            }
        }
    }
}