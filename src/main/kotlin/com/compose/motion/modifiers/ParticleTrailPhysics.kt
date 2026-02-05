// src/main/kotlin/com/compose/motion/modifiers/ParticleTrailPhysics.kt

package com.compose.motion.modifiers

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.compose.motion.theme.motionScheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

/**
 * Adds a subtle trailing particle effect during drag gestures.
 *
 * Particles are emitted near the drag position and move with a gentle velocity
 * influenced by drag direction + small random component. They fade out following
 * the current [MotionScheme]'s fast effect timing.
 *
 * Designed to feel responsive yet restrained — suitable for reorder handles,
 * swipe-to-dismiss, carousel drags, etc.
 *
 * @param color Base color of the particles (alpha is modulated internally).
 *              Default uses onSurfaceVariant at low opacity.
 * @param maxParticles Maximum number of live particles (performance guard).
 *                     Recommended range: 6–16. Default 10.
 * @param enabled Controls whether the trail is active. Default true.
 * @param density Controls spawn frequency (0.0–1.0). Higher = denser trail.
 *                Default 0.4f (balanced for most cases).
 * @param withBlur Adds a very subtle blur/glow to particles (GPU cost).
 *                 Default false — enable only when the visual premium justifies it.
 */
fun Modifier.particleTrailOnDragPhy(
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.35f),
    maxParticles: Int = 10,
    enabled: Boolean = true,
    density: Float = 0.4f,
    withBlur: Boolean = false
): Modifier = composed {

    if (!enabled) return@composed this

    val scheme = MaterialTheme.motionScheme

    // We approximate lifetime from the fast effect spec duration (if tween)
    // Fallback to sensible range if spring-based
    val nominalLifetimeMs by remember(scheme) {
        derivedStateOf {
            // Very conservative heuristic — we want ~400–800 ms feel
            when {
                scheme.fastEffectsSpec() is androidx.compose.animation.core.TweenSpec<*> ->
                    (scheme.fastEffectsSpec() as androidx.compose.animation.core.TweenSpec<*>).durationMillis.coerceIn(300, 900)
                else -> 600
            }
        }
    }

    var lastEmitPosition by remember { mutableStateOf(Offset.Zero) }
    val particles = remember { mutableStateListOf<DragTrailParticle>() }

    // Main gesture detection
    this.pointerInput(Unit) {
        detectDragGestures(
            onDragStart = { offset ->
                lastEmitPosition = offset
                // Optional: very small initial burst could be added here later
            },
            onDragEnd = {
                // Particles continue fading naturally — no forced cleanup
            },
            onDragCancel = {
                // same as above
            },
            onDrag = { change, _ ->
                change.consume()
                val current = change.position

                val distance = (current - lastEmitPosition).getDistance()
                val shouldEmit = distance > 6.dp.toPx() && particles.size < maxParticles

                if (shouldEmit && Random.nextFloat() < density) {
                    val dragDirection = (current - lastEmitPosition).normalizeOrZero()
                    val randomAngle = Random.nextFloat() * 2f * PI.toFloat()
                    val spread = Random.nextFloat() * 0.6f + 0.2f   // subtle spread

                    val velocity = Offset(
                        x = dragDirection.x * 60f + cos(randomAngle) * spread * 35f,
                        y = dragDirection.y * 60f + sin(randomAngle) * spread * 35f
                    )

                    particles.add(
                        DragTrailParticle(
                            position = current,
                            velocity = velocity,
                            birthTimeMs = System.currentTimeMillis(),
                            lifetimeMs = nominalLifetimeMs
                        )
                    )

                    lastEmitPosition = current
                }
            }
        )
    }
        // Very light blur/glow when requested (subtle — not disco)
        .graphicsLayer {
            if (withBlur) {
                renderEffect = androidx.compose.ui.graphics.RenderEffect.createBlurEffect(
                    radiusX = 1.8.dp.toPx(),
                    radiusY = 1.8.dp.toPx(),
                    edgeTreatment = androidx.compose.ui.graphics.TileMode.Clamp
                )
            }
        }
        // Particle rendering
        .drawWithContent {
            drawContent()

            val now = System.currentTimeMillis()

            particles.removeAll { particle ->
                val ageMs = (now - particle.birthTimeMs).toFloat()
                val rawProgress = (ageMs / particle.lifetimeMs).coerceIn(0f, 1f)

                if (rawProgress >= 1f) {
                    true // remove
                } else {
                    // Use scheme fast effect easing for natural fade
                    val easedProgress = scheme.fastEffects.transform(rawProgress)

                    val alpha = 1f - easedProgress
                    val scale = 1f - easedProgress * 0.65f   // gentle shrink

                    // Very light gravity + continued motion
                    val gravity = Offset(0f, 0.8f)   // subtle downward bias
                    val currentPos = particle.position +
                            particle.velocity * (ageMs / 1000f) +
                            gravity * (ageMs / 1000f) * (ageMs / 1000f) * 0.5f

                    drawCircle(
                        color = color.copy(alpha = alpha * color.alpha),
                        radius = 2.8.dp.toPx() * scale,
                        center = currentPos
                    )
                    false
                }
            }
        }
}

private data class DragTrailParticlePhysics(
    val position: Offset,
    val velocity: Offset,
    val birthTimeMs: Long,
    val lifetimeMs: Int
)

private fun Offset.normalizeOrZero(): Offset {
    val len = getDistance()
    return if (len > 0.001f) this / len else Offset.Zero
}
