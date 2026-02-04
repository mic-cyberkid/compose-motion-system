package com.compose.motion.content

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.togetherWith
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.compose.motion.effects.TransitionEffects
import com.compose.motion.theme.motionScheme

/**
 * A themed alternative to [AnimatedContent].
 *
 * It provides opinionated defaults for transitions that are automatically
 * aligned with the current [com.compose.motion.theme.MotionTheme].
 *
 * @param targetState The state that determines which content to show.
 * @param modifier Modifier for the container.
 * @param transitionSpec The spec for the content transition. If null, uses themed fade + scale.
 * @param contentAlignment Alignment of the content within the container.
 * @param label The label for the animation.
 * @param content The content to display for the given state.
 */
@Composable
fun <S> MotionContent(
    targetState: S,
    modifier: Modifier = Modifier,
    transitionSpec: (AnimatedContentTransitionScope<S>.() -> ContentTransform)? = null,
    contentAlignment: Alignment = Alignment.TopStart,
    label: String = "MotionContent",
    content: @Composable AnimatedContentScope.(targetState: S) -> Unit
) {
    val scheme = MaterialTheme.motionScheme

    AnimatedContent(
        targetState = targetState,
        modifier = modifier,
        transitionSpec = {
            if (transitionSpec != null) {
                transitionSpec()
            } else {
                TransitionEffects.fadeScaleIn(scheme) togetherWith TransitionEffects.fadeScaleOut(scheme)
            }
        },
        contentAlignment = contentAlignment,
        label = label,
        content = content
    )
}

/** Typealias for [androidx.compose.animation.AnimatedContentTransitionScope] for convenience. */
typealias AnimatedContentTransitionScope<S> = androidx.compose.animation.AnimatedContentTransitionScope<S>
