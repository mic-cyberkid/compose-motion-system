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
 * A confetti burst effect.
 *
 * @param trigger A key that triggers the burst when changed.
 */
@Composable
fun ConfettiBurst(
    trigger: Any?,
    modifier: Modifier = Modifier,
    particleCount: Int = 30,
    colors: List<Color> = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Magenta)
) {
    var particles by remember { mutableStateOf(emptyList<ConfettiParticle>()) }
    val progress = remember { Animatable(0f) }

    LaunchedEffect(trigger) {
        if (trigger == null) return@LaunchedEffect

        particles = List(particleCount) {
            val angle = Random.nextFloat() * 360f
            val speed = Random.nextFloat() * 400f + 200f
            ConfettiParticle(
                color = colors.random(),
                velocity = Offset(
                    cos(Math.toRadians(angle.toDouble())).toFloat() * speed,
                    sin(Math.toRadians(angle.toDouble())).toFloat() * speed
                )
            )
        }

        progress.snapTo(0f)
        progress.animateTo(1f, tween(1500, easing = LinearOutSlowInEasing))
        particles = emptyList()
    }

    if (particles.isNotEmpty()) {
        Canvas(modifier = modifier.fillMaxSize()) {
            val p = progress.value
            particles.forEach { particle ->
                val x = center.x + particle.velocity.x * p
                val y = center.y + particle.velocity.y * p + (500f * p * p) // Add "gravity"

                drawCircle(
                    color = particle.color.copy(alpha = 1f - p),
                    radius = 4.dp.toPx(),
                    center = Offset(x, y)
                )
            }
        }
    }
}

private data class ConfettiParticle(
    val color: Color,
    val velocity: Offset
)
