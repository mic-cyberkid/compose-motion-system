package com.compose.motion.theme

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import com.compose.motion.tokens.MotionDurations
import com.compose.motion.tokens.MotionEasings

/**
 * Motion scheme providing themed [FiniteAnimationSpec]s for spatial (bounds/shape-changing)
 * and effect (color/alpha/elevation) animations.
 *
 * This interface follows the Material 3 motion system principles, providing three levels
 * of speed (fast, default, slow) for two primary types of animations:
 * - **Spatial**: Used for movement, resizing, and shape changes.
 * - **Effects**: Used for color changes, alpha fades, and elevation shifts.
 */
@JvmDefaultWithCompatibility
interface MotionScheme {
    /** Returns an [FiniteAnimationSpec] for fast spatial transitions. */
    fun <T> fastSpatialSpec(): FiniteAnimationSpec<T>
    /** Returns an [FiniteAnimationSpec] for standard spatial transitions. */
    fun <T> defaultSpatialSpec(): FiniteAnimationSpec<T>
    /** Returns an [FiniteAnimationSpec] for slow spatial transitions. */
    fun <T> slowSpatialSpec(): FiniteAnimationSpec<T>

    /** Returns an [FiniteAnimationSpec] for fast effect transitions (alpha/color). */
    fun <T> fastEffectsSpec(): FiniteAnimationSpec<T>
    /** Returns an [FiniteAnimationSpec] for standard effect transitions (alpha/color). */
    fun <T> defaultEffectsSpec(): FiniteAnimationSpec<T>
    /** Returns an [FiniteAnimationSpec] for slow effect transitions (alpha/color). */
    fun <T> slowEffectsSpec(): FiniteAnimationSpec<T>

    /**
     * Factory for creating standard Material [MotionScheme]s.
     */
    companion object {
        /**
         * Standard motion scheme: uses subtle easings and durations, ideal for functional,
         * productivity-focused interfaces.
         */
        fun standard(): MotionScheme = StandardMotionScheme

        /**
         * Expressive motion scheme: uses bouncier springs and more fluid durations,
         * ideal for consumer-facing apps that want more personality.
         */
        fun expressive(): MotionScheme = ExpressiveMotionScheme
    }
}

/** Convenience property for [MotionScheme.fastSpatialSpec] with [Float] type. */
val MotionScheme.fastSpatial: FiniteAnimationSpec<Float> get() = fastSpatialSpec()
/** Convenience property for [MotionScheme.defaultSpatialSpec] with [Float] type. */
val MotionScheme.defaultSpatial: FiniteAnimationSpec<Float> get() = defaultSpatialSpec()
/** Convenience property for [MotionScheme.slowSpatialSpec] with [Float] type. */
val MotionScheme.slowSpatial: FiniteAnimationSpec<Float> get() = slowSpatialSpec()

/** Convenience property for [MotionScheme.fastEffectsSpec] with [Float] type. */
val MotionScheme.fastEffects: FiniteAnimationSpec<Float> get() = fastEffectsSpec()
/** Convenience property for [MotionScheme.defaultEffectsSpec] with [Float] type. */
val MotionScheme.defaultEffects: FiniteAnimationSpec<Float> get() = defaultEffectsSpec()
/** Convenience property for [MotionScheme.slowEffectsSpec] with [Float] type. */
val MotionScheme.slowEffects: FiniteAnimationSpec<Float> get() = slowEffectsSpec()

private object StandardMotionScheme : MotionScheme {
    override fun <T> fastSpatialSpec(): FiniteAnimationSpec<T> =
        tween(durationMillis = MotionDurations.Short2, easing = MotionEasings.Standard)

    override fun <T> defaultSpatialSpec(): FiniteAnimationSpec<T> =
        tween(durationMillis = MotionDurations.Medium2, easing = MotionEasings.Standard)

    override fun <T> slowSpatialSpec(): FiniteAnimationSpec<T> =
        tween(durationMillis = MotionDurations.Long2, easing = MotionEasings.Standard)

    override fun <T> fastEffectsSpec(): FiniteAnimationSpec<T> =
        tween(durationMillis = MotionDurations.Short1, easing = MotionEasings.Linear)

    override fun <T> defaultEffectsSpec(): FiniteAnimationSpec<T> =
        tween(durationMillis = MotionDurations.Medium1, easing = MotionEasings.Standard)

    override fun <T> slowEffectsSpec(): FiniteAnimationSpec<T> =
        tween(durationMillis = MotionDurations.Long1, easing = MotionEasings.Standard)
}

private object ExpressiveMotionScheme : MotionScheme {
    override fun <T> fastSpatialSpec(): FiniteAnimationSpec<T> =
        spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessMedium)

    override fun <T> defaultSpatialSpec(): FiniteAnimationSpec<T> =
        spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow)

    override fun <T> slowSpatialSpec(): FiniteAnimationSpec<T> =
        spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessVeryLow)

    override fun <T> fastEffectsSpec(): FiniteAnimationSpec<T> =
        tween(durationMillis = MotionDurations.Short1, easing = MotionEasings.Emphasized)

    override fun <T> defaultEffectsSpec(): FiniteAnimationSpec<T> =
        tween(durationMillis = MotionDurations.Medium1, easing = MotionEasings.Emphasized)

    override fun <T> slowEffectsSpec(): FiniteAnimationSpec<T> =
        tween(durationMillis = MotionDurations.Long1, easing = MotionEasings.Emphasized)
}
