package io.github.sadeghi.online_shop.ui.screens.homeScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun BannerScreen() {

    val images = listOf(
        R.drawable.baner,
        R.drawable.baner2,
        R.drawable.baner3,
        R.drawable.baner4
    )
    val pageCount = images.size
    val startIndex = Int.MAX_VALUE / 2

    val pagerState = rememberPagerState(
        initialPage = startIndex - (startIndex % pageCount),
        pageCount = { Int.MAX_VALUE }
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000.milliseconds)
            if (!pagerState.isScrollInProgress) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }

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
        val indicator = createRefFor("indicator")
        constrain(indicator) {
            start.linkTo(image.start)
            end.linkTo(image.end)
            bottom.linkTo(image.bottom, margin = 12.dp)
        }

    }
    ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {

        Box(
            modifier = Modifier
                .layoutId("image")

        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val actualPage = page % pageCount
                Image(
                    painter = painterResource(images[actualPage]),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
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
                .clickable {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                },
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
                .clickable {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
            contentAlignment = Alignment.Center

        ) {
            Icon(
                Icons.Default.ArrowBackIosNew,
                contentDescription = "Arrow",
                modifier = Modifier.rotate(180f)
            )
        }

        Row(
            modifier = Modifier
                .layoutId("indicator"),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val actualPage = pagerState.currentPage % pageCount
            repeat(pageCount) { index ->
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(
                            if (actualPage == index) Color.White
                            else Color.White.copy(alpha = 0.4f)
                        )
                )
            }
        }
    }
}