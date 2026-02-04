package com.compose.motion.effects

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.compose.motion.theme.motionScheme
import com.compose.motion.theme.fastEffects
import com.compose.motion.theme.defaultEffects
import com.compose.motion.theme.slowEffects

/**
 * Reusable animation spec factories for effect animations (alpha, color, elevation).
 *
 * These properties provide easy access to the current [MotionTheme]'s effect specs.
 */
object EffectEffects {
    /** Fast effect animation spec. */
    val fast: FiniteAnimationSpec<Float>
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.motionScheme.fastEffects

    /** Default effect animation spec. */
    val default: FiniteAnimationSpec<Float>
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.motionScheme.defaultEffects

    /** Slow effect animation spec. */
    val slow: FiniteAnimationSpec<Float>
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.motionScheme.slowEffects
}
