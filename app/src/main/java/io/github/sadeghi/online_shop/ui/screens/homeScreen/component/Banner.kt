package io.github.sadeghi.online_shop.ui.screens.homeScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import io.github.sadeghi.online_shop.R


@Composable
fun BannerScreen() {

    val constraints = ConstraintSet {

        val image = createRefFor("image")
        constrain(image) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }

        val offer = createRefFor("offer")
        constrain(offer) {
            start.linkTo(image.start)
            end.linkTo(image.end)
            top.linkTo(image.top)
            bottom.linkTo(image.top)
            horizontalBias = 0.08f
        }

        val boxL = createRefFor("boxL")
        constrain(boxL) {
            start.linkTo(image.start)
            end.linkTo(image.start)
            top.linkTo(image.top)
            bottom.linkTo(image.bottom)

        }
        val boxR = createRefFor("boxR")
        constrain(boxR) {
            start.linkTo(image.end)
            end.linkTo(image.end)
            top.linkTo(image.top)
            bottom.linkTo(image.bottom)

        }

    }

    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .layoutId("image")

        ) {
            Image(
                painter = painterResource(R.drawable.baner),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .layoutId("offer")
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFD583D),
                            Color(0xFFE32A0D)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "تخفیف ویژه",
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .size(height = 40.dp, width = 13.dp)
                .background(Color(0xFFFCF3EC))
                .layoutId("boxL")
                .clickable {}
            ,
            contentAlignment = Alignment.Center

        ) {
            Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Arrow")
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .size(height = 40.dp, width = 13.dp)
                .background(Color(0xFFFCF3EC))
                .layoutId("boxR")
                .clickable {}
            ,
            contentAlignment = Alignment.Center

        ) {
            Icon( Icons.Default.ArrowBackIosNew, contentDescription = "Arrow",modifier = Modifier.rotate(180f))
        }

    }
}