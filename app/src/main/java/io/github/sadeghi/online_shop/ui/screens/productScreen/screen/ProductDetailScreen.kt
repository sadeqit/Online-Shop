package io.github.sadeghi.online_shop.ui.screens.productScreen.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.screens.cartScreen.CartViewModel
import io.github.sadeghi.online_shop.ui.screens.homeScreen.component.Bestselling
import io.github.sadeghi.online_shop.viewModel.FavoritesViewModel
import io.github.sadeghi.online_shop.viewModel.ProductReviewViewModel
import io.github.sadeghi.online_shop.viewModel.ProductViewModel
import kotlinx.coroutines.launch

@Composable
fun ProductDetailScreen(
    productId: Int,
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val reviewViewModel: ProductReviewViewModel = hiltViewModel()
    val reviews by reviewViewModel.reviews.collectAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()
    val favoritesViewModel: FavoritesViewModel = hiltViewModel()

    val cartViewModel: CartViewModel = hiltViewModel()

    val productViewModel: ProductViewModel = hiltViewModel()

    val products by productViewModel.products.collectAsStateWithLifecycle()

    if (products.isEmpty()) {
        return
    }

    var currentProductIndex by remember {
        mutableIntStateOf(
            products.indexOfFirst { it.id == productId }
                .coerceAtLeast(0)
        )
    }

    val product = products[currentProductIndex]

    val focusManager = LocalFocusManager.current
    val listState = rememberLazyListState()
    val canGoPrevious = currentProductIndex > 0
    val canGoNext = currentProductIndex < products.lastIndex

    LaunchedEffect(product.id) {
        reviewViewModel.loadReviews(product.id)
        reviewViewModel.loadCurrentUser()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
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
                    favoritesViewModel = favoritesViewModel,
                    canGoPrevious = canGoPrevious,
                    canGoNext = canGoNext,
                    onPreviousProduct = {
                        if (canGoPrevious) currentProductIndex--
                    },
                    onNextProduct = {
                        if (canGoNext) currentProductIndex++
                    },
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope
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
                        reviewViewModel.addReview(
                            productId = product.id,
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
                    navController = navController,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope
                )
            }

            item {
                SpacerHeight(30)
            }
        }

        CartButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),

            product = product,

            onAddToCart = { selectedProduct, quantity ->

                cartViewModel.addToCart(
                    product = selectedProduct,
                    quantity = quantity
                )
            },

            onAddedToCart = {

                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "محصول به سبد خرید اضافه شد",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        )

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
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
