package com.compose.motion.content

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.motion.theme.motionScheme

/**
 * A general-purpose animated container that provides consistent motion for its content.
 *
 * It automatically animates its size whenever the content changes, using the
 * current [MotionTheme]'s default spatial animation spec.
 *
 * Use cases:
 * - Collapsing/expanding sections
 * - Conditional content swaps
 * - Smooth layout changes
 *
 * @param modifier Modifier for the container.
 * @param content The content to display.
 */
@Composable
fun MotionContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scheme = MaterialTheme.motionScheme

    Box(
        modifier = modifier.animateContentSize(
            animationSpec = scheme.defaultSpatialSpec()
        )
    ) {
        content()
    }
}
