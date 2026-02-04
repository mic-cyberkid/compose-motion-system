package com.compose.motion.effects

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import com.compose.motion.theme.MotionScheme

/**
 * Reusable transition factories for common motion patterns.
 *
 * These transitions are configured using the provided [MotionScheme].
 */
object TransitionEffects {
    /**
     * Combined fade and scale entrance transition.
     */
    fun fadeScaleIn(scheme: MotionScheme): EnterTransition =
        fadeIn(animationSpec = scheme.fastEffectsSpec()) +
                scaleIn(initialScale = 0.95f, animationSpec = scheme.defaultSpatialSpec())

    /**
     * Combined fade and scale exit transition.
     */
    fun fadeScaleOut(scheme: MotionScheme): ExitTransition =
        fadeOut(animationSpec = scheme.fastEffectsSpec()) +
                scaleOut(targetScale = 0.95f, animationSpec = scheme.defaultSpatialSpec())

    /**
     * Simple fade entrance transition.
     */
    fun fadeIn(scheme: MotionScheme): EnterTransition =
        fadeIn(animationSpec = scheme.fastEffectsSpec())

    /**
     * Simple fade exit transition.
     */
    fun fadeOut(scheme: MotionScheme): ExitTransition =
        fadeOut(animationSpec = scheme.fastEffectsSpec())
}
