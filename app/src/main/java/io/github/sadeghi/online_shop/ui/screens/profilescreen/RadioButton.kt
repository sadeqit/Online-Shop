package io.github.sadeghi.online_shop.ui.screens.profilescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.sadeghi.online_shop.ui.component.SpacerWidth

@Composable
fun RadioButton() {
    val statType = remember { mutableStateListOf(false, false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    )
    {

        Text(text = "مرد")
        RadioButton(
            selected = statType[0],
            onClick = {
                statType[0] = true
                statType[1] = false
            },
            modifier = Modifier.scale(0.85f),
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,

                )
        )
        SpacerWidth(12)
        Text(text = "زن")

        RadioButton(
            selected = statType[1],
            onClick = {
                statType[0] = false
                statType[1] = true
            },
            modifier = Modifier.scale(0.85f),
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,)
        )

    }

}
