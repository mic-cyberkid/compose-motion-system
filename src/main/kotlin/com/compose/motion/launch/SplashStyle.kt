package com.compose.motion.launch

/**
 * Defines standard splash and launch styles for [SplashScreen] and [MotionLaunch].
 */
sealed class SplashStyle {
    /** No entrance or exit animation. */
    data object None : SplashStyle()

    /** Simple fade out when the splash screen is dismissed. */
    data object Fade : SplashStyle()

    /**
     * Fade and scale out when the splash screen is dismissed.
     * Provides a "zoom-in" effect into the app.
     */
    data object FadeScale : SplashStyle()

    /**
     * Slide up when the splash screen is dismissed.
     * Reveals the app content from underneath.
     */
    data object SlideUp : SplashStyle()

    /**
     * Futuristic holographic pulse with chromatic aberration and soft glow.
     * Fits well for premium, tech-forward applications.
     */
    data object HolographicPulse : SplashStyle()

    /**
     * Futuristic orbital particles that converge toward the center.
     * Dynamic and kinetic, ideal for AI or data-heavy apps.
     */
    data object OrbitalConverge : SplashStyle()

    /**
     * Futuristic liquid glass morph effect.
     * Starts as a frosted glass blob and smoothly morphs into the content.
     */
    data object LiquidGlassMorph : SplashStyle()

    /**
     * Futuristic aurora-like gradient background with breathing icon effect.
     */
    data object AuroraGradient : SplashStyle()

    /**
     * Futuristic scan-line effect for text and icons.
     */
    data object KineticScan : SplashStyle()

    /**
     * Futuristic glowing reveal that expands from the center.
     */
    data object GlowPulse : SplashStyle()

    /**
     * Cyber-modern Matrix-inspired digital rain effect.
     */
    data object MatrixRain : SplashStyle()

    /**
     * High-end bento-grid layout reveal.
     */
    data object BentoReveal : SplashStyle()

    /**
     * Advanced glass reveal with refraction and soft particles.
     */
    data object GlassReveal : SplashStyle()

    /**
     * Organic morphing shape that dissolves into the content.
     */
    data object OrganicMorph : SplashStyle()

    // --- New Styles for Logo-centric Animations ---

    /** Idea #1 - Logo reveal with motion (fade + bounce). */
    data object LogoReveal : SplashStyle()

    /** Idea #2 - Logo morphs into UI placeholder skeleton. */
    data object LogoMorphToUI : SplashStyle()

    /** Idea #3 - Simple animated logo spin with easing. */
    data object LogoSpin : SplashStyle()

    /** Idea #1 (cont.) - Logo draw path animation. */
    data object LogoDrawPath : SplashStyle()

    // --- New Styles for Brand Story & Narrative ---

    /** Idea #4 - Mascot walking in, playing out brand theme. */
    data object MascotWalkIn : SplashStyle()

    /** Idea #5 - Tagline scene with words animating in. */
    data object TaglineTypewriter : SplashStyle()

    /** Idea #6 - Progressive animation from logo to product symbol. */
    data object ProgressiveBrandReveal : SplashStyle()

    // --- New Styles for Visual Effects ---

    /** Idea #7 - Particles animate around logo then disperse. */
    data object ParticleBurst : SplashStyle()

    /** Idea #8 - Liquid splash or fluid motion transitions. */
    data object LiquidSplash : SplashStyle()

    /** Idea #9 - 3D transformations or perspective logo rotation. */
    data object ThreeDLogoRotation : SplashStyle()

    /** Idea #10 - Full-screen animated artwork (water ripple, gradients). */
    data object FullScreenArtworkLoop : SplashStyle()
}

/**
 * Typealias for backward compatibility with [LaunchStyle].
 */
@Deprecated("Use SplashStyle instead", ReplaceWith("SplashStyle"))
typealias LaunchStyle = SplashStyle
