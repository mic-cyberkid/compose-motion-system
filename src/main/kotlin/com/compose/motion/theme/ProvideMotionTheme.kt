package com.compose.motion.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * Provides a [MotionTheme] to the [content].
 *
 * This should be placed at the root of your application or around a specific sub-tree
 * to control the motion characteristics (standard vs expressive).
 *
 * @param scheme The [MotionScheme] to use.
 * @param style The [MotionStyle] to use.
 * @param content The composable content to provide the theme to.
 */
@Composable
fun ProvideMotionTheme(
    scheme: MotionScheme = MotionScheme.standard(),
    style: MotionStyle = MotionStyle.standard(),
    content: @Composable () -> Unit
) {
    val theme = MotionTheme(scheme = scheme, style = style)
    CompositionLocalProvider(
        LocalMotionTheme provides theme,
        content = content
    )
}

/**
 * Provides a [MotionTheme] to the [content].
 *
 * @param theme The [MotionTheme] to provide.
 * @param content The composable content to provide the theme to.
 */
@Composable
fun ProvideMotionTheme(
    theme: MotionTheme,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalMotionTheme provides theme,
        content = content
    )
}

/**
 * Default configurations for [MotionTheme].
 */
object MotionThemeDefaults {
    /** Standard motion theme, focused on productivity and subtle transitions. */
    val Default = MotionTheme(scheme = MotionScheme.standard(), style = MotionStyle.standard())

    /** Expressive motion theme, using bouncy springs and fluid transitions. */
    val Expressive = MotionTheme(scheme = MotionScheme.expressive(), style = MotionStyle.standard())

    /** Apple-style Glassmorphism theme. */
    val AppleGlass = MotionTheme(scheme = MotionScheme.expressive(), style = MotionStyle.appleGlass())

    /** Cyberpunk Glitch theme. */
    val CyberGlitch = MotionTheme(scheme = MotionScheme.expressive(), style = MotionStyle.cyberGlitch())

    /** Neumorphic theme. */
    val Neumorphic = MotionTheme(scheme = MotionScheme.standard(), style = MotionStyle.neumorphic())

    /** Aura Finance Glass theme. */
    val AuraGlass = MotionTheme(scheme = MotionScheme.expressive(), style = MotionStyle.auraGlass())

    /** Aura Finance Emerald theme. */
    val AuraEmerald = MotionTheme(scheme = MotionScheme.expressive(), style = MotionStyle.auraEmerald())

    /** Aura Finance Neumorphic theme. */
    val AuraNeumorphic = MotionTheme(scheme = MotionScheme.standard(), style = MotionStyle.auraNeumorphic())

    /** Tactile Neumorphic theme. */
    val TactileNeumorphic = MotionTheme(scheme = MotionScheme.standard(), style = MotionStyle.tactileNeumorphic())

    /** visionOS-inspired theme. */
    val VisionOS = MotionTheme(scheme = MotionScheme.expressive(), style = MotionStyle.visionOS())

    /** Chromatic Liquid theme. */
    val ChromaticLiquid = MotionTheme(scheme = MotionScheme.expressive(), style = MotionStyle.chromaticLiquid())
}
