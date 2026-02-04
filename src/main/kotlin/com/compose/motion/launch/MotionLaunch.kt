package com.compose.motion.launch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import com.compose.motion.theme.motionScheme

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
        enter = fadeIn(animationSpec = scheme.slowEffectsSpec()),
        exit = when (style) {
            LaunchStyle.Fade -> fadeOut(animationSpec = scheme.slowEffectsSpec())
            LaunchStyle.FadeScale -> fadeOut(animationSpec = scheme.slowEffectsSpec()) +
                                   scaleOut(targetScale = 1.1f, animationSpec = scheme.slowSpatialSpec())
            LaunchStyle.SlideUp -> slideOutVertically(animationSpec = scheme.slowSpatialSpec()) { -it } +
                                 fadeOut(animationSpec = scheme.slowEffectsSpec())
        },
        modifier = modifier.zIndex(Float.MAX_VALUE)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}
