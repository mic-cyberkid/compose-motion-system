package com.compose.motion.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.compose.motion.modifiers.elevateOnPress
import com.compose.motion.modifiers.motionClickable
import com.compose.motion.modifiers.scaleOnPress
import com.compose.motion.theme.MotionThemeDefaults
import com.compose.motion.theme.ProvideMotionTheme

/**
 * A highly interactive, copy-paste ready example demonstrating themed interactions.
 *
 * This sample shows how scaleOnPress and elevateOnPress transform a static Box
 * into a responsive, premium-feeling component.
 */
@Composable
fun BasicInteractionsSample() {
    ProvideMotionTheme(theme = MotionThemeDefaults.Expressive) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Touch & Hold",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Premium Interactive Card
            Box(
                modifier = Modifier
                    .size(240.dp)
                    // 1. Motion Clickable handles the interaction state
                    .motionClickable { /* click action */ }
                    // 2. Scale down on press for tactile feel
                    .scaleOnPress(targetScale = 0.92f)
                    // 3. Increase elevation (shadow) on press for depth
                    .elevateOnPress(pressedElevation = 24f, defaultElevation = 6f)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.tertiary
                            )
                        ),
                        shape = RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "PRESS ME",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        "Scale + Elevation",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Explanation Section
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "What's happening?",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "• scaleOnPress: Uses a bouncy spring to shrink the element.\n" +
                        "• elevateOnPress: Smoothly transition shadow depth.\n" +
                        "• motionClickable: Connects these modifiers to the touch lifecycle.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
