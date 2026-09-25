package io.github.sadeghi.online_shop.feature.product.review

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.core.ui.SpacerHeight
import io.github.sadeghi.online_shop.core.theme.orange

@Composable
fun CommentScreen(
    onSubmit: (String, Int) -> Unit
) {

    var comment by remember { mutableStateOf("") }
    var rating by remember { mutableIntStateOf(0) }

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "ثبت نظر",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right
            )

            SpacerHeight(16)

            OutlinedTextField(
                value = comment,
                onValueChange = { comment = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(16.dp)
                    ),
                placeholder = {
                    Text(
                        text = "نظر خود را وارد کنید",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Right
                    )
                },
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Right,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(16.dp)
            )

            SpacerHeight(16)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {

                    Text(
                        text = "امتیاز دهید",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    repeat(5) { index ->

                        Icon(
                            imageVector = if (index < rating) {
                                Icons.Filled.Star
                            } else {
                                Icons.Outlined.Star
                            },
                            contentDescription = "امتیاز ${index + 1}",
                            tint = if (index < rating) {
                                Color(0xFFFFC107)
                            } else {
                                Color.Gray
                            },
                            modifier = Modifier
                                .size(22.dp)
                                .clickable(
                                    interactionSource = remember {
                                        MutableInteractionSource()
                                    },
                                    indication = null
                                ) {
                                    rating = index + 1
                                }
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(0.7f))

                Button(
                    onClick = {
                        if (comment.isNotBlank() && rating in 1..5) {
                            onSubmit(comment.trim(), rating)
                            comment = ""
                            rating = 0
                        }
                    },
                    enabled = comment.isNotBlank() && rating in 1..5,
                    modifier = Modifier
                        .height(45.dp)
                        .border(
                            width = 1.dp,
                            color = if (comment.isNotBlank() && rating in 1..5) {
                                orange
                            } else {
                                Color.LightGray
                            },
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(
                            color = if (comment.isNotBlank() && rating in 1..5) {
                                Color.Transparent
                            } else {
                                Color.LightGray
                            },
                            shape = RoundedCornerShape(16.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        contentColor = orange,
                        disabledContentColor = Color.Gray
                    ),
                    elevation = null
                ) {
                    Text(
                        text = "ثبت نظر",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
