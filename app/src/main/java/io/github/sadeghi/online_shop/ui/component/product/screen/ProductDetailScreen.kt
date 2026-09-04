package io.github.sadeghi.online_shop.ui.component.product.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.product.bestsellingProducts
import io.github.sadeghi.online_shop.ui.screens.homeScreen.component.Bestselling

@Composable
fun ProductDetailScreen(
    productId: Int,
    navController: NavHostController
) {
    val product = bestsellingProducts.find {
        it.id == productId
    } ?: return

    val focusManager = LocalFocusManager.current
    val listState = rememberLazyListState()

    var reviews by remember {
        mutableStateOf(
            listOf(
                ProductReview(
                    userName = "علی رضایی",
                    rating = 4,
                    comment = "محصول بسیار عالی بود و کیفیت اشفالی بود"
                ),
                ProductReview(
                    userName = "رضا صادقی",
                    rating = 4,
                    comment = "محصول خیلی خوب بود و کیفیت مناسبی داشت."
                )
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus() // اینجا استفاده کن
            }
    ) {

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(bottom = 120.dp)
        ) {

            item {
                SpacerHeight(30)
            }

            item {
                ProductImage(
                    product = product,
                    reviews = reviews,
                    listState = listState,
                )
            }

            item {
                SpacerHeight(20)
            }
            item {
                FeatureScreen()
            }
            item {
                SpacerHeight(20)
            }

            item {
                CommentScreen(
                    onSubmit = { comment, rating ->
                        reviews = reviews + ProductReview(
                            userName = "کاربر",
                            rating = rating,
                            comment = comment
                        )
                    }
                )
            }

            item {
                SpacerHeight(30)
            }

            item {
                UserReviews(reviews = reviews)
            }

            item {
                SpacerHeight(30)
            }

            item {
                Bestselling(
                    text = "محصولات مشابه",
                    navController = navController
                )
            }

            item {
                SpacerHeight(30)
            }
        }

        // همیشه ثابت و شناور
        CartButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            product
        )
    }
}
