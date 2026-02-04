package com.compose.motion.effects

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.compose.motion.theme.motionScheme
import com.compose.motion.theme.fastSpatial
import com.compose.motion.theme.defaultSpatial
import com.compose.motion.theme.slowSpatial

/**
 * Reusable animation spec factories for spatial animations (bounds, shapes, positions).
 *
 * These properties provide easy access to the current [MotionTheme]'s spatial specs.
 */
object SpatialEffects {
    /** Fast spatial animation spec. */
    val fast: FiniteAnimationSpec<Float>
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.motionScheme.fastSpatial

    /** Default spatial animation spec. */
    val default: FiniteAnimationSpec<Float>
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.motionScheme.defaultSpatial

    /** Slow spatial animation spec. */
    val slow: FiniteAnimationSpec<Float>
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.motionScheme.slowSpatial
}
