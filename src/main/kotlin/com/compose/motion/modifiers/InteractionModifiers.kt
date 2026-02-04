package com.compose.motion.modifiers

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.compose.motion.theme.motionScheme

/**
 * A modifier that applies a scale effect when the element is pressed.
 *
 * Uses themed motion tokens from [MaterialTheme.motionScheme] to ensure
 * consistency with the application's motion style.
 *
 * @param targetScale The scale to apply when pressed. Defaults to 0.95f.
 */
fun Modifier.scaleOnPress(
    targetScale: Float = 0.95f
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scheme = MaterialTheme.motionScheme

    val scale by animateFloatAsState(
        targetValue = if (isPressed) targetScale else 1f,
        animationSpec = scheme.fastSpatialSpec(),
        label = "scaleOnPress"
    )

    this.scale(scale)
        .clickable(
            interactionSource = interactionSource,
            indication = null, // Interaction is handled by the scale effect
            onClick = {}
        )
}

/**
 * A motion-aware clickable modifier that uses themed motion tokens.
 *
 * By default, this removes the standard visual indication (ripple) to allow
 * other motion modifiers (like [scaleOnPress]) to provide the feedback.
 *
 * @param interactionSource Optional [MutableInteractionSource] to track interactions.
 * @param useDefaultIndication Whether to use the default [LocalIndication] (ripple). Defaults to false.
 * @param onClick Callback to be invoked when the element is clicked.
 */
fun Modifier.motionClickable(
    interactionSource: MutableInteractionSource? = null,
    useDefaultIndication: Boolean = false,
    onClick: () -> Unit
): Modifier = composed {
    val actualInteractionSource = interactionSource ?: remember { MutableInteractionSource() }

    this.clickable(
        interactionSource = actualInteractionSource,
        indication = if (useDefaultIndication) androidx.compose.foundation.LocalIndication.current else null,
        onClick = onClick
    )
}

/**
 * A modifier that applies an elevation effect when the element is pressed.
 *
 * @param pressedElevation The elevation to apply when pressed. Defaults to 8.dp.
 * @param defaultElevation The elevation to apply when not pressed. Defaults to 2.dp.
 */
fun Modifier.elevateOnPress(
    pressedElevation: Float = 8f,
    defaultElevation: Float = 2f
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scheme = MaterialTheme.motionScheme

    val elevation by animateFloatAsState(
        targetValue = if (isPressed) pressedElevation else defaultElevation,
        animationSpec = scheme.fastEffectsSpec(),
        label = "elevateOnPress"
    )

    this.graphicsLayer {
        shadowElevation = elevation.dp.toPx()
    }
}
