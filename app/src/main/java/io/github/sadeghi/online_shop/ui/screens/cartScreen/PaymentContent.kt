package io.github.sadeghi.online_shop.ui.screens.cartScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.viewModel.AddressViewModel
import io.github.sadeghi.online_shop.ui.theme.orange
@Composable
fun PaymentContent(
    onBack: () -> Unit,
    onContinue: () -> Unit
) {

    val addressViewModel: AddressViewModel = hiltViewModel()
    val cartViewModel: CartViewModel = hiltViewModel()

    val addresses by addressViewModel.addresses.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()

    val selectedAddress = addresses.firstOrNull { it.isDefault }

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

            if (selectedAddress != null) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp)
                ) {

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("گیرنده : ")
                            }
                            append(selectedAddress.receiver)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Right
                    )

                    SpacerHeight(10)

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("آدرس : ")
                            }
                            append(selectedAddress.address)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Right
                    )

                    SpacerHeight(10)

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("کد پستی : ")
                            }
                            append(selectedAddress.postalCode)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Right
                    )

                    SpacerHeight(10)

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("شماره همراه : ")
                            }
                            append(selectedAddress.phoneNumber)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Right
                    )
                }
            }

            SpacerHeight(16)

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = orange
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = orange
                )
            ) {

                Text(
                    text = "به آدرس دیگری برود",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = orange
                )
            }

            SpacerHeight(20)

            Text(
                text = "تقریبا تا 5 روز آینده این محصول بدست شما میرسد",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {

                CartSummary(
                    totalPrice = formatPrice(totalPrice),
                    discount = formatPrice(discount),
                    finalPrice = formatPrice(finalPrice),
                    shippingCost = "150,000",
                    continueButtonText = "پرداخت نهایی",
                    onContinueClick = onContinue
                )
            }
        }
    }
}