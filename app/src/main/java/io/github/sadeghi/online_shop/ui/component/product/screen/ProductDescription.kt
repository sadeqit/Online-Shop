package io.github.sadeghi.online_shop.ui.component.product.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.product.Product
import kotlinx.coroutines.launch

@SuppressLint("FrequentlyChangingValue")
@Composable
fun ProductDescription(
    product: Product,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {

    val scope = rememberCoroutineScope()

    var selectedTab by remember {
        mutableStateOf("توضیحات")
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topEnd = 30.dp,
                    bottomStart = 30.dp,
                    bottomEnd = 30.dp
                )
            )
            .background(Color.White)
            .padding(top = 20.dp)
    ) {

        Text(
            text = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right
        )

        SpacerHeight(16)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "محصولات مشابه",
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(0xFFF8F7F7))
                    .border(
                        width = 1.dp,
                        color = if (selectedTab == "محصولات مشابه")
                            Color(0xFFEF472C)
                        else
                            Color.Transparent,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable {
                        selectedTab = "محصولات مشابه"

                        scope.launch {
                            listState.scrollToItem(9)
                        }
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 12.sp,
                color = if (selectedTab == "محصولات مشابه")
                    Color(0xFFEF472C)
                else
                    Color.Black,
                textAlign = TextAlign.Center
            )

            Text(
                text = "نظرات",
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(0xFFF8F7F7))
                    .border(
                        width = 1.dp,
                        color = if (selectedTab == "نظرات")
                            Color(0xFFEF472C)
                        else
                            Color.Transparent,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable {
                        selectedTab = "نظرات"

                        scope.launch {
                            listState.scrollToItem(6)
                        }
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 12.sp,
                color = if (selectedTab == "نظرات")
                    Color(0xFFEF472C)
                else
                    Color.Black,
                textAlign = TextAlign.Center
            )

            Text(
                text = "ویژگی ها",
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(0xFFF8F7F7))
                    .border(
                        width = 1.dp,
                        color = if (selectedTab == "ویژگی ها")
                            Color(0xFFEF472C)
                        else
                            Color.Transparent,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable {
                        selectedTab = "ویژگی ها"

                        scope.launch {
                            listState.scrollToItem(2)
                        }
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 12.sp,
                color = if (selectedTab == "ویژگی ها")
                    Color(0xFFEF472C)
                else
                    Color.Black,
                textAlign = TextAlign.Center
            )

            Text(
                text = "توضیحات",
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(0xFFF8F7F7))
                    .border(
                        width = 1.dp,
                        color = if (selectedTab == "توضیحات")
                            Color(0xFFEF472C)
                        else
                            Color.Transparent,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable {
                        selectedTab = "توضیحات"

                        scope.launch {
                            listState.scrollToItem(1)
                        }
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 12.sp,
                color = if (selectedTab == "توضیحات")
                    Color(0xFFEF472C)
                else
                    Color.Black,
                textAlign = TextAlign.Center
            )


            /* Text(
                 text = "توضیحات",
                 modifier = Modifier
                     .clip(RoundedCornerShape(30.dp))
                     .background(Color(0xFFF8F7F7))
                     .padding(horizontal = 16.dp, vertical = 8.dp),
                 fontSize = 12.sp,
                 textAlign = TextAlign.Center
             )*/
        }

        SpacerHeight(20)

        Text(
            text = "توضیحات",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right
        )
        SpacerHeight(12)

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Text(
                text = "با توجه به تفاوت رنگ‌ها در صفحه نمایش دستگاه‌های مختلف، ممکن است رنگ محصولات در تصویر تا 20٪ با واقعیت متفاوت باشد. دارای 5 رنگ جذاب. تمام نخ پنبه. پارچه پیراهن کشمیر. دم‌دست و پایین بلوز کشباف. سایز بندی: M تا 4XL",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 12.sp,
                textAlign = TextAlign.Justify
            )
        }
        SpacerHeight(30)


    }
}

