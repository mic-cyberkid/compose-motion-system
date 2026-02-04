package com.compose.motion.launch

/**
 * Defines standard launch styles for [MotionLaunch].
 */
sealed class LaunchStyle {
    /** Simple fade out when the launch screen is dismissed. */
    object Fade : LaunchStyle()

    /**
     * Fade and scale out when the launch screen is dismissed.
     * Provides a "zoom-in" effect into the app.
     */
    object FadeScale : LaunchStyle()

    /**
     * Slide up when the launch screen is dismissed.
     * Reveals the app content from underneath.
     */
    object SlideUp : LaunchStyle()
}
