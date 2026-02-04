package com.compose.motion.tokens

/**
 * Standard Material 3 motion durations in milliseconds.
 *
 * Durations are categorized into Short, Medium, Long, and ExtraLong,
 * each with four steps to provide a granular range for different animation scales.
 */
object MotionDurations {
    /** 50ms - used for micro-interactions like small fades. */
    const val Short1 = 50
    /** 100ms - used for small element transitions. */
    const val Short2 = 100
    /** 150ms - used for medium-sized element transitions. */
    const val Short3 = 150
    /** 200ms - standard duration for many small UI elements. */
    const val Short4 = 200

    /** 250ms - used for larger element transitions. */
    const val Medium1 = 250
    /** 300ms - standard duration for medium-sized spatial transitions. */
    const val Medium2 = 300
    /** 350ms - used for significant spatial transitions. */
    const val Medium3 = 350
    /** 400ms - used for large-scale spatial transitions. */
    const val Medium4 = 400

    /** 450ms - used for slow, emphasized transitions. */
    const val Long1 = 450
    /** 500ms - standard duration for long, fluid transitions. */
    const val Long2 = 500
    /** 550ms - used for very slow transitions. */
    const val Long3 = 550
    /** 600ms - used for expansive transitions. */
    const val Long4 = 600

    /** 700ms - used for complex, multi-stage transitions. */
    const val ExtraLong1 = 700
    /** 800ms - used for very expansive transitions. */
    const val ExtraLong2 = 800
    /** 900ms - used for slow-motion effects. */
    const val ExtraLong3 = 900
    /** 1000ms - 1 second duration for extremely slow transitions. */
    const val ExtraLong4 = 1000
}
