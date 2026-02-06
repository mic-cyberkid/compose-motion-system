package com.compose.motion.examples.showcase

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.motion.theme.MotionScheme
import com.compose.motion.theme.ProvideMotionTheme
import com.compose.motion.theme.motionScheme
import androidx.compose.animation.core.FiniteAnimationSpec

/**
 * Demonstrates how to use [MotionScheme] tokens for different animation types.
 * It compares "Standard" (tween-based) vs "Expressive" (spring-based) motion
 * across different speed levels (Fast, Default, Slow).
 */
@Composable
fun AnimationShowcase() {
    var useExpressive by remember { mutableStateOf(true) }
    val scheme = if (useExpressive) MotionScheme.expressive() else MotionScheme.standard()

    ProvideMotionTheme(scheme = scheme) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text("Animation Tokens Showcase", style = MaterialTheme.typography.headlineMedium)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Expressive Motion")
                Switch(checked = useExpressive, onCheckedChange = { useExpressive = it })
            }

            HorizontalDivider()

            AnimationSection(title = "Spatial Tokens (Size/Position)", isSpatial = true)
            AnimationSection(title = "Effect Tokens (Color/Alpha)", isSpatial = false)
        }
    }
}

@Composable
private fun AnimationSection(title: String, isSpatial: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(title, style = MaterialTheme.typography.titleMedium)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AnimationBox(label = "Fast", isSpatial = isSpatial, speed = Speed.Fast)
            AnimationBox(label = "Default", isSpatial = isSpatial, speed = Speed.Default)
            AnimationBox(label = "Slow", isSpatial = isSpatial, speed = Speed.Slow)
        }
    }
}

enum class Speed { Fast, Default, Slow }

@Composable
private fun RowScope.AnimationBox(label: String, isSpatial: Boolean, speed: Speed) {
    var toggled by remember { mutableStateOf(false) }
    val scheme = MaterialTheme.motionScheme

    // Spatial animation (Size)
    val size by animateDpAsState(
        targetValue = if (toggled) 100.dp else 60.dp,
        animationSpec = when (speed) {
            Speed.Fast -> scheme.fastSpatialSpec()
            Speed.Default -> scheme.defaultSpatialSpec()
            Speed.Slow -> scheme.slowSpatialSpec()
        }
    )

    // Effect animation (Color/Alpha)
    val color by animateColorAsState(
        targetValue = if (toggled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
        animationSpec = when (speed) {
            Speed.Fast -> scheme.fastEffectsSpec()
            Speed.Default -> scheme.defaultEffectsSpec()
            Speed.Slow -> scheme.slowEffectsSpec()
        }
    )
    val alpha by animateFloatAsState(
        targetValue = if (toggled) 1f else 0.4f,
        animationSpec = when (speed) {
            Speed.Fast -> scheme.fastEffectsSpec()
            Speed.Default -> scheme.defaultEffectsSpec()
            Speed.Slow -> scheme.slowEffectsSpec()
        }
    )

    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(if (isSpatial) size else 80.dp)
                .alpha(if (isSpatial) 1f else alpha)
                .background(if (isSpatial) MaterialTheme.colorScheme.secondary else color, MaterialTheme.shapes.medium)
                .clickable { toggled = !toggled },
            contentAlignment = Alignment.Center
        ) {
            Text(label, style = MaterialTheme.typography.labelSmall, color = Color.White)
        }
    }
}
