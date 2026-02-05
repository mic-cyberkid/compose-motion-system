package com.compose.motion.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Defines the motion theme for the application.
 *
 * @property scheme The [MotionScheme] to use for animations. Defaults to [MotionScheme.standard].
 * @property style The [MotionStyle] to use for surface treatments and effects. Defaults to [MotionStyle.standard].
 */
@Immutable
data class MotionTheme(
    val scheme: MotionScheme = MotionScheme.standard(),
    val style: MotionStyle = MotionStyle.standard()
)

/**
 * CompositionLocal used to provide the [MotionTheme] down the tree.
 */
internal val LocalMotionTheme = staticCompositionLocalOf { MotionTheme() }

/**
 * Access the current [MotionScheme] from the [MaterialTheme].
 * This provides a bridge between the standard MaterialTheme and the Motion library.
 *
 * Example:
 * ```
 * val scheme = MaterialTheme.motionScheme
 * val spec = scheme.defaultSpatial
 * ```
 */
val MaterialTheme.motionScheme: MotionScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalMotionTheme.current.scheme

/**
 * Access the current [MotionStyle] from the [MaterialTheme].
 */
val MaterialTheme.motionStyle: MotionStyle
    @Composable
    @ReadOnlyComposable
    get() = LocalMotionTheme.current.style
