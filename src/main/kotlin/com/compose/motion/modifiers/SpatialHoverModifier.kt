package com.compose.motion.modifiers

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import com.compose.motion.theme.motionScheme

/**
 * A modifier that adds hover effects (scale, elevation, glow).
 */
fun Modifier.spatialHover(
    scale: Float = 1.05f,
    glowColor: Color = Color.Cyan.copy(alpha = 0.2f)
): Modifier = composed {
    var isHovered by remember { mutableStateOf(false) }
    val scheme = MaterialTheme.motionScheme

    val animatedScale by animateFloatAsState(
        targetValue = if (isHovered) scale else 1f,
        animationSpec = scheme.fastSpatialSpec(),
        label = "hoverScale"
    )

    this.pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                val event = awaitPointerEvent()
                when (event.type) {
                    PointerEventType.Enter -> isHovered = true
                    PointerEventType.Exit -> isHovered = false
                }
            }
        }
    }
    .graphicsLayer {
        scaleX = animatedScale
        scaleY = animatedScale
    }
    .drawBehind {
        if (isHovered) {
            drawCircle(
                color = glowColor,
                radius = size.maxDimension * 0.6f,
                center = center
            )
        }
    }
}
