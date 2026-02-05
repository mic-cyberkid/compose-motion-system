package com.compose.motion.effects

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * A modifier that adds a chromatic aberration glow at the edge based on overscroll [offset].
 */
fun Modifier.overscrollGlow(
    offset: Float,
    color: Color = Color.Cyan
): Modifier = composed {
    this.drawBehind {
        if (offset != 0f) {
            val glowHeight = offset.coerceIn(0f, 100f)
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(color.copy(alpha = 0.3f), Color.Transparent),
                    startY = 0f,
                    endY = glowHeight
                ),
                topLeft = Offset.Zero,
                size = Size(size.width, glowHeight)
            )
        }
    }
}
