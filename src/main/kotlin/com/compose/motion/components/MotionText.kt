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
 *
 * @param bounce If true, characters will have a slight squash/bounce on entrance.
 */
@Composable
fun MotionText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    bounce: Boolean = false
) {
    Row(modifier = modifier) {
        text.forEachIndexed { index, char ->
            var visible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                visible = true
            }

            val progress by animateFloatAsState(
                targetValue = if (visible) 1f else 0f,
                animationSpec = if (bounce) {
                    spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow)
                } else {
                    tween(durationMillis = 400, delayMillis = index * 30, easing = LinearOutSlowInEasing)
                },
                label = "char"
            )

            Text(
                text = char.toString(),
                style = style,
                modifier = Modifier.graphicsLayer {
                    alpha = progress.coerceIn(0f, 1f)
                    translationY = (1f - progress) * 20.dp.toPx()
                    if (bounce) {
                        scaleX = 0.8f + (progress * 0.2f)
                        scaleY = 1.2f - (progress * 0.2f)
                    }
                }
            )
        }
    }
}
