package com.compose.motion.effects

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * A modifier that applies a skeleton shimmer effect.
 */
fun Modifier.shimmer(
    visible: Boolean = true,
    baseColor: Color? = null,
    shimmerColor: Color? = null
): Modifier = composed {
    if (!visible) return@composed this

    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val actualBaseColor = baseColor ?: MaterialTheme.colorScheme.surfaceVariant
    val actualShimmerColor = shimmerColor ?: MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.1f)

    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerProgress"
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            actualBaseColor,
            actualShimmerColor,
            actualBaseColor
        ),
        start = Offset(progress - 200f, progress - 200f),
        end = Offset(progress, progress)
    )

    this.background(brush)
}
