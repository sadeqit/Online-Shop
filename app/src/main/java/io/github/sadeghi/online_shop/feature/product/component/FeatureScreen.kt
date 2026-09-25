package io.github.sadeghi.online_shop.feature.product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.core.ui.SpacerHeight

@Composable
fun FeatureScreen() {

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(Color.White)
            .padding(top = 20.dp, bottom = 20.dp)
    ) {

        Text(
            text = "ویژگی ها",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right
        )
        SpacerHeight(12)

        Text(
            text = "وزن خالص: 340 گرم",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .size(300.dp, 50.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF8F7F7))
                .padding(12.dp),
            fontSize = 13.sp,
            textAlign = TextAlign.Right
        )
        Text(
            text = "وزن با بسته بندی: 400 گرم",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            fontSize = 13.sp,
            textAlign = TextAlign.Right
        )
        Text(
            text = "وزن خالص: 340 گرم",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .size(300.dp, 50.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF8F7F7))
                .padding(12.dp),
            fontSize = 13.sp,
            textAlign = TextAlign.Right
        )
        Text(
            text = "وزن با بسته بندی: 400 گرم",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            fontSize = 13.sp,
            textAlign = TextAlign.Right
        )

    }
}