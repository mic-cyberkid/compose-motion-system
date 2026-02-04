package com.compose.motion.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.compose.motion.modifiers.MotionEnter
import com.compose.motion.modifiers.animateVisibility

/**
 * A themed alternative to [Scaffold] that provides opinionated animations for its content.
 *
 * It allows for an optional entrance animation for the main content area, which can
 * be useful for creating a polished feel when a screen first appears.
 *
 * @param modifier Modifier for the scaffold.
 * @param topBar Top app bar for the screen.
 * @param bottomBar Bottom app bar for the screen.
 * @param snackbarHost Snackbar host for the screen.
 * @param floatingActionButton Floating action button for the screen.
 * @param floatingActionButtonPosition Position of the FAB.
 * @param containerColor Background color for the scaffold.
 * @param contentColor Content color for the scaffold.
 * @param contentWindowInsets Window insets for the content.
 * @param contentEnter Entrance animation for the main content. Defaults to [MotionEnter.None].
 * @param content The main content of the screen.
 */
@Composable
fun MotionScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = androidx.compose.material3.MaterialTheme.colorScheme.background,
    contentColor: Color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    contentEnter: MotionEnter = MotionEnter.None,
    content: @Composable (PaddingValues) -> Unit
) {
    var hasAppeared by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        hasAppeared = true
    }

    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = { paddingValues ->
            if (contentEnter != MotionEnter.None) {
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier.animateVisibility(
                        visible = hasAppeared,
                        enter = contentEnter
                    )
                ) {
                    content(paddingValues)
                }
            } else {
                content(paddingValues)
            }
        }
    )
}
