package com.compose.motion.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.motion.launch.LaunchStyle
import com.compose.motion.launch.MotionLaunch
import com.compose.motion.theme.ProvideMotionTheme
import kotlinx.coroutines.delay

/**
 * A comprehensive sample showing multiple futuristic Launch Animation styles.
 */
@Composable
fun LaunchAnimationSample() {
    var showLaunch by remember { mutableStateOf(true) }
    var currentStyle by remember { mutableStateOf<LaunchStyle>(LaunchStyle.LiquidGlassMorph) }

    // Re-trigger launch when style changes for demo purposes
    LaunchedEffect(currentStyle) {
        showLaunch = true
        delay(3000)
        showLaunch = false
    }

    ProvideMotionTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Select a Style to Preview:", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { currentStyle = LaunchStyle.LiquidGlassMorph }) { Text("Liquid Glass") }
                Button(onClick = { currentStyle = LaunchStyle.HolographicPulse }) { Text("Holographic Pulse") }
                Button(onClick = { currentStyle = LaunchStyle.OrbitalConverge }) { Text("Orbital Converge") }
                Button(onClick = { currentStyle = LaunchStyle.AuroraGradient }) { Text("Aurora Gradient") }
                Button(onClick = { currentStyle = LaunchStyle.GlowPulse }) { Text("Glow Pulse") }
            }

            // The Launch Overlay
            MotionLaunch(
                visible = showLaunch,
                style = currentStyle,
                backgroundColor = if (currentStyle == LaunchStyle.AuroraGradient) Color.Transparent else Color(0xFF121212)
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.White.copy(alpha = 0.8f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("M", color = Color.Black, style = MaterialTheme.typography.displayMedium)
                }
            }
        }
    }
}
