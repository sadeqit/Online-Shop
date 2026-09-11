package io.github.sadeghi.online_shop.ui.screens.productScreen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.theme.orange

@Composable
fun UserReviews(
    reviews: List<ProductReview>
) {

    val focusManager = LocalFocusManager.current

    var replies by remember {
        mutableStateOf(
            emptyMap<Int, String>()
        )
    }

    var replyInputs by remember {
        mutableStateOf(
            emptyMap<Int, String>()
        )
    }

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "نظرات کاربران",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right
            )

            SpacerHeight(16)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    reviews.forEachIndexed { index, review ->

                        val reply = replies[index].orEmpty()
                        val replyInput = replyInputs[index].orEmpty()

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .then(
                                    if (reply.isNotBlank()) {
                                        Modifier
                                            .border(
                                                width = 1.dp,
                                                color = Color(0xFF666666),
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(12.dp)
                                    } else {
                                        Modifier
                                    }
                                )
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = review.userName,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(
                                    modifier = Modifier.weight(1f)
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {

                                    Text(
                                        text = review.rating.toString(),
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Icon(
                                        imageVector = Icons.Filled.Star,
                                        contentDescription = "امتیاز",
                                        tint = Color(0xFFFFC107),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }

                            SpacerHeight(10)

                            Text(
                                text = review.comment,
                                modifier = Modifier.fillMaxWidth(),
                                fontSize = 13.sp,
                                textAlign = TextAlign.Right
                            )

                            if (reply.isNotBlank()) {

                                SpacerHeight(12)

                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                ) {

                                    Text(
                                        text = "مدیر",
                                        modifier = Modifier.fillMaxWidth(),
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Right
                                    )

                                    SpacerHeight(4)

                                    Text(
                                        text = reply,
                                        modifier = Modifier.fillMaxWidth(),
                                        fontSize = 13.sp,
                                        textAlign = TextAlign.Right
                                    )
                                }
                            }
                        }

                        SpacerHeight(14)

                        OutlinedTextField(
                            value = replyInput,
                            onValueChange = {
                                replyInputs = replyInputs + (
                                        index to it
                                        )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .background(
                                    Color(0xFFF2F2F2),
                                    RoundedCornerShape(12.dp)
                                ),
                            placeholder = {
                                Text(
                                    text = "پاسخ خود را وارد کنید",
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Right,
                                    color = Color.DarkGray
                                )
                            },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Right,
                                color = Color.Black,
                                fontSize = 13.sp
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {

                                    if (replyInput.isNotBlank()) {

                                        replies = replies + (
                                                index to replyInput.trim()
                                                )

                                        replyInputs = replyInputs + (
                                                index to ""
                                                )

                                        focusManager.clearFocus()
                                    }
                                }
                            )
                        )

                        SpacerHeight(10)

                        if (replyInput.isNotBlank()) {

                            Button(
                                onClick = {

                                    replies = replies + (
                                            index to replyInput.trim()
                                            )

                                    replyInputs = replyInputs + (
                                            index to ""
                                            )

                                    focusManager.clearFocus()
                                },
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .height(45.dp)
                                    .border(
                                        width = 1.dp,
                                        color = orange,
                                        shape = RoundedCornerShape(16.dp)
                                    ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                    contentColor = orange
                                )
                            ) {
                                Text(
                                    text = "ارسال پاسخ",
                                    color = orange,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }

                        if (index != reviews.lastIndex) {
                            SpacerHeight(6)
                        }
                    }
                }
            }
        }
    }
}