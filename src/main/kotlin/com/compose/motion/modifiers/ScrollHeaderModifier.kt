package com.compose.motion.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

/**
 * A modifier that applies scale, blur, and scrim effects based on a scroll progress.
 *
 * @param progress 0.0 (expanded) to 1.0 (fully collapsed/scrolled).
 * @param maxBlur Maximum blur radius.
 * @param minScale Minimum scale.
 * @param scrimColor Color of the overlay that fades in as progress increases.
 */
fun Modifier.scrollHeaderEffects(
    progress: () -> Float,
    maxBlur: Float = 20f,
    minScale: Float = 0.9f,
    scrimColor: Color = Color.Black.copy(alpha = 0.4f)
): Modifier = composed {
    this.graphicsLayer {
        val p = progress().coerceIn(0f, 1f)
        val s = 1f - ((1f - minScale) * p)
        scaleX = s
        scaleY = s
        alpha = 1f - (p * 0.5f) // Subtle fade
    }
    .blur(maxBlur.dp * progress())
    .drawWithContent {
        drawContent()
        drawRect(
            color = scrimColor.copy(alpha = scrimColor.alpha * progress()),
            size = size
        )
    }
}
