package io.github.sadeghi.online_shop.ui.component.product.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import io.github.sadeghi.online_shop.R
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.SpacerWidth
import io.github.sadeghi.online_shop.ui.component.product.Product
import kotlinx.coroutines.CoroutineScope

@Composable
fun ProductImage(
    product: Product,
    reviews: List<ProductReview>,
    listState: LazyListState,
    modifier: Modifier = Modifier
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
        constraintSet = constraints,

        ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color(0xFFEBEBEB))
                .size(500.dp)
                .layoutId("box")

        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp))
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
                text = "10%",
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }


        Image(
            painter = painterResource(product.image),
            contentDescription = product.title,
            modifier = Modifier
                .size(250.dp)
                .layoutId("image")
                .padding(top = 60.dp),
            contentScale = ContentScale.Crop
        )

        IconButton(
            onClick = {
                // محصول قبلی
            },
            modifier = Modifier

                .layoutId("iconBack")

        ) {

            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = "محصول قبلی"
            )
        }

        IconButton(
            onClick = {
                // محصول بعدی
            },
            modifier = Modifier

                .layoutId("iconNext")

        ) {

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "محصول بعدی"
            )
        }

        ProductActionsOverlay(
            modifier = Modifier
                .layoutId("myBox"),
            reviews = reviews,
            listState = listState,

            )

        ProductDescription(
            product = product,
            listState = listState,
            modifier = modifier.layoutId("description")
        )


    }
}

