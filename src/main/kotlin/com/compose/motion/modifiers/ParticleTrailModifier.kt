package com.compose.motion.modifiers

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.isActive
import kotlin.random.Random

/**
 * A modifier that adds a subtle particle trail following the user's drag gesture.
 */
fun Modifier.particleTrail(
    color: Color = Color.Cyan.copy(alpha = 0.5f),
    maxParticles: Int = 20
): Modifier = composed {
    val particles = remember { mutableStateListOf<ParticleData>() }

    LaunchedEffect(Unit) {
        while (isActive) {
            withFrameMillis { _ ->
                val it = particles.iterator()
                while (it.hasNext()) {
                    val p = it.next()
                    p.age += 0.04f
                    if (p.age >= 1f) {
                        it.remove()
                    }
                }
            }
        }
    }

    this.pointerInput(Unit) {
        detectDragGestures { change, _ ->
            if (particles.size < maxParticles) {
                particles.add(
                    ParticleData(
                        position = change.position,
                        radius = (Random.nextFloat() * 4 + 2).dp.toPx(),
                        age = 0f
                    )
                )
            }
        }
    }
    .drawBehind {
        particles.forEach { p ->
            drawCircle(
                color = color.copy(alpha = (1f - p.age) * color.alpha),
                radius = p.radius * (1f - p.age),
                center = p.position
            )
        }
    }
}

private class ParticleData(
    val position: Offset,
    val radius: Float,
    var age: Float
)
