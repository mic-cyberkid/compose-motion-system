package com.compose.motion.effects

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

/**
 * A particle explosion effect with physics-like behavior.
 */
@Composable
fun MagicExplosion(
    trigger: Any?,
    modifier: Modifier = Modifier,
    count: Int = 24,
    color: Color = Color.Cyan
) {
    var particles by remember { mutableStateOf(emptyList<PhysicsParticle>()) }
    val progress = remember { Animatable(0f) }

    LaunchedEffect(trigger) {
        if (trigger == null) return@LaunchedEffect

        particles = List(count) {
            val angle = Random.nextFloat() * 360f
            val velocity = Random.nextFloat() * 600f + 200f
            PhysicsParticle(
                vx = cos(Math.toRadians(angle.toDouble())).toFloat() * velocity,
                vy = sin(Math.toRadians(angle.toDouble())).toFloat() * velocity,
                size = Random.nextFloat() * 4.dp.value + 2.dp.value
            )
        }

        progress.snapTo(0f)
        progress.animateTo(1f, tween(1000, easing = LinearOutSlowInEasing))
        particles = emptyList()
    }

    if (particles.isNotEmpty()) {
        Canvas(modifier = modifier.fillMaxSize()) {
            val p = progress.value
            particles.forEach { dot ->
                val x = center.x + dot.vx * p
                val y = center.y + dot.vy * p + (400f * p * p) // Gravity

                drawCircle(
                    color = color.copy(alpha = 1f - p),
                    radius = dot.size * (1f - p),
                    center = Offset(x, y)
                )
            }
        }
    }
}

private class PhysicsParticle(val vx: Float, val vy: Float, val size: Float)
