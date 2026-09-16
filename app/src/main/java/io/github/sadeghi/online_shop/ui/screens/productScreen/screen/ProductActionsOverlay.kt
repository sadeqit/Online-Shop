package io.github.sadeghi.online_shop.ui.screens.productScreen.screen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.sadeghi.online_shop.viewModel.FavoritesViewModel
import kotlinx.coroutines.launch


@SuppressLint("DefaultLocale")
@Composable
fun ProductActionsOverlay(
    productId: Int,
    reviews: List<ProductReviewUi>,
    listState: LazyListState,
    favoritesViewModel: FavoritesViewModel,
    modifier: Modifier = Modifier
) {
    val favoriteProductIds by favoritesViewModel.favoriteProductIds.collectAsStateWithLifecycle()

    val isFavorite = productId in favoriteProductIds
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val reviewCount = reviews.size

    val averageRating = if (reviews.isNotEmpty()) {
        reviews.map { it.rating }.average()
    } else {
        0.0
    }
    Box(
        modifier = modifier
            .size(width = 200.dp, height = 47.dp)
            .clip(LeftRoundedRightSlantedShape())
            .background(Color.White))
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 14.dp, end = 22.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 1 - نظرات
            Box(
                modifier = Modifier.size(35.dp),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Outlined.ChatBubbleOutline,
                    contentDescription = "نظرات",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            scope.launch {
                                listState.scrollToItem(7)
                            }
                        }
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(18.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE32A0D)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = reviewCount.toString(),
                        modifier = Modifier.padding(bottom = 3.dp),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // 2 - ذخیره
            Icon(
                imageVector = if (isFavorite) {
                    Icons.Filled.Bookmark
                } else {
                    Icons.Outlined.BookmarkBorder
                },
                contentDescription = if (isFavorite) {
                    "حذف از علاقه مندی ها"
                } else {
                    "افزودن به علاقه مندی ها"
                },
                modifier = Modifier
                    .size(26.dp)
                    .clickable {
                        favoritesViewModel.toggleFavorite(productId)
                    }
            )

            // 3 - امتیاز
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "امتیاز",
                    modifier = Modifier.size(25.dp),
                    tint = Color(0xFFFFC400)
                )

                Spacer(
                    modifier = Modifier.width(2.dp)
                )

                Text(
                    text = String.format("%.1f", averageRating),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // 4 - اشتراک گذاری
            Icon(
                imageVector = Icons.Outlined.Share,
                contentDescription = "اشتراک گذاری",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "این محصول را ببینید"
                            )
                        }

                        context.startActivity(
                            Intent.createChooser(intent, "اشتراک گذاری")
                        )
                    }
            )
        }
    }
}



