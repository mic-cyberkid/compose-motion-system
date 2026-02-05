package com.compose.motion.effects

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.random.Random

/**
 * A modifier that applies a glitch effect.
 */
fun Modifier.glitch(
    enabled: Boolean = true,
    intensity: Float = 10f
): Modifier = composed {
    if (!enabled) return@composed this

    val infiniteTransition = rememberInfiniteTransition(label = "glitch")
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(100, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "glitchOffset"
    )

    this.graphicsLayer {
        if (Random.nextFloat() > 0.8f) {
            translationX = (Random.nextFloat() - 0.5f) * intensity
            alpha = 0.8f + Random.nextFloat() * 0.2f
        } else {
            translationX = 0f
            alpha = 1f
        }
    }
}
