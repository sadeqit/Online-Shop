package io.github.sadeghi.online_shop.feature.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import io.github.sadeghi.online_shop.core.ui.SpacerHeight
import io.github.sadeghi.online_shop.feature.profile.component.HeaderProfile
import io.github.sadeghi.online_shop.feature.orders.component.OrderItem
import io.github.sadeghi.online_shop.feature.profile.ProfileViewModel

@Composable
fun MyOrdersScreen(
    profileViewModel: ProfileViewModel,
    viewModel: OrderViewModel = hiltViewModel()
) {

    val orders by viewModel.orders.collectAsState()

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Ltr
            ) {
                HeaderProfile(
                    compact = true,
                    profileViewModel = profileViewModel
                )
            }

            SpacerHeight(40)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {

                Text(
                    text = "سفارشات من",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right
                )

                SpacerHeight(20)

                if (orders.isEmpty()) {

                    Text(
                        text = "هنوز سفارشی ثبت نکرده‌اید",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 16.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )

                } else {

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        items(
                            items = orders,
                            key = { it.id }
                        ) { order ->

                            OrderItem(
                                order = order
                            )

                            SpacerHeight(12)
                        }
                    }
                }
            }
        }
    }
}