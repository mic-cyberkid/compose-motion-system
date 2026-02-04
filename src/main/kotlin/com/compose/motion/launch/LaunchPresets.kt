package com.compose.motion.launch

/**
 * Defines standard launch styles for [MotionLaunch].
 */
sealed class LaunchStyle {
    /** No entrance or exit animation. */
    data object None : LaunchStyle()

    /** Simple fade out when the launch screen is dismissed. */
    data object Fade : LaunchStyle()

    /**
     * Fade and scale out when the launch screen is dismissed.
     * Provides a "zoom-in" effect into the app.
     */
    data object FadeScale : LaunchStyle()

    /**
     * Slide up when the launch screen is dismissed.
     * Reveals the app content from underneath.
     */
    data object SlideUp : LaunchStyle()

    /**
     * Futuristic holographic pulse with chromatic aberration and soft glow.
     * Fits well for premium, tech-forward applications.
     */
    data object HolographicPulse : LaunchStyle()

    /**
     * Futuristic orbital particles that converge toward the center.
     * Dynamic and kinetic, ideal for AI or data-heavy apps.
     */
    data object OrbitalConverge : LaunchStyle()

    /**
     * Futuristic liquid glass morph effect.
     * Starts as a frosted glass blob and smoothly morphs into the content.
     */
    data object LiquidGlassMorph : LaunchStyle()

    /**
     * Futuristic aurora-like gradient background with breathing icon effect.
     */
    data object AuroraGradient : LaunchStyle()

    /**
     * Futuristic scan-line effect for text and icons.
     */
    data object KineticScan : LaunchStyle()

    /**
     * Futuristic glowing reveal that expands from the center.
     */
    data object GlowPulse : LaunchStyle()

    /**
     * Cyber-modern Matrix-inspired digital rain effect.
     */
    data object MatrixRain : LaunchStyle()

    /**
     * High-end bento-grid layout reveal.
     */
    data object BentoReveal : LaunchStyle()

    /**
     * Advanced glass reveal with refraction and soft particles.
     */
    data object GlassReveal : LaunchStyle()

    /**
     * Organic morphing shape that dissolves into the content.
     */
    data object OrganicMorph : LaunchStyle()
}
