package com.compose.motion.launch

/**
 * Defines standard launch styles for [MotionLaunch].
 */
sealed class LaunchStyle {
    /** No entrance or exit animation. */
    object None : LaunchStyle()

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

    /**
     * Futuristic holographic pulse with chromatic aberration and soft glow.
     * Fits well for premium, tech-forward applications.
     */
    object HolographicPulse : LaunchStyle()

    /**
     * Futuristic orbital particles that converge toward the center.
     * Dynamic and kinetic, ideal for AI or data-heavy apps.
     */
    object OrbitalConverge : LaunchStyle()

    /**
     * Futuristic liquid glass morph effect.
     * Starts as a frosted glass blob and smoothly morphs into the content.
     */
    object LiquidGlassMorph : LaunchStyle()
}
