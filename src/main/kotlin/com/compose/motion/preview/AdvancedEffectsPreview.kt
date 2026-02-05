package com.compose.motion.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.motion.components.LiquidProgressIndicator
import com.compose.motion.components.MotionText
import com.compose.motion.content.StaggeredEntrance
import com.compose.motion.effects.ConfettiBurst
import com.compose.motion.effects.glitch
import com.compose.motion.effects.shimmer
import com.compose.motion.modifiers.magneticPull
import com.compose.motion.modifiers.tiltOnTouch
import com.compose.motion.modifiers.waveRipple
import com.compose.motion.theme.ProvideMotionTheme

@Preview(showBackground = true)
@Composable
fun AdvancedEffectsPreview() {
    var confettiTrigger by remember { mutableStateOf(0) }

    ProvideMotionTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text("Micro-interactions", style = MaterialTheme.typography.headlineSmall)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .tiltOnTouch()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Tilt Me")
                    }
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .magneticPull()
                            .background(MaterialTheme.colorScheme.secondaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Magnetic")
                    }
                }
            }

            item {
                Text("Skeleton Shimmer", style = MaterialTheme.typography.headlineSmall)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .shimmer()
                )
            }

            item {
                Text("Staggered Entrance", style = MaterialTheme.typography.headlineSmall)
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(3) { index ->
                        StaggeredEntrance(index = index) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .background(MaterialTheme.colorScheme.surfaceVariant),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Item $index")
                            }
                        }
                    }
                }
            }

            item {
                Text("Liquid Progress", style = MaterialTheme.typography.headlineSmall)
                LiquidProgressIndicator(progress = 0.6f)
            }

            item {
                Text("Glitch Effect", style = MaterialTheme.typography.headlineSmall)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .glitch()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Text("GLITCHY", color = Color.Green, style = MaterialTheme.typography.headlineMedium)
                }
            }

            item {
                Text("Motion Text", style = MaterialTheme.typography.headlineSmall)
                MotionText("Futuristic Reveal", style = MaterialTheme.typography.headlineMedium)
            }

            item {
                Text("Wave Ripple & Confetti", style = MaterialTheme.typography.headlineSmall)
                Button(
                    onClick = { confettiTrigger++ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .waveRipple()
                ) {
                    Text("Celebrate!")
                }
                Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {
                    ConfettiBurst(trigger = confettiTrigger)
                }
            }
        }
    }
}
