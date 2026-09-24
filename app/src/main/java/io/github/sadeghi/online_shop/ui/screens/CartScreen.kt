package io.github.sadeghi.online_shop.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.screens.cartScreen.AddressContent
import io.github.sadeghi.online_shop.ui.screens.cartScreen.CartContent
import io.github.sadeghi.online_shop.ui.screens.cartScreen.CartStep
import io.github.sadeghi.online_shop.ui.screens.cartScreen.CartViewModel
import io.github.sadeghi.online_shop.ui.screens.cartScreen.PaymentContent
import io.github.sadeghi.online_shop.ui.screens.profilescreen.orfer.Order
import io.github.sadeghi.online_shop.ui.screens.profilescreen.orfer.OrderViewModel
import io.github.sadeghi.online_shop.utils.isNetworkAvailable
import io.github.sadeghi.online_shop.viewModel.NotificationsViewModel
import kotlinx.coroutines.launch

@Composable
fun CartScreen(
    navController: NavHostController,
    currentStep: CartStep,
    onStepChange: (CartStep) -> Unit,
    notificationsViewModel: NotificationsViewModel
) {

    val context = LocalContext.current

    val cartViewModel: CartViewModel = hiltViewModel()
    val orderViewModel: OrderViewModel = hiltViewModel()
    val cartItems by cartViewModel.cartItems.collectAsState()
    val isSubmitting by orderViewModel.isSubmitting.collectAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }


    val scope = rememberCoroutineScope()

    BackHandler(
        enabled = currentStep != CartStep.CART
    ) {
        when (currentStep) {

            CartStep.CART -> Unit

            CartStep.ADDRESS -> {
                onStepChange(CartStep.CART)
            }

            CartStep.PAYMENT -> {
                onStepChange(CartStep.ADDRESS)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        when (currentStep) {

            CartStep.CART -> {

                CartContent(
                    navController = navController,
                    onContinue = {
                        onStepChange(CartStep.ADDRESS)
                    }
                )
            }

            CartStep.ADDRESS -> {

                AddressContent(
                    navController = navController,
                    onBack = {
                        onStepChange(CartStep.CART)
                    },
                    onContinue = {
                        onStepChange(CartStep.PAYMENT)
                    }
                )
            }

            CartStep.PAYMENT -> {

                PaymentContent(
                    onBack = {
                        onStepChange(CartStep.ADDRESS)
                    },
                    isSubmitting = isSubmitting,
                    onContinue = {

                        if (isSubmitting) {
                            return@PaymentContent
                        }

                        if (cartItems.isEmpty()) {
                            return@PaymentContent
                        }

                        val isOnline = isNetworkAvailable(context)

                        if (!isOnline) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "اتصال به اینترنت برقرار نیست"
                                )
                            }
                            return@PaymentContent
                        }

                        val totalPrice = cartItems.sumOf { cartItem ->
                            cartItem.product.price * cartItem.quantity
                        }

                        val order = Order(
                            id = (1000..99999).random().toLong(),
                            date = System.currentTimeMillis(),
                            items = cartItems,
                            totalPrice = totalPrice,
                            status = "پرداخت موفق"
                        )

                        orderViewModel.addOrder(
                            order = order,

                            onSuccess = {

                                cartViewModel.clearCart()

                                onStepChange(CartStep.CART)

                                notificationsViewModel.loadNotifications()

                                scope.launch {

                                    snackbarHostState.showSnackbar(
                                        "خرید شما با موفقیت انجام شد"
                                    )

                                    navController.navigate(
                                        Screens.MyOrders.route
                                    )
                                }
                            },

                            onError = { exception ->

                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "خطا: ${exception.message}"
                                    )
                                }
                            }
                        )
                    }
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        ) { snackbarData ->

            Snackbar(
                modifier = Modifier.padding(horizontal = 24.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = snackbarData.visuals.message,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}

