package io.github.sadeghi.online_shop.ui.component.product.screen

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
                                        color = Color(0xFFEF472C),
                                        shape = RoundedCornerShape(16.dp)
                                    ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                    contentColor = Color(0xFFEF472C)
                                )
                            ) {
                                Text(
                                    text = "ارسال پاسخ",
                                    color = Color(0xFFEF472C),
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

/*
@Composable
fun UserReviews(
    reviews: List<ProductReview>
) {

    var reply1 by remember { mutableStateOf("") }
    var submittedReply1 by remember { mutableStateOf("") }

    var reply2 by remember { mutableStateOf("") }
    var submittedReply2 by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

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

                    // نظر اول
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .then(
                                if (submittedReply1.isNotBlank()) {
                                    Modifier
                                        .border(
                                            width = 1.dp,
                                            color = Color(0xFFA09C9C),
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
                                text = "علی رضایی",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {

                                Text(
                                    text = "4.5",
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
                            text = "محصول بسیار عالی بود و کیفیت اشفالی بود",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 13.sp,
                            textAlign = TextAlign.Right
                        )

                        if (submittedReply1.isNotBlank()) {

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
                                    text = submittedReply1,
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Right
                                )
                            }
                        }
                    }

                    SpacerHeight(14)

                    OutlinedTextField(
                        value = reply1,
                        onValueChange = { reply1 = it },
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
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (reply1.isNotBlank()) {
                                    submittedReply1 = reply1
                                    reply1 = ""
                                    focusManager.clearFocus()
                                }
                            }
                        ),

                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )

                    SpacerHeight(10)

                    if (reply1.isNotBlank()) {
                        Button(
                            onClick = {
                                submittedReply1 = reply1
                                reply1 = ""
                            },
                            modifier = Modifier
                                .align(Alignment.End)
                                .height(45.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFEF472C),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                contentColor = Color(0xFFEF472C)
                            )
                        ) {
                            Text(
                                text = "ارسال پاسخ",
                                color = Color(0xFFEF472C),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }

                    SpacerHeight(20)

                    // نظر دوم
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .then(
                                if (submittedReply2.isNotBlank()) {
                                    Modifier
                                        .border(
                                            width = 1.dp,
                                            color = Color(0xFFE0E0E0),
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
                                text = "رضا صادقی",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {

                                Text(
                                    text = "4",
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
                            text = "محصول خیلی خوب بود و کیفیت مناسبی داشت.",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 13.sp,
                            textAlign = TextAlign.Right
                        )

                        if (submittedReply2.isNotBlank()) {

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
                                    text = submittedReply2,
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Right
                                )
                            }
                        }
                    }

                    SpacerHeight(14)

                    OutlinedTextField(
                        value = reply2,
                        onValueChange = { reply2 = it },
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
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (reply1.isNotBlank()) {
                                    submittedReply1 = reply1
                                    reply1 = ""
                                    focusManager.clearFocus()
                                }
                            }
                        ),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )

                    SpacerHeight(10)

                    if (reply2.isNotBlank()) {
                        Button(
                            onClick = {
                                submittedReply2 = reply2
                                reply2 = ""
                            },
                            modifier = Modifier
                                .align(Alignment.End)
                                .height(45.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFEF472C),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                contentColor = Color(0xFFEF472C)
                            )
                        ) {
                            Text(
                                text = "ارسال پاسخ",
                                color = Color(0xFFEF472C),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

*/
