package com.compose.motion.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * CompositionLocal providing the current [SharedTransitionScope].
 * Automatically provided by [MotionNavHost].
 */
@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }

/**
 * CompositionLocal providing the current [AnimatedVisibilityScope].
 * Automatically provided by [motionComposable].
 */
val LocalAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }

/**
 * A themed alternative to [composable] that provides the necessary scopes for shared element transitions.
 *
 * It automatically provides the current [AnimatedVisibilityScope] via [LocalAnimatedVisibilityScope],
 * which is required for [Modifier.sharedElement] to work correctly within [MotionNavHost].
 *
 * @param route The route for the destination.
 * @param arguments List of arguments to pass to the destination.
 * @param deepLinks List of deep links for the destination.
 * @param content The composable content for the destination.
 */
fun NavGraphBuilder.motionComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
    ) { backStackEntry ->
        CompositionLocalProvider(LocalAnimatedVisibilityScope provides this) {
            content(backStackEntry)
        }
    }
}

/**
 * A modifier that enables shared element transitions for its content.
 *
 * It automatically resolves the required [SharedTransitionScope] and
 * [AnimatedVisibilityScope] from CompositionLocals provided by [MotionNavHost]
 * and [motionComposable].
 *
 * @param key A unique key for the shared element. Must match in both start and end destinations.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
fun Modifier.sharedElement(key: Any): Modifier = composed {
    val sharedScope = LocalSharedTransitionScope.current
    val animatedScope = LocalAnimatedVisibilityScope.current

    if (sharedScope != null && animatedScope != null) {
        with(sharedScope) {
            this@composed.sharedElement(
                rememberSharedContentState(key = key),
                animatedVisibilityScope = animatedScope
            )
        }
    } else {
        this
    }
}
