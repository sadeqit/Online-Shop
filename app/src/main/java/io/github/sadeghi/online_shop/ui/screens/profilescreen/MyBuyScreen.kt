package io.github.sadeghi.online_shop.ui.screens.profilescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.screens.profilescreen.component.HeaderProfile

@Composable
fun MyBuyScreen(
    navController: NavController
){
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Ltr
            ) {
                HeaderProfile(true)
            }

            SpacerHeight(40)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)

            ) {

                Text(
                    text = "تجربه های خرید من",
                    modifier = Modifier
                        .fillMaxWidth(),
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

                // محصول اول - هنوز نظر ثبت نشده
                MyPurchaseItem(
                    productName = "ست سویشرت و شلوار مردانه",
                    productPrice = "1,550,000",
                    hasReview = false,
                    onReviewClick = {
                        navController.navigate(
                            Screens.ProductDetail.createRoute(1)
                        )
                    }
                )

                SpacerHeight(15)

                // محصول دوم - نظر ثبت شده
                MyPurchaseItem(
                    productName = "ست سویشرت و شلوار مردانه",
                    productPrice = "1,550,000",
                    hasReview = true,
                    rating = 4,
                    reviewText = "کیفیت محصول خیلی خوب بود و از خریدم راضی هستم.",
                    onReviewClick = {}
                )

                SpacerHeight(15)

                // محصول سوم - هنوز نظر ثبت نشده
                MyPurchaseItem(
                    productName = "ست سویشرت و شلوار مردانه",
                    productPrice = "1,550,000",
                    hasReview = false,
                    onReviewClick = {
                        navController.navigate(
                            Screens.ProductDetail.createRoute(1)
                        )
                    }
                )
                SpacerHeight(15)

                // محصول سوم - هنوز نظر ثبت نشده
                MyPurchaseItem(
                    productName = "ست سویشرت و شلوار مردانه",
                    productPrice = "1,550,000",
                    hasReview = false,
                    onReviewClick = {
                        navController.navigate(
                            Screens.ProductDetail.createRoute(1)
                        )
                    }
                )
                SpacerHeight(15)

                // محصول سوم - هنوز نظر ثبت نشده
                MyPurchaseItem(
                    productName = "ست سویشرت و شلوار مردانه",
                    productPrice = "1,550,000",
                    hasReview = false,
                    onReviewClick = {
                        navController.navigate(
                            Screens.ProductDetail.createRoute(1)
                        )
                    }
                )
            }
        }
    }
}}