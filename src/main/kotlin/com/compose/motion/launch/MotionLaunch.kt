package com.compose.motion.launch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.compose.motion.theme.motionScheme
import kotlin.math.cos
import kotlin.math.sin

/**
 * A pure-Compose launch animation container.
 *
 * This provides a flexible way to implement custom splash or launch screens
 * entirely in Compose, without relying on the system SplashScreen API.
 *
 * It is entirely driven by the [visible] parameter, allowing the application
 * to control when the launch animation starts and ends (e.g., after data is loaded).
 *
 * @param visible Whether the launch screen should be visible.
 * @param style The style of the exit animation when [visible] becomes false.
 *              Defaults to [LaunchStyle.FadeScale].
 * @param modifier Modifier for the container.
 * @param backgroundColor Background color of the launch screen. Defaults to theme background.
 * @param content The content to display on the launch screen (e.g., app logo).
 */
@Composable
fun MotionLaunch(
    visible: Boolean,
    style: LaunchStyle = LaunchStyle.FadeScale,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit
) {
    val scheme = MaterialTheme.motionScheme

    AnimatedVisibility(
        visible = visible,
        enter = if (style == LaunchStyle.None) androidx.compose.animation.EnterTransition.None else fadeIn(animationSpec = scheme.slowEffectsSpec()),
        exit = when (style) {
            LaunchStyle.None -> androidx.compose.animation.ExitTransition.None
            LaunchStyle.Fade -> fadeOut(animationSpec = scheme.slowEffectsSpec())
            LaunchStyle.FadeScale -> fadeOut(animationSpec = scheme.slowEffectsSpec()) +
                                   scaleOut(targetScale = 1.1f, animationSpec = scheme.slowSpatialSpec())
            LaunchStyle.SlideUp -> slideOutVertically(animationSpec = scheme.slowSpatialSpec()) { -it } +
                                 fadeOut(animationSpec = scheme.slowEffectsSpec())
            else ->
                fadeOut(animationSpec = scheme.slowEffectsSpec()) +
                scaleOut(targetScale = 1.2f, animationSpec = scheme.slowSpatialSpec())
        },
        modifier = modifier.zIndex(Float.MAX_VALUE)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            when (style) {
                LaunchStyle.HolographicPulse -> {
                    HolographicEffect(content = content)
                }
                LaunchStyle.OrbitalConverge -> {
                    OrbitalEffect(content = content)
                }
                LaunchStyle.LiquidGlassMorph -> {
                    LiquidGlassEffect(content = content)
                }
                LaunchStyle.AuroraGradient -> {
                    AuroraEffect(content = content)
                }
                LaunchStyle.KineticScan -> {
                    ScanEffect(content = content)
                }
                else -> content()
            }
        }
    }
}

@Composable
private fun HolographicEffect(content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "holographic")

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    val aberrationOffset by infiniteTransition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "aberration"
    )

    Box(
        modifier = Modifier
            .scale(pulseScale)
            .graphicsLayer {
                shadowElevation = 20f
                spotShadowColor = Color.Cyan.copy(alpha = 0.5f)
            },
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.graphicsLayer {
            translationX = aberrationOffset
            alpha = 0.5f
        }) {
            content()
        }
        Box(modifier = Modifier.graphicsLayer {
            translationX = -aberrationOffset
            alpha = 0.5f
        }) {
            content()
        }
        content()
    }
}

@Composable
private fun OrbitalEffect(content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "orbital")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2, size.height / 2)
            val radius = 100.dp.toPx() * pulse

            for (i in 0 until 12) {
                val angle = Math.toRadians((rotation + (i * 30)).toDouble())
                val x = center.x + radius * cos(angle).toFloat()
                val y = center.y + radius * sin(angle).toFloat()

                drawCircle(
                    color = Color.Cyan.copy(alpha = 0.3f),
                    radius = 4.dp.toPx(),
                    center = Offset(x, y)
                )
            }
        }
        content()
    }
}

@Composable
private fun LiquidGlassEffect(content: @Composable () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }
    val scheme = MaterialTheme.motionScheme

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val blurAmount by animateFloatAsState(
        targetValue = if (startAnimation) 0f else 40f,
        animationSpec = scheme.slowSpatialSpec(),
        label = "blur"
    )

    val scaleAmount by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.4f,
        animationSpec = scheme.slowSpatialSpec(),
        label = "scale"
    )

    val alphaAmount by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = scheme.slowEffectsSpec(),
        label = "alpha"
    )

    Box(
        modifier = Modifier
            .scale(scaleAmount)
            .alpha(alphaAmount)
            .blur(blurAmount.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun AuroraEffect(content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "aurora")
    val shift by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shift"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                val brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF001A33), Color(0xFF004D40), Color(0xFF1A237E)),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * shift, size.height)
                )
                drawRect(brush)
            },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun ScanEffect(content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "scan")
    val scanProgress by infiniteTransition.animateFloat(
        initialValue = -0.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "scan"
    )

    Box(contentAlignment = Alignment.Center) {
        content()
        Canvas(modifier = Modifier.fillMaxSize()) {
            val y = size.height * scanProgress
            drawLine(
                color = Color.Cyan.copy(alpha = 0.5f),
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 2.dp.toPx()
            )
        }
    }
}
