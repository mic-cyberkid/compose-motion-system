package com.compose.motion.content

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

/**
 * A container that applies a staggered entrance animation to its children.
 *
 * Note: This is a simple implementation. In a real world, you might want to
 * wrap a LazyColumn or provide a list of items.
 */
@Composable
fun StaggeredEntrance(
    index: Int,
    delayMillis: Int = 100,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }

    val progress by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = index * delayMillis,
            easing = LinearOutSlowInEasing
        ),
        label = "staggered"
    )

    Box(
        modifier = modifier.graphicsLayer {
            alpha = progress
            translationY = (1f - progress) * 50.dp.toPx()
        }
    ) {
        content()
    }
}
