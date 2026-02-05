package com.compose.motion.modifiers

import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.compose.motion.theme.motionScheme

/**
 * A modifier that makes the element "magnetic", attracting it to the touch point.
 *
 * @param pullStrength The strength of the magnetic pull (0.0 to 1.0). Defaults to 0.5f.
 */
fun Modifier.magneticPull(
    pullStrength: Float = 0.5f
): Modifier = composed {
    var offset by remember { mutableStateOf(Offset.Zero) }
    val scheme = MaterialTheme.motionScheme

    val animatedOffset by animateOffsetAsState(
        targetValue = offset,
        animationSpec = scheme.fastSpatialSpec(),
        label = "magneticOffset"
    )

    this.pointerInput(Unit) {
        detectTapGestures(
            onPress = { touchOffset ->
                val centerX = size.width / 2f
                val centerY = size.height / 2f
                offset = Offset(
                    (touchOffset.x - centerX) * pullStrength,
                    (touchOffset.y - centerY) * pullStrength
                )
                tryAwaitRelease()
                offset = Offset.Zero
            }
        )
    }
    .graphicsLayer {
        translationX = animatedOffset.x
        translationY = animatedOffset.y
    }
}
