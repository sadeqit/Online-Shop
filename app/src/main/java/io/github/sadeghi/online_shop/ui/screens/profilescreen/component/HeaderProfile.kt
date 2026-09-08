package io.github.sadeghi.online_shop.ui.screens.profilescreen.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.SpacerWidth

@Composable
fun HeaderProfile(
    compact: Boolean = false,
    iconEdit: Boolean = true,
    profileImageUri: String? = null,
    onUploadClick: () -> Unit = {},
    onEditClick: () -> Unit = {}
) {

    if (compact) {

        // هدر صفحه سفارش‌ها
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFE32A0D),
                            Color(0xFFFD937F)
                        ),
                        start = Offset(x = 0f, y = Float.POSITIVE_INFINITY),
                        end = Offset(x = Float.POSITIVE_INFINITY, y = 0f)
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

                SpacerWidth(10)
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

                SpacerWidth(9)

                if (profileImageUri != null) {
                    Box(
                        modifier = Modifier.size(90.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = profileImageUri,
                            contentDescription = "profile-pic",
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        Canvas(
                            modifier = Modifier.size(80.dp)
                        ) {
                            drawArc(
                                color = Color.White,
                                startAngle = 270f,
                                sweepAngle = 270f,
                                useCenter = false,
                                style = Stroke(width = 4.dp.toPx())
                            )
                        }
                    }
                } else {
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = "profile-pic",
                        modifier = Modifier.size(90.dp),
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }

    } else {

        // هدر اصلی پروفایل
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFE32A0D),
                            Color(0xFFFD937F)
                        ),
                        start = Offset(x = 0f, y = Float.POSITIVE_INFINITY),
                        end = Offset(x = Float.POSITIVE_INFINITY, y = 0f)
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
                        onClick = onUploadClick
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.pic),
                            contentDescription = "upload",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    SpacerWidth(30)

                    if (profileImageUri != null) {
                        Box(
                            modifier = Modifier.size(120.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = profileImageUri,
                                contentDescription = "profile-pic",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )

                            Canvas(
                                modifier = Modifier.size(115.dp)
                            ) {
                                drawArc(
                                    color = Color.White,
                                    startAngle = 270f,
                                    sweepAngle = 270f,
                                    useCenter = false,
                                    style = Stroke(width = 4.dp.toPx())
                                )
                            }
                        }
                    } else {
                        Image(
                            painter = painterResource(R.drawable.profile),
                            contentDescription = "profile-pic",
                            modifier = Modifier.size(100.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    SpacerWidth(20)

                    IconButton(
                        modifier = Modifier.size(30.dp),
                        onClick = {
                            if (iconEdit) {
                                onEditClick()
                            }
                        },
                        enabled = iconEdit
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.edit),
                            contentDescription = "edit",
                            tint = if (iconEdit) Color.White else Color.LightGray,
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
