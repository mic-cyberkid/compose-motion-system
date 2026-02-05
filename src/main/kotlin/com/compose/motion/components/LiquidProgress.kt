package com.compose.motion.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import kotlin.math.sin

/**
 * A progress indicator with a liquid-like wave animation.
 *
 * @param progress Current progress (0.0 to 1.0).
 * @param color The color of the "liquid".
 */
@Composable
fun LiquidProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    val infiniteTransition = rememberInfiniteTransition(label = "liquid")
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "waveOffset"
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(20.dp)
    ) {
        val width = size.width
        val height = size.height
        val fillWidth = width * progress
        val waveHeight = 4.dp.toPx()

        val path = Path().apply {
            moveTo(0f, height)
            for (x in 0..fillWidth.toInt()) {
                val y = height - (height * 0.8f) + sin(x.toFloat() / 20f + waveOffset) * waveHeight
                lineTo(x.toFloat(), y)
            }
            lineTo(fillWidth, height)
            close()
        }

        drawPath(path, color)
    }
}
