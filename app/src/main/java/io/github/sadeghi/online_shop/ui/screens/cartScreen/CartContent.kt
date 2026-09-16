package io.github.sadeghi.online_shop.ui.screens.cartScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.component.SpacerHeight

@Composable
fun CartContent(
    navController: NavHostController,
    onContinue: () -> Unit
) {
    val viewModel: CartViewModel = hiltViewModel()
    val cartItems by viewModel.cartItems.collectAsState()

    val totalPrice = cartItems.sumOf { cartItem ->
        cartItem.product.oldPrice
            .replace(",", "")
            .toLong() * cartItem.quantity
    }

    val finalPrice = cartItems.sumOf { cartItem ->
        cartItem.product.price
            .replace(",", "")
            .toLong() * cartItem.quantity
    }

    val discount = totalPrice - finalPrice

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SpacerHeight(40)

            Text(
                text = "سبد خرید",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right
            )

            SpacerHeight(20)

            if (cartItems.isEmpty()) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "سبد خرید شما خالی است",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }

            } else {

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = cartItems,
                        key = { it.product.id }
                    ) { cartItem ->

                        CartItem(
                            productName = cartItem.product.title,
                            productImage = cartItem.product.image,
                            discountedPrice = cartItem.product.price,
                            originalPrice = cartItem.product.oldPrice,
                            quantity = cartItem.quantity,

                            onIncrease = {
                                viewModel.increaseQuantity(
                                    cartItem.product.id
                                )
                            },

                            onDecrease = {
                                viewModel.decreaseQuantity(
                                    cartItem.product.id
                                )
                            },

                            onClick = {
                                navController.navigate(
                                    Screens.ProductDetail.createRoute(
                                        cartItem.product.id
                                    )
                                )
                            }
                        )
                    }
                }
            }

            if (cartItems.isNotEmpty()) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    CartSummary(
                        totalPrice = formatPrice(totalPrice),
                        discount = formatPrice(discount),
                        finalPrice = formatPrice(finalPrice),
                        onContinueClick = onContinue
                    )
                }

            }
        }
    }
}
fun formatPrice(price: Long): String {
    return "%,d".format(price)
}
