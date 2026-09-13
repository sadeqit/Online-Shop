package io.github.sadeghi.online_shop.ui.screens.productScreen.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.Icon
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
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.SpacerWidth

@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    showBookmark: Boolean = false
) {

    val constraints = ConstraintSet {

        val box = createRefFor("box")
        constrain(box) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
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
        val describe = createRefFor("describe")
        constrain(describe) {
            start.linkTo(box.start)
            end.linkTo(box.end)
            top.linkTo(box.top)
            bottom.linkTo(box.bottom)
            verticalBias = 0.9f


        }
        val plus = createRefFor("plus")
        constrain(plus) {
            start.linkTo(describe.start)
            end.linkTo(describe.start)
            top.linkTo(describe.top)
            bottom.linkTo(describe.bottom)

        }

        val bookmark = createRefFor("bookmark")
        constrain(bookmark) {
            start.linkTo(box.start, margin = 10.dp)
            top.linkTo(box.top, margin = 10.dp)
        }

    }

    ConstraintLayout(
        constraintSet = constraints,
        modifier = modifier.clickable { onClick() }

    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFEBEBEB))
                .size(width = 200.dp, height = 250.dp)

                .layoutId("box")

        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp))
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
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        if (showBookmark) {
            Icon(
                imageVector = Icons.Outlined.Bookmark,
                contentDescription = "بوکمارک",
                tint = Color.Black,
                modifier = Modifier
                    .size(28.dp)
                    .layoutId("bookmark")
            )
        }
        Image(
            painter = painterResource(product.image),
            contentDescription = product.title,
            modifier = Modifier
                .size(135.dp)
                .layoutId("image")
                .padding(top =10.dp),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .size(width = 170.dp, height = 90.dp)
                .layoutId("describe")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 8.dp,
                        vertical = 7.dp
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // اسم محصول - همیشه بالا
                Text(
                    text = product.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    maxLines = 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                )

                // قیمت - همیشه پایین
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "تومان",
                        fontSize = 10.sp,
                        color = Color.Black,
                        maxLines = 1
                    )

                    SpacerWidth(5)

                    Text(
                        text = product.price,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 1
                    )

                    SpacerWidth(5)

                    Text(
                        text = product.oldPrice,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough,
                        maxLines = 1
                    )
                }
            }
        }

        /*Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White)
                .size(width = 170.dp, height = 90.dp)
                .layoutId("describe")

        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(vertical = 7.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = product.title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)

                )
                SpacerHeight(30)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "تومان ",
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                    SpacerWidth(3)
                    Text(
                        text = product.price,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    SpacerWidth(8)
                    Text(
                        text = product.oldPrice,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough


                    )

                }
            }
        }*/

        Box(
            modifier = Modifier
                .layoutId("plus")
                .size(27.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFD583D),
                            Color(0xFFE32A0D)
                        )
                    )
                )
                .clickable {},
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Add, "", tint = Color.White)
        }


    }
}
