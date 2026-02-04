package com.compose.motion.tokens

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing

/**
 * Standard Material 3 motion easings.
 *
 * These easings control the rate of change for animations, providing a natural
 * and consistent feel across the application.
 */
object MotionEasings {
    /**
     * Standard easing: used for elements that move within the screen.
     * Equivalent to Material 3 "Standard" easing.
     */
    val Standard: Easing = CubicBezierEasing(0.2f, 0f, 0f, 1f)

    /**
     * Standard accelerate: used for elements that exit the screen.
     * Equivalent to Material 3 "Standard Accelerate" easing.
     */
    val StandardAccelerate: Easing = CubicBezierEasing(0.3f, 0f, 1f, 1f)

    /**
     * Standard decelerate: used for elements that enter the screen.
     * Equivalent to Material 3 "Standard Decelerate" easing.
     */
    val StandardDecelerate: Easing = CubicBezierEasing(0f, 0f, 0f, 1f)

    /**
     * Emphasized easing: used for transitions that should draw more attention.
     * Often used with longer durations.
     */
    val Emphasized: Easing = CubicBezierEasing(0.2f, 0f, 0f, 1f)

    /**
     * Emphasized accelerate: used for elements exiting with emphasis.
     */
    val EmphasizedAccelerate: Easing = CubicBezierEasing(0.3f, 0f, 0.8f, 0.15f)

    /**
     * Emphasized decelerate: used for elements entering with emphasis.
     */
    val EmphasizedDecelerate: Easing = CubicBezierEasing(0.05f, 0.7f, 0.1f, 1f)

    /** Linear easing: used for simple fades and color changes. */
    val Linear: Easing = LinearEasing
}
