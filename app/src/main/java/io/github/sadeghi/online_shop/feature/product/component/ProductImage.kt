package io.github.sadeghi.online_shop.feature.product.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import coil3.compose.AsyncImage
import io.github.sadeghi.online_shop.domain.model.Product
import io.github.sadeghi.online_shop.domain.model.ProductReviewUi
import io.github.sadeghi.online_shop.feature.favorites.FavoritesViewModel

@Composable
fun ProductImage(
    product: Product,
    reviews: List<ProductReviewUi>,
    listState: LazyListState,
    favoritesViewModel: FavoritesViewModel,
    onPreviousProduct: () -> Unit,
    onNextProduct: () -> Unit,
    modifier: Modifier = Modifier,
    canGoPrevious: Boolean,
    canGoNext: Boolean,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null
) {

    val constraints = ConstraintSet {

        val box = createRefFor("box")
        constrain(box) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }

        val label = createRefFor("label")
        constrain(label) {
            end.linkTo(box.end)
            top.linkTo(box.top)
            bottom.linkTo(box.bottom)
            verticalBias = 0.1f
        }

        val image = createRefFor("image")
        constrain(image) {
            end.linkTo(box.end)
            top.linkTo(box.top)
            start.linkTo(box.start)
        }

        val iconBack = createRefFor("iconBack")
        constrain(iconBack) {
            start.linkTo(box.start)
            top.linkTo(box.top)
            bottom.linkTo(box.bottom)
        }

        val iconNext = createRefFor("iconNext")
        constrain(iconNext) {
            end.linkTo(box.end)
            top.linkTo(box.top)
            bottom.linkTo(box.bottom)
        }

        val myBox = createRefFor("myBox")
        constrain(myBox) {
            start.linkTo(box.start)
            top.linkTo(box.top)
            bottom.linkTo(box.bottom)
            verticalBias = 0.7f
        }

        val description = createRefFor("description")
        constrain(description) {
            start.linkTo(myBox.start)
            end.linkTo(box.end)
            top.linkTo(myBox.bottom)
        }
    }

    ConstraintLayout(
        constraintSet = constraints
    ) {

        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
                .background(Color(0xFFEBEBEB))
                .size(500.dp)
                .layoutId("box")
                .let { boxModifier ->

                    if (
                        sharedTransitionScope != null &&
                        animatedVisibilityScope != null
                    ) {
                        with(sharedTransitionScope) {
                            boxModifier.sharedElement(
                                sharedContentState = rememberSharedContentState(
                                    key = "product-box-${product.id}"
                                ),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                        }
                    } else {
                        boxModifier
                    }
                }
        )

        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 50.dp,
                        bottomStart = 50.dp
                    )
                )
                .layoutId("label")
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFE32A0D),
                            Color(0xFFFD583D)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${product.discountPercent}%",
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 4.dp
                ),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        val imageModifier = Modifier
            .size(250.dp)
            .layoutId("image")
            .padding(top = 60.dp)

        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            modifier = if (
                sharedTransitionScope != null &&
                animatedVisibilityScope != null
            ) {
                with(sharedTransitionScope) {
                    imageModifier.sharedElement(
                        sharedContentState = rememberSharedContentState(
                            key = "product-image-${product.id}"
                        ),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
            } else {
                imageModifier
            },
            contentScale = ContentScale.Fit
        )

        IconButton(
            onClick = {
                if (canGoPrevious) {
                    onPreviousProduct()
                }
            },
            enabled = canGoPrevious,
            modifier = Modifier.layoutId("iconBack")
        ) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = "محصول قبلی"
            )
        }

        IconButton(
            onClick = {
                if (canGoNext) {
                    onNextProduct()
                }
            },
            enabled = canGoNext,
            modifier = Modifier.layoutId("iconNext")
        ) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "محصول بعدی"
            )
        }

        ProductActionsOverlay(
            modifier = Modifier.layoutId("myBox"),
            reviews = reviews,
            listState = listState,
            productId = product.id,
            favoritesViewModel = favoritesViewModel
        )

        ProductDescription(
            product = product,
            listState = listState,
            modifier = modifier.layoutId("description")
        )
    }
}

