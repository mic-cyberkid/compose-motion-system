package com.compose.motion.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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
    // 1. Create a shared interaction source to coordinate animations
    val interactionSource = remember { MutableInteractionSource() }

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
            // We use Surface here to handle elevation and clipping correctly
            Surface(
                modifier = Modifier
                    .size(240.dp)
                    // 2. Pass the shared interaction source to all motion modifiers
                    .motionClickable(interactionSource = interactionSource) { /* action */ }
                    .scaleOnPress(interactionSource = interactionSource, targetScale = 0.92f)
                    .elevateOnPress(interactionSource = interactionSource, pressedElevation = 24f, defaultElevation = 6f),
                shape = RoundedCornerShape(24.dp),
                // Using a brush for a modern look
                color = Color.Transparent
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.tertiary
                                )
                            )
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
                        "• Shared Interaction: All modifiers now share a single InteractionSource.\n" +
                        "• Surface usage: Handles shadows and clipping cleanly without sharp edges.\n" +
                        "• Bouncy Motion: resolve automatically from the Expressive theme.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
