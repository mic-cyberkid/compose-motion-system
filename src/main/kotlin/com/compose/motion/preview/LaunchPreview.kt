package com.compose.motion.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.motion.launch.LaunchStyle
import com.compose.motion.launch.MotionLaunch
import com.compose.motion.theme.ProvideMotionTheme

@Preview(showBackground = true)
@Composable
fun HolographicLaunchPreview() {
    ProvideMotionTheme {
        MotionLaunch(
            visible = true,
            style = LaunchStyle.HolographicPulse,
            backgroundColor = Color.Black
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White, CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrbitalLaunchPreview() {
    ProvideMotionTheme {
        MotionLaunch(
            visible = true,
            style = LaunchStyle.OrbitalConverge,
            backgroundColor = Color(0xFF0D0D0D)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Cyan, CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LiquidGlassLaunchPreview() {
    ProvideMotionTheme {
        MotionLaunch(
            visible = true,
            style = LaunchStyle.LiquidGlassMorph,
            backgroundColor = Color(0xFF1A1A1A)
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.White.copy(alpha = 0.8f), CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuroraLaunchPreview() {
    ProvideMotionTheme {
        MotionLaunch(
            visible = true,
            style = LaunchStyle.AuroraGradient
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White.copy(alpha = 0.5f), CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatrixLaunchPreview() {
    ProvideMotionTheme {
        MotionLaunch(
            visible = true,
            style = LaunchStyle.MatrixRain,
            backgroundColor = Color.Black
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Green.copy(0.5f), CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BentoLaunchPreview() {
    ProvideMotionTheme {
        MotionLaunch(
            visible = true,
            style = LaunchStyle.BentoReveal
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
            )
        }
    }
}
