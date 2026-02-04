package com.compose.motion.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.compose.motion.theme.motionScheme
import com.compose.motion.theme.MotionScheme

/**
 * A themed alternative to [NavHost] that provides built-in support for [MotionTransition]
 * and shared element transitions.
 *
 * It automatically wraps the content in a [SharedTransitionLayout] and provides
 * [LocalSharedTransitionScope] and [LocalAnimatedVisibilityScope] down the tree,
 * enabling easy use of [Modifier.sharedElement].
 *
 * @param navController The [NavHostController] for the host.
 * @param startDestination The route for the start destination.
 * @param modifier Modifier for the container.
 * @param transition The default [MotionTransition] to use for all destinations.
 *                   Defaults to [MotionTransition.SharedAxisX].
 * @param content The [NavGraphBuilder] used to build the graph.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MotionNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    transition: MotionTransition = MotionTransition.SharedAxisX,
    content: NavGraphBuilder.() -> Unit
) {
    val scheme = MaterialTheme.motionScheme

    SharedTransitionLayout(modifier = modifier) {
        CompositionLocalProvider(LocalSharedTransitionScope provides this) {
            NavHost(
                navController = navController,
                startDestination = startDestination,
                enterTransition = {
                    getEnterTransition(transition, scheme)
                },
                exitTransition = {
                    getExitTransition(transition, scheme)
                },
                popEnterTransition = {
                    getPopEnterTransition(transition, scheme)
                },
                popExitTransition = {
                    getPopExitTransition(transition, scheme)
                },
                builder = content
            )
        }
    }
}

private fun getEnterTransition(
    transition: MotionTransition,
    scheme: MotionScheme
): EnterTransition {
    return when (transition) {
        MotionTransition.Fade -> fadeIn(animationSpec = scheme.fastEffectsSpec())
        MotionTransition.Slide -> slideInHorizontally(animationSpec = scheme.defaultSpatialSpec()) { it }
        MotionTransition.SharedAxisX -> slideInHorizontally(animationSpec = scheme.defaultSpatialSpec()) { it } +
                                     fadeIn(animationSpec = scheme.fastEffectsSpec())
        MotionTransition.SharedAxisY -> slideInVertically(animationSpec = scheme.defaultSpatialSpec()) { it } +
                                     fadeIn(animationSpec = scheme.fastEffectsSpec())
        MotionTransition.ContainerTransform -> fadeIn(animationSpec = scheme.fastEffectsSpec()) +
                                              scaleIn(initialScale = 0.85f, animationSpec = scheme.defaultSpatialSpec())
    }
}

private fun getExitTransition(
    transition: MotionTransition,
    scheme: MotionScheme
): ExitTransition {
    return when (transition) {
        MotionTransition.Fade -> fadeOut(animationSpec = scheme.fastEffectsSpec())
        MotionTransition.Slide -> slideOutHorizontally(animationSpec = scheme.defaultSpatialSpec()) { -it }
        MotionTransition.SharedAxisX -> slideOutHorizontally(animationSpec = scheme.defaultSpatialSpec()) { -it / 3 } +
                                     fadeOut(animationSpec = scheme.fastEffectsSpec())
        MotionTransition.SharedAxisY -> slideOutVertically(animationSpec = scheme.defaultSpatialSpec()) { -it / 3 } +
                                     fadeOut(animationSpec = scheme.fastEffectsSpec())
        MotionTransition.ContainerTransform -> fadeOut(animationSpec = scheme.fastEffectsSpec()) +
                                              scaleOut(targetScale = 0.85f, animationSpec = scheme.defaultSpatialSpec())
    }
}

private fun getPopEnterTransition(
    transition: MotionTransition,
    scheme: MotionScheme
): EnterTransition {
    return when (transition) {
        MotionTransition.Fade -> fadeIn(animationSpec = scheme.fastEffectsSpec())
        MotionTransition.Slide -> slideInHorizontally(animationSpec = scheme.defaultSpatialSpec()) { -it }
        MotionTransition.SharedAxisX -> slideInHorizontally(animationSpec = scheme.defaultSpatialSpec()) { -it } +
                                     fadeIn(animationSpec = scheme.fastEffectsSpec())
        MotionTransition.SharedAxisY -> slideInVertically(animationSpec = scheme.defaultSpatialSpec()) { -it } +
                                     fadeIn(animationSpec = scheme.fastEffectsSpec())
        MotionTransition.ContainerTransform -> fadeIn(animationSpec = scheme.fastEffectsSpec()) +
                                              scaleIn(initialScale = 0.85f, animationSpec = scheme.defaultSpatialSpec())
    }
}

private fun getPopExitTransition(
    transition: MotionTransition,
    scheme: MotionScheme
): ExitTransition {
    return when (transition) {
        MotionTransition.Fade -> fadeOut(animationSpec = scheme.fastEffectsSpec())
        MotionTransition.Slide -> slideOutHorizontally(animationSpec = scheme.defaultSpatialSpec()) { it }
        MotionTransition.SharedAxisX -> slideOutHorizontally(animationSpec = scheme.defaultSpatialSpec()) { it / 3 } +
                                     fadeOut(animationSpec = scheme.fastEffectsSpec())
        MotionTransition.SharedAxisY -> slideOutVertically(animationSpec = scheme.defaultSpatialSpec()) { it / 3 } +
                                     fadeOut(animationSpec = scheme.fastEffectsSpec())
        MotionTransition.ContainerTransform -> fadeOut(animationSpec = scheme.fastEffectsSpec()) +
                                              scaleOut(targetScale = 0.85f, animationSpec = scheme.defaultSpatialSpec())
    }
}
