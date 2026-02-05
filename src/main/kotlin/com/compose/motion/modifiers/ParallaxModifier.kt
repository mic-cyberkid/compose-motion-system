package com.compose.motion.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

/**
 * A modifier that applies a parallax effect based on a [progress] value.
 *
 * @param intensity The multiplier for the parallax shift. Defaults to 50f.
 * @param progress A lambda that returns the current progress (usually from a scroll state).
 */
fun Modifier.parallax(
    intensity: Float = 50f,
    progress: () -> Float
): Modifier = composed {
    this.graphicsLayer {
        translationY = progress() * intensity
    }
}
