package io.github.sadeghi.online_shop.ui.screens.productScreen.screen

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class LeftRoundedRightSlantedShape(
    private val leftRadius: Dp = 24.dp,
    private val topEndRadius: Dp = 15.dp,
    private val slantAmount: Dp = 18.dp
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val leftR = with(density) { leftRadius.toPx() }
        val topEndR = with(density) { topEndRadius.toPx() }
        val slant = with(density) { slantAmount.toPx() }

        val path = Path().apply {
            // شروع از بالای چپ (بعد از شعاع)
            moveTo(leftR, 0f)

            // خط بالا تا قبل از گوشه بالا-راست
            lineTo(size.width - slant - topEndR, 0f)

            // قوس بالا-راست (Top End)
            arcTo(
                rect = Rect(
                    left = size.width - slant - 2 * topEndR,
                    top = 0f,
                    right = size.width - slant,
                    bottom = 2 * topEndR
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // خط شیب‌دار سمت راست
            lineTo(size.width, size.height)

            // خط پایین تا گوشه پایین-چپ (تیز)
            lineTo(0f, size.height)

            // خط سمت چپ از پایین به بالا
            lineTo(0f, leftR)

            // قوس بالا-چپ
            arcTo(
                rect = Rect(
                    left = 0f,
                    top = 0f,
                    right = 2 * leftR,
                    bottom = 2 * leftR
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            close()
        }

        return Outline.Generic(path)
    }
}