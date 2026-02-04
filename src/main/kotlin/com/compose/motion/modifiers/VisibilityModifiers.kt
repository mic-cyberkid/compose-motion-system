package com.compose.motion.modifiers

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import com.compose.motion.theme.motionScheme
import com.compose.motion.theme.defaultSpatial
import com.compose.motion.theme.fastEffects

/**
 * Defines standard enter animations for the motion system.
 */
sealed class MotionEnter {
    /** No entrance animation. */
    object None : MotionEnter()
    /** Simple fade in. */
    object Fade : MotionEnter()
    /** Simple scale in. */
    object Scale : MotionEnter()
    /** Combined fade and scale in. */
    object FadeScale : MotionEnter()
}

/**
 * Defines standard exit animations for the motion system.
 */
sealed class MotionExit {
    /** No exit animation. */
    object None : MotionExit()
    /** Simple fade out. */
    object Fade : MotionExit()
    /** Simple scale out. */
    object Scale : MotionExit()
    /** Combined fade and scale out. */
    object FadeScale : MotionExit()
}

/**
 * A modifier that animates the visibility of its content using alpha and scale.
 *
 * **Note:** This modifier only hides the content visually; it does not remove the
 * element from the composition or stop it from taking up space. For full
 * enter/exit transitions that affect layout, use [com.compose.motion.content.MotionVisibility].
 *
 * @param visible Whether the content should be visible.
 * @param enter The style of the entrance animation. Defaults to [MotionEnter.FadeScale].
 * @param exit The style of the exit animation. Defaults to [MotionExit.FadeScale].
 */
fun Modifier.animateVisibility(
    visible: Boolean,
    enter: MotionEnter = MotionEnter.FadeScale,
    exit: MotionExit = MotionExit.FadeScale
): Modifier = composed {
    if (enter == MotionEnter.None && exit == MotionExit.None && !visible) {
        return@composed this.graphicsLayer { alpha = 0f }
    }

    val scheme = MaterialTheme.motionScheme

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = scheme.fastEffects,
        label = "animateVisibilityAlpha"
    )

    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else if (enter == MotionEnter.Fade || exit == MotionExit.Fade || enter == MotionEnter.None || exit == MotionExit.None) 1f else 0.8f,
        animationSpec = scheme.defaultSpatial,
        label = "animateVisibilityScale"
    )

    this.graphicsLayer {
        this.alpha = if (enter == MotionEnter.None && exit == MotionExit.None) (if (visible) 1f else 0f) else alpha
        this.scaleX = scale
        this.scaleY = scale
    }
}
