package com.compose.motion.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.motion.modifiers.elevateOnPress
import com.compose.motion.modifiers.motionClickable
import com.compose.motion.modifiers.scaleOnPress
import com.compose.motion.theme.MotionThemeDefaults
import com.compose.motion.theme.ProvideMotionTheme

/**
 * A complete, copy-paste ready example of using Compose Motion interaction modifiers.
 *
 * To use this:
 * 1. Ensure you have the Compose Motion library dependency in your build.gradle.
 * 2. Copy this file into your project.
 * 3. Call BasicInteractionsSample() from your setContent block.
 */
@Composable
fun BasicInteractionsSample() {
    // 1. Wrap your content in ProvideMotionTheme
    ProvideMotionTheme(theme = MotionThemeDefaults.Expressive) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Compose Motion Interactions",
                style = MaterialTheme.typography.headlineMedium
            )

            // 2. An interactive card using themed modifiers
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(16.dp)
                    )
                    // Apply motion modifiers!
                    .motionClickable { /* Handle Click */ }
                    .scaleOnPress(targetScale = 0.9f)
                    .elevateOnPress(pressedElevation = 12f, defaultElevation = 4f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Press Me",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Text(
                "The button above uses scaleOnPress and elevateOnPress " +
                "which automatically resolve their animation specs from " +
                "the Expressive MotionTheme.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
