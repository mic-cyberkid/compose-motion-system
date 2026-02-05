package com.compose.motion.loading

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.compose.motion.ExperimentalMotionApi
import com.compose.motion.theme.motionScheme
import com.compose.motion.tokens.MotionDurations
import com.compose.motion.tokens.MotionEasings
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

/**
 * A simple spinner loader.
 */
@ExperimentalMotionApi
@Composable
fun SpinnerLoader(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = 4.dp
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
        strokeWidth = strokeWidth
    )
}

/**
 * A standard linear progress bar loader.
 */
@ExperimentalMotionApi
@Composable
fun ProgressBarLoader(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    LinearProgressIndicator(
        progress = { progress },
        modifier = modifier.clip(CircleShape),
        color = color,
        trackColor = trackColor
    )
}

/**
 * Idea #13 - Bouncing dots or shapes.
 */
@ExperimentalMotionApi
@Composable
fun DotsLoader(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    dotSize: Dp = 10.dp,
    spacing: Dp = 4.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "dots")

    @Composable
    fun Dot(delay: Int) {
        val yOffset by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = -10f,
            animationSpec = infiniteRepeatable(
                animation = tween(400, delayMillis = delay, easing = MotionEasings.Standard),
                repeatMode = RepeatMode.Reverse
            ),
            label = "dot"
        )
        Box(
            modifier = Modifier
                .size(dotSize)
                .graphicsLayer { translationY = yOffset.dp.toPx() }
                .background(color, CircleShape)
        )
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Dot(0)
        Dot(150)
        Dot(300)
    }
}

/**
 * Idea #14 - Skeleton screens with shimmer effects.
 */
@ExperimentalMotionApi
@Composable
fun SkeletonLoader(
    modifier: Modifier = Modifier,
    width: Dp = 200.dp,
    height: Dp = 20.dp,
    cornerRadius: Dp = 4.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val xOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "xOffset"
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            Color.LightGray.copy(alpha = 0.3f),
            Color.LightGray.copy(alpha = 0.5f),
            Color.LightGray.copy(alpha = 0.3f),
        ),
        start = Offset(xOffset * width.value, 0f),
        end = Offset((xOffset + 1f) * width.value, 0f)
    )

    Box(
        modifier = modifier
            .size(width, height)
            .background(brush, RoundedCornerShape(cornerRadius))
    )
}

/**
 * Idea #17 - Loading screen with helpful tips.
 */
@ExperimentalMotionApi
@Composable
fun LoadingWithTips(
    tips: List<String>,
    modifier: Modifier = Modifier,
    indicator: @Composable () -> Unit = { SpinnerLoader() }
) {
    var currentTipIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(tips) {
        while (true) {
            delay(4000)
            currentTipIndex = (currentTipIndex + 1) % tips.size
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        indicator()
        Spacer(modifier = Modifier.height(24.dp))
        AnimatedContent(
            targetState = tips[currentTipIndex],
            transitionSpec = {
                fadeIn() + slideInVertically { it } togetherWith fadeOut() + slideOutVertically { -it }
            },
            label = "tip"
        ) { tip ->
            Text(
                text = tip,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }
    }
}

/**
 * Idea #19 - Particle or fluid progress.
 */
@ExperimentalMotionApi
@Composable
fun ParticleLoader(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    val infiniteTransition = rememberInfiniteTransition(label = "particles")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        ),
        label = "rotation"
    )

    Canvas(modifier = modifier.size(64.dp)) {
        val radius = size.minDimension / 3
        for (i in 0 until 8) {
            val angle = (i * 45f + rotation) * (Math.PI / 180f).toFloat()
            val x = center.x + cos(angle) * radius
            val y = center.y + sin(angle) * radius
            drawCircle(
                color = color,
                radius = 4.dp.toPx() * (1f - (i / 10f)),
                center = Offset(x, y),
                alpha = 1f - (i / 8f)
            )
        }
    }
}

/**
 * Idea #23 - Epic "game-style" loading (large progress, detailed art).
 */
@ExperimentalMotionApi
@Composable
fun EpicGameLoader(
    progress: Float,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize().background(Color.Black)) {
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, color = Color.White, style = MaterialTheme.typography.headlineMedium)
            Text(subtitle, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(32.dp))
            Box(modifier = Modifier.width(300.dp).height(4.dp).background(Color.DarkGray)) {
                Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(progress).background(Color.White))
            }
            Text("${(progress * 100).toInt()}%", color = Color.White, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

/**
 * Idea #25 - Treasure chest or reward reveal progress.
 */
@ExperimentalMotionApi
@Composable
fun TreasureChestProgress(
    progress: Float,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        val scale = 1f + (progress * 0.2f)
        val shake = if (progress > 0.9f) (sin(System.currentTimeMillis() / 50.0) * 5.0).toFloat() else 0f

        Box(modifier = Modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
            rotationZ = shake
        }) {
            // Placeholder for a chest
            Canvas(modifier = Modifier.size(80.dp)) {
                drawRoundRect(
                    color = Color(0xFF8B4513),
                    size = Size(size.width, size.height * 0.6f),
                    topLeft = Offset(0f, size.height * 0.4f),
                    cornerRadius = CornerRadius(8.dp.toPx())
                )
                drawRect(
                    color = Color.Yellow,
                    size = Size(10.dp.toPx(), 10.dp.toPx()),
                    topLeft = Offset(size.width / 2 - 5.dp.toPx(), size.height * 0.5f)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProgressBarLoader(progress = progress, modifier = Modifier.width(120.dp))
    }
}

/**
 * Idea #29 - Progressive reveal loader (unblurring image).
 */
@ExperimentalMotionApi
@Composable
fun ProgressiveRevealLoader(
    progress: Float,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.graphicsLayer {
        alpha = progress
    }) {
        Box(modifier = Modifier.graphicsLayer {
            // In Compose 1.7+ we can use blur modifier
        }.blur(radius = 20.dp * (1f - progress))) {
            content()
        }
    }
}

/**
 * A full screen loading overlay.
 */
@ExperimentalMotionApi
@Composable
fun LoadingOverlay(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable MotionLoadingScope.() -> Unit = { SpinnerLoader() }
) {
    val scheme = MaterialTheme.motionScheme
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = scheme.defaultEffectsSpec()),
        exit = fadeOut(animationSpec = scheme.defaultEffectsSpec()),
        modifier = modifier.zIndex(100f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            val scope = remember { MotionLoadingScopeImpl(0f, LoadingState.Loading, null) }
            scope.content()
        }
    }
}
