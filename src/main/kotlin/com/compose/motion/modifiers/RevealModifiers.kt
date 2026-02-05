package com.compose.motion.modifiers

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.compose.motion.theme.MotionScheme
import androidx.compose.material3.MaterialTheme
import com.compose.motion.theme.motionScheme

/**
 * Automatically triggers an entrance animation when the element enters the viewport.
 *
 * @param threshold The vertical offset from the bottom of the screen to trigger the reveal.
 * @param direction The direction to slide from.
 */
fun Modifier.revealOnScroll(
    threshold: Dp = 50.dp,
    direction: RevealDirection = RevealDirection.Up
): Modifier = composed {
    var hasBeenRevealed by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    val scheme = MaterialTheme.motionScheme

    val progress by animateFloatAsState(
        targetValue = if (hasBeenRevealed) 1f else 0f,
        animationSpec = scheme.slowSpatialSpec(),
        label = "reveal"
    )

    this.onGloballyPositioned { coords ->
        val y = coords.positionInWindow().y
        val windowHeight = coords.parentLayoutCoordinates?.size?.height ?: 2000

        if (y < windowHeight - with(density) { threshold.toPx() }) {
            hasBeenRevealed = true
        }
    }
    .graphicsLayer {
        alpha = progress
        when (direction) {
            RevealDirection.Up -> translationY = (1f - progress) * 100.dp.toPx()
            RevealDirection.Down -> translationY = -(1f - progress) * 100.dp.toPx()
            RevealDirection.Left -> translationX = (1f - progress) * 100.dp.toPx()
            RevealDirection.Right -> translationX = -(1f - progress) * 100.dp.toPx()
        }
        scaleX = 0.9f + (progress * 0.1f)
        scaleY = 0.9f + (progress * 0.1f)
    }
}

enum class RevealDirection {
    Up, Down, Left, Right
}
