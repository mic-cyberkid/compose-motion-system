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
import com.compose.motion.ExperimentalMotionApi
import com.compose.motion.launch.SplashStyle
import com.compose.motion.launch.MotionLaunch
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import com.compose.motion.theme.ProvideMotionTheme
import kotlinx.coroutines.delay

/**
 * A comprehensive sample showing multiple futuristic Launch Animation styles.
 */
@OptIn(ExperimentalMotionApi::class, ExperimentalLayoutApi::class)
@Composable
fun LaunchAnimationSample() {
    var showLaunch by remember { mutableStateOf(true) }
    var currentStyle by remember { mutableStateOf<SplashStyle>(SplashStyle.LiquidGlassMorph) }

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

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = { currentStyle = SplashStyle.LiquidGlassMorph }, modifier = Modifier.padding(4.dp)) { Text("Liquid Glass") }
                    Button(onClick = { currentStyle = SplashStyle.GlassReveal }, modifier = Modifier.padding(4.dp)) { Text("Glass Reveal") }
                    Button(onClick = { currentStyle = SplashStyle.OrganicMorph }, modifier = Modifier.padding(4.dp)) { Text("Organic Morph") }
                    Button(onClick = { currentStyle = SplashStyle.HolographicPulse }, modifier = Modifier.padding(4.dp)) { Text("Holographic") }
                    Button(onClick = { currentStyle = SplashStyle.OrbitalConverge }, modifier = Modifier.padding(4.dp)) { Text("Orbital") }
                    Button(onClick = { currentStyle = SplashStyle.AuroraGradient }, modifier = Modifier.padding(4.dp)) { Text("Aurora") }
                    Button(onClick = { currentStyle = SplashStyle.BentoReveal }, modifier = Modifier.padding(4.dp)) { Text("Bento") }
                    Button(onClick = { currentStyle = SplashStyle.MatrixRain }, modifier = Modifier.padding(4.dp)) { Text("Matrix") }
                }
            }

            // The Launch Overlay
            MotionLaunch(
                visible = showLaunch,
                style = currentStyle,
                backgroundColor = if (currentStyle == SplashStyle.AuroraGradient || currentStyle == SplashStyle.MatrixRain) Color.Transparent else Color(0xFF121212)
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
