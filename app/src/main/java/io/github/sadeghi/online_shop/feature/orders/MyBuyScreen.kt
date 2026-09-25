package io.github.sadeghi.online_shop.feature.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.core.ui.SpacerHeight
import io.github.sadeghi.online_shop.feature.profile.component.HeaderProfile
import io.github.sadeghi.online_shop.feature.product.review.ProductReviewViewModel
import io.github.sadeghi.online_shop.feature.profile.ProfileViewModel

@Composable
fun MyBuyScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel,
    orderViewModel: OrderViewModel = hiltViewModel(),
    reviewViewModel: ProductReviewViewModel = hiltViewModel()
) {

    val orders by orderViewModel.orders.collectAsState()
    val reviews by reviewViewModel.reviews.collectAsState()

    val purchasedItems = remember(orders) {
        orders
            .flatMap { it.items }
            .distinctBy { it.product.id }
    }

    LaunchedEffect(purchasedItems) {

        if (purchasedItems.isNotEmpty()) {

            reviewViewModel.loadReviewsForProducts(
                purchasedItems.map { it.product.id }
            )

            reviewViewModel.loadCurrentUser()
        }
    }

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Ltr
            ) {
                HeaderProfile(
                    compact = true,
                    profileViewModel = profileViewModel
                )
            }

            SpacerHeight(40)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {

                Text(
                    text = "تجربه های خرید من",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right
                )

                SpacerHeight(20)

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 20.dp)
                ) {

                    purchasedItems.forEachIndexed { index, item ->

                        val productReview =
                            reviews
                                .filter {
                                    it.productId == item.product.id
                                }
                                .maxByOrNull {
                                    it.createdAt
                                }

                        MyPurchaseItem(
                            product = item.product,
                            hasReview = productReview != null,
                            rating = productReview?.rating ?: 0,
                            reviewText = productReview?.comment.orEmpty(),
                            onReviewClick = {

                                navController.navigate(
                                    Screens.ProductDetail.createRoute(
                                        item.product.id
                                    )
                                )
                            }
                        )

                        if (index != purchasedItems.lastIndex) {
                            SpacerHeight(15)
                        }
                    }
                }
            }
        }
    }
}
