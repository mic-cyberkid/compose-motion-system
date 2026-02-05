package com.compose.motion.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

/**
 * A text component that animates its characters into place.
 */
@Composable
fun MotionText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    Row(modifier = modifier) {
        text.forEachIndexed { index, char ->
            var visible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                visible = true
            }

            val progress by animateFloatAsState(
                targetValue = if (visible) 1f else 0f,
                animationSpec = tween(
                    durationMillis = 400,
                    delayMillis = index * 30,
                    easing = LinearOutSlowInEasing
                ),
                label = "char"
            )

            Text(
                text = char.toString(),
                style = style,
                modifier = Modifier.graphicsLayer {
                    alpha = progress
                    translationY = (1f - progress) * 20.dp.toPx()
                }
            )
        }
    }
}
