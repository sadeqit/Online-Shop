package io.github.sadeghi.online_shop.ui.screens.cartScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.screens.profilescreen.address.Address
import io.github.sadeghi.online_shop.ui.theme.orange

/*
@Composable
fun CheckoutAddressCard(
    address: Address,
    selected: Boolean,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) {
                    orange
                } else {
                    Color.LightGray
                },
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                onClick()
            }
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = address.receiver,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right
            )

            RadioButton(
                selected = selected,
                onClick = onClick
            )
        }

        SpacerHeight(10)

        Text(
            text = address.address,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Right
        )

        SpacerHeight(10)

        Text(
            text = "کد پستی : ${address.postalCode}",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 13.sp,
            color = Color.Gray,
            textAlign = TextAlign.Right
        )

        SpacerHeight(8)

        Text(
            text = "شماره همراه : ${address.phoneNumber}",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 13.sp,
            color = Color.Gray,
            textAlign = TextAlign.Right
        )
    }
}*/
