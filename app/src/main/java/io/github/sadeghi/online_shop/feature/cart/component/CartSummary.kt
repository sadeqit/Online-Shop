package io.github.sadeghi.online_shop.feature.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.core.ui.GradientButton
import io.github.sadeghi.online_shop.core.ui.SpacerHeight

@Composable
fun CartSummary(
    totalPrice: String,
    discount: String,
    finalPrice: String,
    onContinueClick: () -> Unit,
    continueButtonText: String = "ادامه خرید",
    shippingCost: String? = null
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                )
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                )
            )
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 20.dp,
                bottom = 24.dp
            )
    ) {

        // جمع قیمت
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "جمع قیمت:",
                fontSize = 16.sp,
                color = Color.Black
            )

            Text(
                text = "$totalPrice تومان",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        SpacerHeight(12)

        // تخفیف
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "تخفیف:",
                fontSize = 16.sp,
                color = Color.Black
            )

            Text(
                text = "$discount تومان",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE02508)
            )
        }

        // فقط در مرحله پرداخت
        if (shippingCost != null) {

            SpacerHeight(12)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "هزینه ارسال:",
                    fontSize = 16.sp,
                    color = Color.Black
                )

                Text(
                    text = "$shippingCost تومان",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }

        SpacerHeight(12)

        // مبلغ نهایی
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "مبلغ نهایی:",
                fontSize = 16.sp,
                color = Color.Black
            )

            Text(
                text = "$finalPrice تومان",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        SpacerHeight(20)

        // دکمه
        GradientButton(
            text = continueButtonText,
            onClick = onContinueClick
        )
    }
}

