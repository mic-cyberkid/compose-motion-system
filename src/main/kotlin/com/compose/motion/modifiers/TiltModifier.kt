package com.compose.motion.modifiers

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.compose.motion.theme.motionScheme

/**
 * A modifier that tilts the element in 3D space based on touch position.
 *
 * @param maxTiltDegrees The maximum rotation in degrees. Defaults to 10f.
 */
fun Modifier.tiltOnTouch(
    maxTiltDegrees: Float = 10f
): Modifier = composed {
    var tiltX by remember { mutableStateOf(0f) }
    var tiltY by remember { mutableStateOf(0f) }
    val scheme = MaterialTheme.motionScheme

    val animatedTiltX by animateFloatAsState(
        targetValue = tiltX,
        animationSpec = scheme.fastSpatialSpec(),
        label = "tiltX"
    )
    val animatedTiltY by animateFloatAsState(
        targetValue = tiltY,
        animationSpec = scheme.fastSpatialSpec(),
        label = "tiltY"
    )

    this.pointerInput(Unit) {
        detectTapGestures(
            onPress = { offset ->
                val centerX = size.width / 2
                val centerY = size.height / 2
                tiltY = ((offset.x - centerX) / centerX) * maxTiltDegrees
                tiltX = -((offset.y - centerY) / centerY) * maxTiltDegrees
                tryAwaitRelease()
                tiltX = 0f
                tiltY = 0f
            }
        )
    }
    .graphicsLayer {
        rotationX = animatedTiltX
        rotationY = animatedTiltY
        cameraDistance = 12f * density
    }
}
