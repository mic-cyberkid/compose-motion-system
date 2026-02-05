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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.compose.motion.ExperimentalMotionApi
import com.compose.motion.theme.motionScheme
import kotlin.math.cos
import kotlin.math.sin

/**
 * A pure-Compose launch animation container.
 */
@ExperimentalMotionApi
@Composable
fun MotionLaunch(
    visible: Boolean,
    style: SplashStyle = SplashStyle.FadeScale,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit
) {
    val scheme = MaterialTheme.motionScheme

    AnimatedVisibility(
        visible = visible,
        enter = if (style == SplashStyle.None) androidx.compose.animation.EnterTransition.None else fadeIn(animationSpec = scheme.slowEffectsSpec()),
        exit = when (style) {
            SplashStyle.None -> androidx.compose.animation.ExitTransition.None
            SplashStyle.Fade -> fadeOut(animationSpec = scheme.slowEffectsSpec())
            SplashStyle.FadeScale -> fadeOut(animationSpec = scheme.slowEffectsSpec()) +
                                   scaleOut(targetScale = 1.1f, animationSpec = scheme.slowSpatialSpec())
            SplashStyle.SlideUp -> slideOutVertically(animationSpec = scheme.slowSpatialSpec()) { -it } +
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
                SplashStyle.HolographicPulse -> HolographicEffect(content = content)
                SplashStyle.OrbitalConverge -> OrbitalEffect(content = content)
                SplashStyle.LiquidGlassMorph -> LiquidGlassEffect(content = content)
                SplashStyle.AuroraGradient -> AuroraEffect(content = content)
                SplashStyle.KineticScan -> ScanEffect(content = content)
                SplashStyle.GlowPulse -> GlowPulseEffect(content = content)
                SplashStyle.MatrixRain -> MatrixEffect(content = content)
                SplashStyle.BentoReveal -> BentoEffect(content = content)
                SplashStyle.GlassReveal -> GlassRevealEffect(content = content)
                SplashStyle.OrganicMorph -> OrganicEffect(content = content)
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
    val blur by animateFloatAsState(if (start) 0f else 40f, tween(1200), label = "blur")
    val scale by animateFloatAsState(if (start) 1f else 0.5f, spring(stiffness = Spring.StiffnessLow), label = "scale")
    val alpha by animateFloatAsState(if (start) 1f else 0f, tween(800), label = "alpha")
    Box(modifier = Modifier.scale(scale).alpha(alpha).blur(blur.dp)) { content() }
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

@Composable
private fun MatrixEffect(content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "matrix")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(4000, easing = LinearEasing)), label = "matrix"
    )
    Box(modifier = Modifier.fillMaxSize().drawBehind {
        val columns = 20
        val columnWidth = size.width / columns
        for (i in 0 until columns) {
            val x = i * columnWidth
            val yOffset = ((progress + (i * 0.13f)) % 1f) * size.height
            drawRect(
                Brush.verticalGradient(listOf(Color.Transparent, Color.Green.copy(0.2f), Color.Transparent)),
                topLeft = Offset(x, yOffset - 200.dp.toPx()),
                size = Size(columnWidth / 2, 200.dp.toPx())
            )
        }
    }, contentAlignment = Alignment.Center) { content() }
}

@Composable
private fun BentoEffect(content: @Composable () -> Unit) {
    var start by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { start = true }
    val gridProgress by animateFloatAsState(
        if (start) 1f else 0f, tween(1200, easing = FastOutSlowInEasing), label = "bento"
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        content()
        Canvas(modifier = Modifier.fillMaxSize()) {
            val grid = 4
            val cellW = size.width / grid
            val cellH = size.height / grid
            for (i in 0 until grid) {
                for (j in 0 until grid) {
                    val delay = (i + j) * 0.1f
                    val cellProgress = (gridProgress - delay).coerceIn(0f, 1f)
                    if (cellProgress < 1f) {
                        drawRect(Color.Black, Offset(i * cellW, j * cellH), Size(cellW, cellH), 1f - cellProgress)
                    }
                }
            }
        }
    }
}

@Composable
private fun GlassRevealEffect(content: @Composable () -> Unit) {
    var start by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { start = true }
    val alpha by animateFloatAsState(if (start) 1f else 0f, tween(1500), label = "alpha")
    val scale by animateFloatAsState(if (start) 1f else 1.2f, spring(stiffness = Spring.StiffnessVeryLow), label = "scale")

    Box(modifier = Modifier.scale(scale).alpha(alpha), contentAlignment = Alignment.Center) {
        content()
    }
}

@Composable
private fun OrganicEffect(content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "organic")
    val morph by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(3000), RepeatMode.Reverse), label = "morph"
    )
    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val radius = size.minDimension / 2
            drawCircle(
                color = Color.Magenta.copy(alpha = 0.2f),
                radius = radius * (0.8f + 0.2f * morph),
                center = center
            )
        }
        content()
    }
}
