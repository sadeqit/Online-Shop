package io.github.sadeghi.online_shop.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import io.github.sadeghi.online_shop.ui.theme.background
import io.github.sadeghi.online_shop.ui.theme.shape

@Preview(showBackground = true)
@Composable
fun BGShape(
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier.fillMaxSize()

    ) {

        // رنگ‌ها
        val backgroundColor = background
        val shapeColor = shape

        // بک‌گراند اصلی
        drawRect(
            color = backgroundColor,
            size = size
        )

        // دایره بالا راست
        drawCircle(
            color = shapeColor,
            radius = size.width * 0.6f,
            center = Offset(
                x = size.width * 1.05f,
                y = size.height * -0.05f
            )
        )

        // دایره وسط چپ
        drawCircle(
            color = shapeColor,
            radius = size.width * 0.20f,
            center = Offset(
                x = size.width * 0.01f,
                y = size.height * 0.3f
            )
        )

        // دایره پایین چپ بزرگ
        drawCircle(
            color = shapeColor,
            radius = size.width * 1.0f,
            center = Offset(
                x = size.width * -0.3f,
                y = size.height * 1.15f
            )
        )
    }
}
