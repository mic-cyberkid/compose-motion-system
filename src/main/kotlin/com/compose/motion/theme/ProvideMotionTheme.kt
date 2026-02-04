package com.compose.motion.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * Provides a [MotionTheme] to the [content].
 *
 * This should be placed at the root of your application or around a specific sub-tree
 * to control the motion characteristics (standard vs expressive).
 *
 * @param theme The [MotionTheme] to provide. Defaults to [MotionThemeDefaults.Default].
 * @param content The composable content to provide the theme to.
 */
@Composable
fun ProvideMotionTheme(
    theme: MotionTheme = MotionThemeDefaults.Default,
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
    val Default = MotionTheme(scheme = MotionScheme.standard())

    /** Expressive motion theme, using bouncy springs and fluid transitions. */
    val Expressive = MotionTheme(scheme = MotionScheme.expressive())
}
