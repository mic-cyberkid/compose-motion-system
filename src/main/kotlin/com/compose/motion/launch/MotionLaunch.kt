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
                LaunchStyle.HolographicPulse -> HolographicEffect(content = content)
                LaunchStyle.OrbitalConverge -> OrbitalEffect(content = content)
                LaunchStyle.LiquidGlassMorph -> LiquidGlassEffect(content = content)
                LaunchStyle.AuroraGradient -> AuroraEffect(content = content)
                LaunchStyle.KineticScan -> ScanEffect(content = content)
                LaunchStyle.GlowPulse -> GlowPulseEffect(content = content)
                else -> content()
            }
        }
    }
}

@Composable
private fun HolographicEffect(content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "holographic")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.05f,
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse), label = "pulse"
    )
    val aberration by infiniteTransition.animateFloat(
        initialValue = -2f, targetValue = 2f,
        animationSpec = infiniteRepeatable(tween(3000), RepeatMode.Reverse), label = "aberration"
    )

    Box(modifier = Modifier.scale(pulseScale), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.graphicsLayer { translationX = aberration; alpha = 0.5f }) { content() }
        Box(modifier = Modifier.graphicsLayer { translationX = -aberration; alpha = 0.5f }) { content() }
        content()
    }
}

@Composable
private fun OrbitalEffect(content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "orbital")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(10000, easing = LinearEasing)), label = "rot"
    )
    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val radius = 100.dp.toPx()
            for (i in 0 until 8) {
                val angle = Math.toRadians((rotation + (i * 45)).toDouble())
                drawCircle(Color.Cyan.copy(alpha = 0.4f), 4.dp.toPx(),
                    Offset(size.width/2 + radius * cos(angle).toFloat(), size.height/2 + radius * sin(angle).toFloat()))
            }
        }
        content()
    }
}

@Composable
private fun LiquidGlassEffect(content: @Composable () -> Unit) {
    var start by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { start = true }
    val blur by animateFloatAsState(if (start) 0f else 40f, tween(1000), label = "blur")
    val scale by animateFloatAsState(if (start) 1f else 0.5f, spring(stiffness = Spring.StiffnessLow), label = "scale")
    Box(modifier = Modifier.scale(scale).blur(blur.dp)) { content() }
}

@Composable
private fun AuroraEffect(content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "aurora")
    val shift by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(8000), RepeatMode.Reverse), label = "shift"
    )
    Box(modifier = Modifier.fillMaxSize().drawBehind {
        drawRect(Brush.linearGradient(listOf(Color(0xFF001A33), Color(0xFF004D40)), end = Offset(size.width * shift, size.height)))
    }, contentAlignment = Alignment.Center) { content() }
}

@Composable
private fun ScanEffect(content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "scan")
    val y by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2000)), label = "y"
    )
    Box {
        content()
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawLine(Color.Cyan.copy(0.3f), Offset(0f, size.height * y), Offset(size.width, size.height * y), 2.dp.toPx())
        }
    }
}

@Composable
private fun GlowPulseEffect(content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "glow")
    val glow by infiniteTransition.animateFloat(
        initialValue = 0.4f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1500), RepeatMode.Reverse), label = "glow"
    )
    Box(modifier = Modifier.graphicsLayer { shadowElevation = 30f * glow; spotShadowColor = Color.White }) { content() }
}
