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
import androidx.compose.foundation.layout.padding
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
import io.github.sadeghi.online_shop.ui.screens.cartScreen.formatPrice
import io.github.sadeghi.online_shop.ui.screens.profilescreen.orfer.Order
import io.github.sadeghi.online_shop.ui.screens.profilescreen.orfer.formatPersianDate

@Composable
fun OrderItem(
    order: Order
) {

    val item = order.items.firstOrNull()
        ?: return

    val isSuccess = order.status == "پرداخت موفق"

    val statusBackground =
        if (isSuccess) {
            Color(0xFFDFF1CF)
        } else {
            Color(0xFFFFDAD6)
        }

    val statusColor =
        if (isSuccess) {
            Color(0xFF22A447)
        } else {
            Color(0xFFD32F2F)
        }

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
                painter = painterResource(item.product.image),
                contentDescription = item.product.title,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
            ) {

                Text(
                    text = "کد سفارش: ${order.id}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )

                Text(
                    text = formatPersianDate(order.date),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }

            Text(
                text = item.product.title,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right,
                color = Color.Black
            )

            SpacerHeight(20)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
            ) {

                Text(
                    text = "${formatPrice(order.totalPrice)} تومان",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = order.status,
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(statusBackground)
                        .padding(
                            horizontal = 8.dp,
                            vertical = 2.dp
                        ),
                    fontSize = 14.sp,
                    color = statusColor
                )
            }
        }
    }
}
/*
@Composable
fun OrderItem(
    order: Order
) {

    val item = order.items.firstOrNull()
        ?: return

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
                painter = painterResource(item.product.image),
                contentDescription = item.product.title,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
            ) {

                Text(
                    text = "کد سفارش: ${order.id}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )

                Text(
                    text = formatPersianDate(order.date),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }

            Text(
                text = item.product.title,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right,
                color = Color.Black
            )

            SpacerHeight(20)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
            ) {

                Text(
                    text = "${formatPrice(order.totalPrice)} تومان",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = order.status,
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color(0xFFDFF1CF))
                        .padding(
                            horizontal = 8.dp,
                            vertical = 2.dp
                        ),
                    fontSize = 14.sp,
                    color = Color(0xFF22A447)
                )
            }
        }
    }
}
*/

