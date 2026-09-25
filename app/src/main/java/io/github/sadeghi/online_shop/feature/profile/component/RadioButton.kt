package io.github.sadeghi.online_shop.feature.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.core.ui.SpacerWidth

@Composable
fun RadioButton(
    gender: String,
    onGenderChange: (String) -> Unit,
    hasError: Boolean = false
) {

    val radioColor = if (hasError) Color.Red else Color.Black

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Text(text = "مرد")

        RadioButton(
            selected = gender == "مرد",
            onClick = {
                onGenderChange("مرد")
            },
            modifier = Modifier.scale(0.85f),
            colors = RadioButtonDefaults.colors(
                selectedColor = radioColor,
                unselectedColor = radioColor
            )
        )

        SpacerWidth(12)

        Text(text = "زن")

        RadioButton(
            selected = gender == "زن",
            onClick = {
                onGenderChange("زن")
            },
            modifier = Modifier.scale(0.85f),
            colors = RadioButtonDefaults.colors(
                selectedColor = radioColor,
                unselectedColor = radioColor
            )
        )
    }
}