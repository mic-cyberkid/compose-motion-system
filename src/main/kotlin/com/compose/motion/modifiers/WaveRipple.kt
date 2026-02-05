package com.compose.motion.modifiers

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * A modifier that applies a custom wave ripple effect on touch.
 */
fun Modifier.waveRipple(
    color: Color = Color.White.copy(alpha = 0.3f)
): Modifier = composed {
    var rippleCenter by remember { mutableStateOf(Offset.Zero) }
    val rippleProgress = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    this.pointerInput(Unit) {
        detectTapGestures { offset ->
            rippleCenter = offset
            scope.launch {
                rippleProgress.snapTo(0f)
                rippleProgress.animateTo(1f, tween(800, easing = LinearOutSlowInEasing))
                rippleProgress.snapTo(0f)
            }
        }
    }
    .drawBehind {
        if (rippleProgress.value > 0f && rippleProgress.value < 1f) {
            drawCircle(
                color = color.copy(alpha = (1f - rippleProgress.value) * color.alpha),
                radius = rippleProgress.value * size.maxDimension,
                center = rippleCenter,
                style = Stroke(width = 2.dp.toPx())
            )
        }
    }
}
