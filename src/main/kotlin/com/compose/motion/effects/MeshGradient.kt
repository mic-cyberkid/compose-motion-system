package com.compose.motion.effects

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.cos
import kotlin.math.sin

/**
 * A subtle, breathing animated gradient background that feels like a fluid mesh.
 */
@Composable
fun MeshGradient(
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "mesh")

    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * Math.PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        // Background base
        drawRect(colors[0])

        // First moving blob
        val center1 = Offset(
            x = (0.5f + 0.3f * cos(time)) * width,
            y = (0.5f + 0.2f * sin(time * 0.8f)) * height
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(colors[1], Color.Transparent),
                center = center1,
                radius = width * 0.8f
            ),
            center = center1,
            radius = width * 0.8f
        )

        // Second moving blob
        val center2 = Offset(
            x = (0.5f + 0.2f * sin(time * 1.2f)) * width,
            y = (0.5f + 0.3f * cos(time * 0.7f)) * height
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(colors[2], Color.Transparent),
                center = center2,
                radius = width * 0.7f
            ),
            center = center2,
            radius = width * 0.7f
        )
    }
}
