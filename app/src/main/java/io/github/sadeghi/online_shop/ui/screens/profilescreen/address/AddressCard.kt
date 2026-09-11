package io.github.sadeghi.online_shop.ui.screens.profilescreen.address

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeghi.online_shop.ui.component.GradientButton
import io.github.sadeghi.online_shop.ui.component.SpacerHeight
import io.github.sadeghi.online_shop.ui.component.SpacerWidth
import io.github.sadeghi.online_shop.ui.theme.background
import io.github.sadeghi.online_shop.ui.theme.orange

@Composable
fun AddressCard(
    address: Address,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onDefaultChange: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (address.isDefault) orange else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("گیرنده : ")
                }

                append(address.receiver)
            },
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            textAlign = TextAlign.Right
        )

        SpacerHeight(10)
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("آدرس : ")
                }

                append(address.address)
            },
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            textAlign = TextAlign.Right
        )


        SpacerHeight(10)
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("کد پستی : ")
                }

                append(address.postalCode)
            },
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            textAlign = TextAlign.Right
        )


        SpacerHeight(10)
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("شماره همراه : ")
                }

                append(address.phoneNumber)
            },
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp,
            textAlign = TextAlign.Right
        )


        SpacerHeight(16)

        HorizontalDivider()

        SpacerHeight(12)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(verticalAlignment = Alignment.CenterVertically)
            {

                RadioButton(
                    selected = address.isDefault,
                    onClick = onDefaultChange,
                    modifier = Modifier.size(20.dp)
                )
                SpacerWidth(10)
                Text(
                    text = "آدرس پیش فرض",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp))
            {

                Button(
                    onClick = onEditClick,
                    modifier = Modifier
                        .width(80.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = background
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "ویرایش",
                        fontWeight = FontWeight.Bold,
                        color = orange,
                        fontSize = 16.sp
                    )
                }

                Button(
                    onClick = onDeleteClick,
                    modifier = Modifier
                        .width(80.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF3F2F1)
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "حذف",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6F6D6D),
                        fontSize = 16.sp
                    )
                }
            }
        }

    }
}