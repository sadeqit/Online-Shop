package io.github.sadeghi.online_shop.ui.screens.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.SpacerWidth
import io.github.sadeghi.online_shop.ui.screens.productScreen.product.Product

@Composable
fun MyPurchaseItem(
    product: Product,
    hasReview: Boolean,
    rating: Int = 0,
    reviewText: String = "",
    onReviewClick: () -> Unit = {}

) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                onReviewClick()
            }
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {

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

                Image(
                    painter = painterResource(product.image),
                    contentDescription = product.title,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Fit
                )
            }

            SpacerWidth(12)

            // اطلاعات محصول
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                // نام محصول
                Text(
                    text = product.title,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right,
                    color = Color.Black
                )

                // امتیاز / ثبت نظر + قیمت
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    if (hasReview) {

                        // امتیاز
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            // ستاره طلایی
                            Text(
                                text = "★",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFB800)
                            )

                            SpacerWidth(4)

                            // عدد امتیاز
                            Text(
                                text = rating.toString(),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }

                    } else {

                        // ثبت نظر
                        Text(
                            text = "ثبت نظر",
                            modifier = Modifier
                                .clickable {
                                    onReviewClick()
                                }
                                .padding(
                                    horizontal = 12.dp,
                                    vertical = 6.dp
                                ),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF7A00)
                        )
                    }

                    SpacerWidth(5)

                    // قیمت
                    Text(
                        text = "${product.price} تومان",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }

        // نظر کاربر
        if (hasReview && reviewText.isNotBlank()) {

            SpacerHeight(12)

            Text(
                text = reviewText,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Right
            )
        }
    }
}
