package com.compose.motion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.compose.motion.modifiers.glassmorphic
import com.compose.motion.effects.glitch
import com.compose.motion.theme.MotionStyle
import com.compose.motion.theme.motionStyle
import com.compose.motion.theme.StandardMotionStyle
import com.compose.motion.theme.AppleGlassStyle
import com.compose.motion.theme.CyberGlitchStyle
import com.compose.motion.theme.MinimalFluidStyle
import com.compose.motion.theme.NeumorphicStyle

/**
 * A flexible surface that automatically adapts its visual treatment
 * based on the current [MotionStyle].
 */
@Composable
fun MotionSurface(
    modifier: Modifier = Modifier,
    shape: Shape? = null,
    color: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit
) {
    val style = MaterialTheme.motionStyle
    val actualShape = shape ?: RoundedCornerShape(style.cornerRadius)

    Box(
        modifier = modifier
            .then(
                when (style) {
                    is AppleGlassStyle -> Modifier.glassmorphic(actualShape)
                    is NeumorphicStyle -> Modifier
                        .shadow(style.shadowElevation, actualShape)
                        .background(color, actualShape)
                    is CyberGlitchStyle -> Modifier
                        .border(1.dp, style.primaryColorOverride ?: Color.Cyan, actualShape)
                        .glitch(intensity = 0.2f)
                        .background(color.copy(alpha = 0.9f), actualShape)
                    is MinimalFluidStyle -> Modifier
                        .background(color.copy(alpha = 0.1f), actualShape)
                        .border(0.5.dp, Color.White.copy(alpha = style.borderAlpha), actualShape)
                    else -> Modifier
                        .shadow(style.shadowElevation, actualShape)
                        .background(color, actualShape)
                }
            )
            .clip(actualShape)
    ) {
        content()
    }
}
