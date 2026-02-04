package com.compose.motion.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

/**
 * A themed alternative to [TopAppBar] with optional motion support.
 *
 * It supports standard Material 3 scroll behaviors and provides an option
 * to animate the title's opacity based on the scroll progress.
 *
 * @param title The title to be displayed in the top app bar.
 * @param modifier Modifier for the top app bar.
 * @param navigationIcon Navigation icon to be displayed at the start.
 * @param actions Actions to be displayed at the end.
 * @param windowInsets Window insets for the top app bar.
 * @param colors Colors for the top app bar.
 * @param scrollBehavior [TopAppBarScrollBehavior] to handle scroll-aware effects.
 * @param animateTitleOnScroll Whether to animate the title's alpha based on scroll progress.
 *                             Requires [scrollBehavior] to be provided. Defaults to false.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotionTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable (androidx.compose.foundation.layout.RowScope.() -> Unit) = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    animateTitleOnScroll: Boolean = false
) {
    val titleWithMotion = @Composable {
        val alpha = if (animateTitleOnScroll && scrollBehavior != null) {
            1f - scrollBehavior.state.collapsedFraction
        } else {
            1f
        }

        androidx.compose.foundation.layout.Box(
            modifier = Modifier.graphicsLayer { this.alpha = alpha }
        ) {
            title()
        }
    }

    TopAppBar(
        title = titleWithMotion,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        windowInsets = windowInsets,
        colors = colors,
        scrollBehavior = scrollBehavior
    )
}
