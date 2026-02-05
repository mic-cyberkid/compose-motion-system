package com.compose.motion.modifiers

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.launch

/**
 * A modifier that provides magnetic feedback toward the finger during a drag interaction.
 */
fun Modifier.dragMagneticFeedback(
    interactionSource: MutableInteractionSource? = null,
    strength: Float = 0.2f
): Modifier = composed {
    val actualSource = interactionSource ?: remember { MutableInteractionSource() }
    val isDragged by actualSource.collectIsDraggedAsState()
    val scope = rememberCoroutineScope()
    val offset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }

    this.pointerInput(Unit) {
        detectDragGestures(
            onDragStart = { },
            onDragEnd = {
                scope.launch {
                    offset.animateTo(
                        Offset.Zero,
                        spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
                    )
                }
            },
            onDragCancel = {
                scope.launch { offset.snapTo(Offset.Zero) }
            },
            onDrag = { change, dragAmount ->
                change.consume()
                scope.launch {
                    // Just a subtle magnetic attraction
                    val target = Offset(dragAmount.x * strength, dragAmount.y * strength)
                    offset.snapTo(target)
                }
            }
        )
    }
    .graphicsLayer {
        translationX = offset.value.x
        translationY = offset.value.y
    }
}
