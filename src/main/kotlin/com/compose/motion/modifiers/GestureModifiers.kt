package com.compose.motion.modifiers

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.sign

/**
 * Adds an elastic, "sticky" drag effect to an element.
 * The element resists movement as it's dragged further from its origin.
 *
 * @param resistance The multiplier for how much the element resists movement.
 */
fun Modifier.elasticDrag(
    resistance: Float = 0.5f
): Modifier = composed {
    var offset by remember { mutableStateOf(Offset.Zero) }
    val animatedOffset by animateOffsetAsState(
        targetValue = offset,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = "elastic"
    )

    this.pointerInput(Unit) {
        detectDragGestures(
            onDrag = { change, dragAmount ->
                change.consume()
                // Apply logarithmic-like resistance
                val newX = offset.x + dragAmount.x * resistance
                val newY = offset.y + dragAmount.y * resistance
                offset = Offset(newX, newY)
            },
            onDragEnd = {
                offset = Offset.Zero
            },
            onDragCancel = {
                offset = Offset.Zero
            }
        )
    }
    .graphicsLayer {
        translationX = animatedOffset.x
        translationY = animatedOffset.y
    }
}
