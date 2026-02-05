package com.compose.motion.launch

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.compose.motion.ExperimentalMotionApi
import com.compose.motion.theme.motionScheme
import com.compose.motion.tokens.MotionDurations
import com.compose.motion.tokens.MotionEasings
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

/**
 * A comprehensive Splash Screen component that supports various branding animations.
 *
 * @param visible Whether the splash screen is currently shown.
 * @param style The [SplashStyle] to use for the animation.
 * @param logo The logo content to be animated.
 * @param backgroundColor The background color of the splash screen.
 * @param onSplashFinished Callback invoked when the entrance/idle animation cycle completes
 * or when [visible] becomes false and the exit animation finishes.
 * @param durationMillis Suggested duration for the splash animation in milliseconds.
 * @param modifier Modifier for the root container.
 */
@ExperimentalMotionApi
@Composable
fun SplashScreen(
    visible: Boolean,
    logo: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    style: SplashStyle = SplashStyle.LogoReveal,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    onSplashFinished: () -> Unit = {},
    durationMillis: Int = 3000
) {
    val scheme = MaterialTheme.motionScheme
    var animationStarted by remember { mutableStateOf(false) }

    LaunchedEffect(visible) {
        if (visible) {
            animationStarted = true
            // Basic delay to let animation play if it's a one-shot
            if (style is SplashStyle.LogoReveal || style is SplashStyle.LogoDrawPath) {
                delay(durationMillis.toLong())
                onSplashFinished()
            }
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = scheme.defaultEffectsSpec()),
        exit = fadeOut(animationSpec = scheme.defaultEffectsSpec()) +
               scaleOut(targetScale = 1.1f, animationSpec = scheme.defaultSpatialSpec()),
        modifier = modifier.zIndex(Float.MAX_VALUE)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            when (style) {
                SplashStyle.LogoReveal -> {
                    LogoRevealContent(animationStarted, logo)
                }
                SplashStyle.LogoSpin -> {
                    LogoSpinContent(logo)
                }
                SplashStyle.LogoDrawPath -> {
                    LogoDrawPathContent(animationStarted, logo)
                }
                SplashStyle.TaglineTypewriter -> {
                    TaglineTypewriterContent(animationStarted, logo)
                }
                SplashStyle.ParticleBurst -> {
                    ParticleBurstContent(animationStarted, logo)
                }
                SplashStyle.LiquidSplash -> {
                    LiquidSplashContent(animationStarted, logo)
                }
                SplashStyle.ThreeDLogoRotation -> {
                    ThreeDLogoRotationContent(logo)
                }
                else -> {
                    // Fallback to standard MotionLaunch style logic if it's one of the futuristic ones
                    // or just show logo
                    logo()
                }
            }
        }
    }
}

/** Idea #1 - Logo reveal with motion (fade + bounce) */
@Composable
private fun LogoRevealContent(started: Boolean, logo: @Composable () -> Unit) {
    val alpha by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(MotionDurations.Medium4, easing = MotionEasings.Standard),
        label = "alpha"
    )
    val scale by animateFloatAsState(
        targetValue = if (started) 1f else 0.8f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = "scale"
    )

    Box(modifier = Modifier.graphicsLayer {
        this.alpha = alpha
        this.scaleX = scale
        this.scaleY = scale
    }) {
        logo()
    }
}

/** Idea #3 - Animated emblem or icon spin */
@Composable
private fun LogoSpinContent(logo: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "spin")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(MotionDurations.ExtraLong4, easing = MotionEasings.Standard),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Box(modifier = Modifier.graphicsLayer { rotationZ = rotation }) {
        logo()
    }
}

/** Idea #1 (cont.) - Logo draw path animation (simulated via trim/stroke reveal if it were a Path)
 * For general Composables, we simulate with a circular reveal */
@Composable
private fun LogoDrawPathContent(started: Boolean, logo: @Composable () -> Unit) {
    val progress by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(MotionDurations.Long4, easing = MotionEasings.Standard),
        label = "draw"
    )

    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(200.dp)) {
            drawCircle(
                color = Color.White,
                radius = size.minDimension / 2,
                style = Stroke(width = 2.dp.toPx(), pathEffect = null),
                alpha = 0.2f
            )
            drawArc(
                color = Color.White,
                startAngle = -90f,
                sweepAngle = 360f * progress,
                useCenter = false,
                style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Box(modifier = Modifier.alpha(if (progress > 0.8f) (progress - 0.8f) * 5f else 0f)) {
            logo()
        }
    }
}

/** Idea #5 - Animated tagline scene */
@Composable
private fun TaglineTypewriterContent(started: Boolean, logo: @Composable () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        logo()
        Spacer(modifier = Modifier.height(16.dp))
        // Placeholder for tagline typewriter
        var textToDisplay by remember { mutableStateOf("") }
        val fullText = "Defining the future of motion"

        LaunchedEffect(started) {
            if (started) {
                fullText.forEachIndexed { index, _ ->
                    textToDisplay = fullText.substring(0, index + 1)
                    delay(50)
                }
            }
        }

        Text(
            text = textToDisplay,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
    }
}

/** Idea #7 - Particle effects around logo */
@Composable
private fun ParticleBurstContent(started: Boolean, logo: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "particles")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(MotionDurations.Long4, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val count = 20
            for (i in 0 until count) {
                val angle = (i.toFloat() / count) * 2 * Math.PI
                val distance = 100.dp.toPx() * time
                val x = center.x + cos(angle).toFloat() * distance
                val y = center.y + sin(angle).toFloat() * distance
                drawCircle(
                    color = Color.White,
                    radius = 2.dp.toPx(),
                    center = Offset(x, y),
                    alpha = 1f - time
                )
            }
        }
        logo()
    }
}

/** Idea #8 - Liquid splash or fluid motion transitions */
@Composable
private fun LiquidSplashContent(started: Boolean, logo: @Composable () -> Unit) {
    val progress by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(MotionDurations.Long4, easing = MotionEasings.Emphasized),
        label = "liquid"
    )

    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val radius = size.maxDimension * progress
            drawCircle(
                color = Color.White.copy(alpha = 0.1f),
                radius = radius,
                center = center
            )
        }
        Box(modifier = Modifier.scale(0.5f + 0.5f * progress).alpha(progress)) {
            logo()
        }
    }
}

/** Idea #9 - 3D transformations or perspective logo rotation */
@Composable
private fun ThreeDLogoRotationContent(logo: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "3d")
    val rotationY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(MotionDurations.ExtraLong4, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotY"
    )

    Box(modifier = Modifier.graphicsLayer {
        this.rotationY = rotationY
        this.cameraDistance = 12f * density
    }) {
        logo()
    }
}
