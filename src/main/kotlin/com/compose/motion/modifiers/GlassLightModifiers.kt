package com.compose.motion.modifiers

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.compose.motion.theme.motionStyle

/**
 * Applies a high-end glassmorphism effect with blur, border, and specular highlight.
 *
 * @param shape The shape of the glass surface.
 * @param blurRadius The radius of the background blur.
 * @param borderAlpha The opacity of the glass border.
 * @param edgeHighlight If true, adds a subtle diagonal gradient to simulate glass thickness.
 */
fun Modifier.glassmorphic(
    shape: Shape? = null,
    blurRadius: Dp = 20.dp,
    borderAlpha: Float = 0.1f,
    edgeHighlight: Boolean = true
): Modifier = composed {
    val style = MaterialTheme.motionStyle
    val actualBlur = if (blurRadius == 20.dp) style.blurRadius else blurRadius
    val actualBorderAlpha = if (borderAlpha == 0.1f) style.borderAlpha else borderAlpha

    this.then(
        if (shape != null) Modifier.clip(shape) else Modifier
    )
    .blur(actualBlur)
    .background(
        Brush.linearGradient(
            listOf(
                Color.White.copy(alpha = 0.1f),
                Color.White.copy(alpha = 0.05f)
            )
        )
    )
    .then(
        if (actualBorderAlpha > 0f) {
            Modifier.border(
                width = 0.5.dp,
                brush = Brush.linearGradient(
                    listOf(
                        Color.White.copy(alpha = actualBorderAlpha * 2),
                        Color.White.copy(alpha = actualBorderAlpha)
                    )
                ),
                shape = shape ?: androidx.compose.foundation.shape.RoundedCornerShape(0.dp)
            )
        } else Modifier
    )
    .then(
        if (edgeHighlight) {
            Modifier.drawBehind {
                drawRect(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.White.copy(alpha = 0.1f), Color.Transparent),
                        start = Offset.Zero,
                        end = Offset(size.width, size.height)
                    )
                )
            }
        } else Modifier
    )
}

/**
 * Adds a spotlight effect that follows the user's touch.
 *
 * @param color The color of the spotlight.
 * @param radius The radius of the light.
 * @param intensity The brightness of the light.
 */
fun Modifier.spotlight(
    color: Color = Color.White,
    radius: Dp = 200.dp,
    intensity: Float = 0.3f
): Modifier = composed {
    var touchPos by remember { mutableStateOf(Offset.Unspecified) }
    val animatedPos by animateOffsetAsState(
        targetValue = if (touchPos == Offset.Unspecified) Offset.Zero else touchPos,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "spotlight"
    )

    this.pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                val event = awaitPointerEvent()
                touchPos = event.changes.first().position
            }
        }
    }
    .drawBehind {
        if (touchPos != Offset.Unspecified) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(color.copy(alpha = intensity), Color.Transparent),
                    center = animatedPos,
                    radius = radius.toPx()
                ),
                radius = radius.toPx(),
                center = animatedPos
            )
        }
    }
}
