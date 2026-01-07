package io.github.sadeghi.online_shop.ui.screens.loginscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradientButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFFFE593E), Color(0xFFE02508))
    )

    val disabledGradient = Brush.horizontalGradient(
        listOf(Color(0xFFE0A199), Color(0xFFE09980))
    )

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .background( brush = if (enabled) gradient else disabledGradient,
                shape = RoundedCornerShape(16.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            contentColor = Color.White
        ),

    ) {
        Text(text, color = Color.White,
            style = MaterialTheme.typography.titleMedium)
    }
}
