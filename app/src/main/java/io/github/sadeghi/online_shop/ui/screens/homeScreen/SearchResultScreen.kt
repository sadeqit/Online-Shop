package io.github.sadeghi.online_shop.ui.screens.homeScreen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.navigation.Screens
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.screens.cartScreen.CartViewModel
import io.github.sadeghi.online_shop.ui.screens.productScreen.product.ProductItem
import io.github.sadeghi.online_shop.viewModel.ProductViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchResultScreen(
    query: String,
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val productViewModel: ProductViewModel = hiltViewModel()

    val products by productViewModel.products.collectAsStateWithLifecycle()

    val results = remember(query, products) {
        products.filter { product ->
            product.title.contains(
                query.trim(),
                ignoreCase = true
            )
        }
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    val cartViewModel: CartViewModel = hiltViewModel()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                SpacerHeight(20)
            }

            item {
                Text(
                    text = "نتایج جستجو برای \u200F«$query»\u200F",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    textAlign = TextAlign.Right,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            if (results.isEmpty()) {

                item {
                    Text(
                        text = "محصولی با این نام پیدا نشد",
                        modifier = Modifier.padding(top = 40.dp),
                        fontSize = 16.sp
                    )
                }

            } else {

                items(results.chunked(2)) { rowItems ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        rowItems.forEach { product ->

                            ProductItem(
                                product = product,
                                modifier = Modifier.weight(1f),
                                sharedTransitionScope = sharedTransitionScope,
                                animatedVisibilityScope = animatedVisibilityScope,
                                onClick = {
                                    navController.navigate(
                                        Screens.ProductDetail.createRoute(
                                            product.id
                                        )
                                    )
                                },
                                onAddToCart = {
                                    cartViewModel.addToCart(product)

                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "محصول به سبد خرید اضافه شد",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            )
                        }

                        repeat(2 - rowItems.size) {
                            Spacer(
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            item {
                SpacerHeight(20)
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 16.dp
                )
        ) { snackbarData ->

            Snackbar(
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
