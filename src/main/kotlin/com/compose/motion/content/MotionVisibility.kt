package com.compose.motion.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.motion.effects.TransitionEffects
import com.compose.motion.theme.motionScheme

/**
 * A themed alternative to [AnimatedVisibility].
 *
 * It provides opinionated defaults for [enter] and [exit] transitions that are
 * automatically aligned with the current [com.compose.motion.theme.MotionTheme].
 *
 * @param visible Whether the content should be visible.
 * @param modifier Modifier for the container.
 * @param enter The entrance transition. If null, uses themed fade + scale.
 * @param exit The exit transition. If null, uses themed fade + scale.
 * @param label The label for the animation.
 * @param content The content to display.
 */
@Composable
fun MotionVisibility(
    visible: Boolean,
    modifier: Modifier = Modifier,
    enter: EnterTransition? = null,
    exit: ExitTransition? = null,
    label: String = "MotionVisibility",
    content: @Composable () -> Unit
) {
    val scheme = MaterialTheme.motionScheme
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = enter ?: TransitionEffects.fadeScaleIn(scheme),
        exit = exit ?: TransitionEffects.fadeScaleOut(scheme),
        label = label,
        content = { content() }
    )
}
