package com.compose.motion.internal

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.ui.util.lerp as composeLerp

/**
 * Reusable math utilities for motion calculations.
 *
 * These utilities are intended for internal use within the library to
 * simplify common animation math patterns.
 */
internal object AnimationMath {
    /**
     * Linearly interpolates between [start] and [stop] based on [fraction].
     *
     * @param start The starting value.
     * @param stop The ending value.
     * @param fraction The interpolation fraction, usually between 0.0 and 1.0.
     * @return The interpolated value.
     */
    fun lerp(start: Float, stop: Float, fraction: Float): Float {
        return composeLerp(start, stop, fraction)
    }

    /**
     * Maps a value from one range to another.
     *
     * @param value The value to map.
     * @param minIn The minimum of the input range.
     * @param maxIn The maximum of the input range.
     * @param minOut The minimum of the output range.
     * @param maxOut The maximum of the output range.
     * @return The mapped value, coerced within the output range.
     */
    fun mapRange(
        value: Float,
        minIn: Float,
        maxIn: Float,
        minOut: Float,
        maxOut: Float
    ): Float {
        if (maxIn == minIn) return minOut
        val fraction = (value - minIn) / (maxIn - minIn)
        return lerp(minOut, maxOut, fraction.coerceIn(0f, 1f))
    }

    /**
     * Transforms a [progress] value using an [easing] function.
     *
     * @param progress The progress value (0.0 to 1.0).
     * @param easing The easing function to apply. Defaults to [FastOutSlowInEasing].
     * @return The eased progress value.
     */
    fun easedProgress(progress: Float, easing: Easing = FastOutSlowInEasing): Float =
        easing.transform(progress.coerceIn(0f, 1f))
}
