package io.github.sadeghi.online_shop.feature.home.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import io.github.sadeghi.online_shop.core.ui.SpacerHeight
import io.github.sadeghi.online_shop.feature.cart.CartViewModel
import io.github.sadeghi.online_shop.feature.product.component.ProductItem
import io.github.sadeghi.online_shop.core.theme.orange
import io.github.sadeghi.online_shop.feature.product.ProductViewModel
import kotlinx.coroutines.launch

@Composable
fun Bestselling(
    text: String,
    grid: Boolean = false,
    subCategoryId: Int? = null,
    showAllButton: Boolean = true,
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null
) {
    val productViewModel: ProductViewModel = hiltViewModel()
    val products by productViewModel.products.collectAsStateWithLifecycle()

    val filteredProducts = if (subCategoryId != null) {
        products.filter { it.subCategoryId == subCategoryId }
    } else {
        products
            .groupBy { it.subCategoryId }
            .map { (_, products) -> products.first() }
    }

    val cartViewModel: CartViewModel = hiltViewModel()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    var showAll by remember {
        mutableStateOf(false)
    }

    val itemCount = if (showAllButton) {
        if (showAll) filteredProducts.size else 3
    } else {
        filteredProducts.size
    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = if (showAllButton) {
                    Arrangement.SpaceBetween
                } else {
                    Arrangement.End
                },
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (showAllButton) {

                    Row(
                        modifier = Modifier.clickable {
                            showAll = !showAll
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        Icon(
                            if (showAll)
                                Icons.AutoMirrored.Filled.ArrowForward
                            else
                                Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                            tint = orange,
                            modifier = Modifier.size(20.dp)
                        )

                        Text(
                            text = if (showAll) "بستن" else "مشاهده همه",
                            fontSize = 14.sp
                        )
                    }
                }

                Text(
                    text = text,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right
                )
            }

            SpacerHeight(12)

            if (grid) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    filteredProducts
                        .take(itemCount)
                        .chunked(2)
                        .forEach { rowItems ->

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {

                                rowItems.reversed().forEach { product ->

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

                                if (rowItems.size == 1) {
                                    Spacer(
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                }

            } else {

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    reverseLayout = true,
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {

                    items(
                        filteredProducts.take(itemCount)
                    ) { product ->

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
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
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

