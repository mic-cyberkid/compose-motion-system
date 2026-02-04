package com.compose.motion.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
 * A complete, copy-paste ready example of a futuristic Launch Animation sequence.
 */
@Composable
fun LaunchAnimationSample() {
    var showLaunch by remember { mutableStateOf(true) }

    // Simulate app loading
    LaunchedEffect(Unit) {
        delay(3000)
        showLaunch = false
    }

    ProvideMotionTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            // Your real App Content
            MainContent(onReset = { showLaunch = true })

            // The Launch Overlay
            MotionLaunch(
                visible = showLaunch,
                // Choose a futuristic style!
                style = LaunchStyle.LiquidGlassMorph,
                backgroundColor = Color(0xFF1A1A1A)
            ) {
                // The Splash content (e.g. your Logo)
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.White.copy(alpha = 0.9f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("LOGO", color = Color.Black, style = MaterialTheme.typography.headlineMedium)
                }
            }
        }
    }
}

@Composable
private fun MainContent(onReset: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("App Loaded!", style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onReset) {
            Text("Re-run Launch Animation")
        }
    }
}
