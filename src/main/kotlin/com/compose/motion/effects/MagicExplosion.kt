package com.compose.motion.effects

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.compose.motion.theme.motionScheme
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

/**
 * A magical explosion effect with particles and expanding rings.
 * Perfect for celebrating a success state.
 *
 * @param visible Whether the explosion should trigger.
 * @param onComplete Callback when the animation finishes.
 */
@Composable
fun MagicExplosion(
    visible: Boolean,
    modifier: Modifier = Modifier,
    onComplete: () -> Unit = {}
) {
    if (!visible) return

    val scheme = MaterialTheme.motionScheme
    val progress = remember { Animatable(0f) }

    LaunchedEffect(visible) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
        )
        onComplete()
    }

    val particles = remember {
        List(30) {
            val angle = Random.nextFloat() * 2 * Math.PI.toFloat()
            val speed = Random.nextFloat() * 500f + 200f
            val color = listOf(Color.Cyan, Color.Magenta, Color.Yellow, Color.White).random()
            Particle(angle, speed, color)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height / 2)
            val p = progress.value

            // Expanding ring
            drawCircle(
                color = Color.White,
                radius = p * 300.dp.toPx(),
                center = center,
                style = Stroke(width = (1f - p) * 10.dp.toPx()),
                alpha = 1f - p
            )

            // Particles
            particles.forEach { particle ->
                val distance = particle.speed * p
                val x = center.x + cos(particle.angle) * distance
                val y = center.y + sin(particle.angle) * distance

                drawCircle(
                    color = particle.color,
                    radius = 4.dp.toPx() * (1f - p),
                    center = Offset(x, y),
                    alpha = 1f - p
                )
            }
        }
    }
}

private data class Particle(
    val angle: Float,
    val speed: Float,
    val color: Color
)
