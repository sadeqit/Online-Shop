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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.SpacerWidth
import io.github.sadeghi.online_shop.ui.theme.orange
import io.github.sadeghi.online_shop.viewModel.ProductReviewViewModel

@Composable
fun UserReviews(
    reviews: List<ProductReviewUi>,
    productId: Int,
    viewModel: ProductReviewViewModel,
    currentUserId: String?,
    isAdmin: Boolean
) {


    val focusManager = LocalFocusManager.current

    var replyInputs by remember {
        mutableStateOf(
            emptyMap<Long, String>()
        )
    }
    var editingReviewId by remember {
        mutableStateOf<Long?>(null)
    }

    var editingComment by remember {
        mutableStateOf("")
    }

    var editingRating by remember {
        mutableIntStateOf(0)
    }

    var deletingReviewId by remember {
        mutableStateOf<Long?>(null)
    }
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        )
        {

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
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    reviews.forEachIndexed { index, review ->

                        val reply = review.adminReply.orEmpty()
                        val replyInput = replyInputs[review.id].orEmpty()
                        val isMyReview =
                            review.userId == currentUserId

                        val isEditing =
                            editingReviewId == review.id

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color.White,
                                    RoundedCornerShape(12.dp)
                                )
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFE0E0E0),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(12.dp)
                        ){

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
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

                            if (isEditing) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {

                                    (1..5).forEach { rating ->

                                        IconButton(
                                            onClick = {
                                                editingRating = rating
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Star,
                                                contentDescription = rating.toString(),
                                                tint =
                                                    if (rating <= editingRating) {
                                                        Color(0xFFFFC107)
                                                    } else {
                                                        Color.LightGray
                                                    }
                                            )
                                        }
                                    }
                                }

                                OutlinedTextField(
                                    value = editingComment,
                                    onValueChange = {
                                        editingComment = it
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    textStyle = LocalTextStyle.current.copy(
                                        textAlign = TextAlign.Right,
                                        color = Color.Black,
                                        fontSize = 13.sp
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                )

                                SpacerHeight(10)

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    TextButton(
                                        onClick = {
                                            editingReviewId = null
                                            editingComment = ""
                                            editingRating = 0
                                        }
                                    ) {
                                        Text("لغو")
                                    }

                                    SpacerWidth(8)

                                    Button(
                                        onClick = {

                                            viewModel.updateReview(
                                                reviewId = review.id,
                                                productId = productId,
                                                rating = editingRating,
                                                comment = editingComment
                                            )

                                            editingReviewId = null
                                            editingComment = ""
                                            editingRating = 0
                                        },
                                        enabled = editingComment.isNotBlank() &&
                                                editingRating in 1..5
                                    ) {
                                        Text("ذخیره")
                                    }
                                }

                            } else {

                                Text(
                                    text = review.comment,
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Right
                                )
                            }

                            if (reply.isNotBlank()) {

                                SpacerHeight(12)

                                Column(
                                    modifier = Modifier.fillMaxWidth()
                                ) {

                                    Text(
                                        text = "غرفه دار",
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
                            if (isMyReview && !isEditing) {

                                SpacerHeight(10)

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {

                                    TextButton(
                                        onClick = {
                                            editingReviewId = review.id
                                            editingComment = review.comment
                                            editingRating = review.rating
                                        }
                                    ) {
                                        Text(
                                            text = "ویرایش",
                                            color = Color.Black,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    TextButton(
                                        onClick = {
                                            deletingReviewId = review.id
                                        }
                                    ) {
                                        Text(
                                            text = "حذف",
                                            color = Color.Red,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }

                        SpacerHeight(14)

                        if (isAdmin) {

                            OutlinedTextField(
                                value = replyInput,
                                onValueChange = {
                                    replyInputs = replyInputs + (
                                            review.id to it
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

                                            viewModel.replyToReview(
                                                reviewId = review.id,
                                                productId = productId,
                                                reply = replyInput.trim()
                                            )

                                            replyInputs = replyInputs - review.id

                                            focusManager.clearFocus()
                                        }
                                    }
                                )
                            )

                            SpacerHeight(10)

                            if (replyInput.isNotBlank()) {

                                Button(
                                    onClick = {

                                        viewModel.replyToReview(
                                            reviewId = review.id,
                                            productId = productId,
                                            reply = replyInput.trim()
                                        )

                                        replyInputs = replyInputs - review.id

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
                        }

                        if (index != reviews.lastIndex) {
                            SpacerHeight(6)
                        }
                    }
                }
            }
        }

        if (deletingReviewId != null) {

            AlertDialog(
                containerColor = Color(0xFFFCF3EC),
                onDismissRequest = {
                    deletingReviewId = null
                },
                title = {
                    Text(
                        text = "حذف نظر",
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )

                },
                text = {
                    Text(
                        text = "آیا از حذف این نظر مطمئن هستید؟",
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                confirmButton = {

                    TextButton(
                        onClick = {

                            val reviewId =
                                deletingReviewId ?: return@TextButton

                            viewModel.deleteReview(
                                reviewId = reviewId,
                                productId = productId
                            )

                            deletingReviewId = null
                        }
                    ) {
                        Text(
                            text = "حذف",
                            color = Color.Red,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                dismissButton = {

                    TextButton(
                        onClick = {
                            deletingReviewId = null
                        }
                    ) {
                        Text(
                            text = "انصراف",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    }
}