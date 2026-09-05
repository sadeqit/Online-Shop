package io.github.sadeghi.online_shop.ui.screens.profilescreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.SpacerWidth

@Composable
fun HeaderProfile(
    compact: Boolean = false
) {

    if (compact) {

        // هدر صفحه سفارش‌ها
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFE32A0D),
                            Color(0xFFFD937F)
                        )
                    ),
                    shape = RoundedCornerShape(
                        bottomEnd = 30.dp,
                        bottomStart = 30.dp
                    )
                )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "بهرام افشاری",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    SpacerHeight(4)

                    Text(
                        text = "0912123456",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }

                SpacerWidth(10)

                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "profile-pic",
                    modifier = Modifier.size(90.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

    } else {

        // هدر اصلی پروفایل
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFE32A0D),
                            Color(0xFFFD937F)
                        )
                    ),
                    shape = RoundedCornerShape(
                        bottomEnd = 30.dp,
                        bottomStart = 30.dp
                    )
                )
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SpacerHeight(20)

                Text(
                    text = "پروفایل کاربر",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                SpacerHeight(15)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        modifier = Modifier.size(30.dp),
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.pic),
                            contentDescription = "upload",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    SpacerWidth(30)

                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = "profile-pic",
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop
                    )

                    SpacerWidth(20)

                    IconButton(
                        modifier = Modifier.size(30.dp),
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.edit),
                            contentDescription = "edit",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                SpacerHeight(5)

                Text(
                    text = "بهرام افشاری",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                SpacerHeight(2)

                Text(
                    text = "0912123456",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}
/*
@Composable
fun HeaderProfile() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFE32A0D),
                        Color(0xFFFD937F)
                    )
                ),
                shape = RoundedCornerShape(
                    bottomEnd = 30.dp,
                    bottomStart = 30.dp
                )
            )
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SpacerHeight(20)

            Text(
                text = "پروفایل کاربر",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            SpacerHeight(15)

            // آیکن آپلود + عکس + آیکن ویرایش
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    modifier = Modifier.size(30.dp),
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.pic),
                        contentDescription = "upload",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }

                SpacerWidth(30)

                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "profile-pic",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )

                SpacerWidth(20)

                IconButton(
                    modifier = Modifier.size(30.dp),
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.edit),
                        contentDescription = "edit",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            SpacerHeight(5)

            Text(
                text = "بهرام افشاری",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            SpacerHeight(2)

            Text(
                text = "0912123456",
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}*/
