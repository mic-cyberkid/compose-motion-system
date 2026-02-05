package com.compose.motion.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.motion.ExperimentalMotionApi
import com.compose.motion.launch.SplashScreen
import com.compose.motion.launch.SplashStyle
import com.compose.motion.loading.*
import com.compose.motion.theme.ProvideMotionTheme

@OptIn(ExperimentalMotionApi::class)
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    ProvideMotionTheme {
        SplashScreen(
            visible = true,
            style = SplashStyle.ParticleBurst,
            logo = {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = Color.Red
                )
            }
        )
    }
}

@OptIn(ExperimentalMotionApi::class)
@Preview(showBackground = true)
@Composable
fun LoadingIndicatorsPreview() {
    ProvideMotionTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Spinner", style = MaterialTheme.typography.labelLarge)
            SpinnerLoader()

            Text("Dots", style = MaterialTheme.typography.labelLarge)
            DotsLoader()

            Text("Skeleton", style = MaterialTheme.typography.labelLarge)
            SkeletonLoader()

            Text("Particle", style = MaterialTheme.typography.labelLarge)
            ParticleLoader()

            Text("Progress", style = MaterialTheme.typography.labelLarge)
            ProgressBarLoader(progress = 0.6f, modifier = Modifier.fillMaxWidth())
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Preview(showBackground = true)
@Composable
fun LoadingWithTipsPreview() {
    ProvideMotionTheme {
        LoadingWithTips(
            tips = listOf(
                "Loading your experience...",
                "Did you know? Motion improves UX.",
                "Almost there!"
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalMotionApi::class)
@Preview(showBackground = true)
@Composable
fun EpicGameLoaderPreview() {
    ProvideMotionTheme {
        EpicGameLoader(
            progress = 0.75f,
            title = "COMPOSE MOTION",
            subtitle = "Initializing premium assets..."
        )
    }
}

@OptIn(ExperimentalMotionApi::class)
@Preview(showBackground = true)
@Composable
fun TreasureChestPreview() {
    ProvideMotionTheme {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TreasureChestProgress(progress = 0.95f)
        }
    }
}
