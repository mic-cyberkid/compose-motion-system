package com.compose.motion.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.compose.motion.modifiers.scaleOnPress

/**
 * An opinionated [Card] with built-in micro-interactions.
 *
 * It automatically applies a [scaleOnPress] effect to provide immediate
 * visual feedback when the card is interacted with.
 *
 * @param onClick Callback to be invoked when the card is clicked.
 * @param modifier Modifier for the card.
 * @param enabled Whether the card is enabled for interaction.
 * @param shape The shape of the card.
 * @param colors The colors for the card.
 * @param elevation The elevation for the card.
 * @param interactionSource Optional [MutableInteractionSource] to track interactions.
 * @param content The content to display inside the card.
 */
@Composable
fun MotionCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CardDefaults.shape,
    colors: CardColors = CardDefaults.cardColors(),
    elevation: CardElevation = CardDefaults.cardElevation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.scaleOnPress(interactionSource = interactionSource),
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        interactionSource = interactionSource,
        content = { content() }
    )
}
