package com.compose.motion.tokens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring

/**
 * Standard motion spring configurations.
 *
 * Springs provide physics-based motion which feels more fluid and natural than
 * fixed-duration tweens.
 */
object MotionSprings {
    /**
     * Default spring: balanced damping and stiffness for general use.
     */
    val Default = spring<Float>(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessMedium
    )

    /**
     * Bouncy spring: lower damping for a playful, expressive feel.
     */
    val Bouncy = spring<Float>(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow
    )

    /**
     * Soft spring: lower stiffness for a slow, gentle transition.
     */
    val Soft = spring<Float>(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessLow
    )
}
