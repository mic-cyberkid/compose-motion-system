package com.compose.motion.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Public, intent-based modifiers for the motion system.
 *
 * This object serves as a namespace for motion-related modifier constants
 * and utility functions.
 */
object MotionModifiers {
    // Intentional placeholder for future common motion modifier utilities
}

/**
 * A modifier that fades the element's opacity based on a [progress] value.
 *
 * Useful for parallax effects, collapsing toolbars, or scroll-driven animations.
 *
 * @param progress A lambda that returns the current progress value (0.0 to 1.0).
 *                 0.0 means fully opaque, 1.0 means fully transparent (or vice versa depending on logic).
 * @param inverted Whether to invert the alpha calculation.
 */
fun Modifier.fadeOnScroll(
    inverted: Boolean = false,
    progress: () -> Float
): Modifier = composed {
    this.graphicsLayer {
        val p = progress().coerceIn(0f, 1f)
        alpha = if (inverted) p else 1f - p
    }
}
