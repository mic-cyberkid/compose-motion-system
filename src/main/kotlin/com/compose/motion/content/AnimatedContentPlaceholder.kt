package com.compose.motion.content

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.motion.effects.shimmer
import com.compose.motion.theme.motionScheme

/**
 * A content wrapper that shows a shimmer placeholder and then cross-fades to the real content.
 */
@Composable
fun StaggeredContentReveal(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    placeholder: @Composable () -> Unit = {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxSize()
                .shimmer()
        )
    },
    content: @Composable () -> Unit
) {
    val scheme = MaterialTheme.motionScheme

    Crossfade(
        targetState = isLoading,
        animationSpec = scheme.defaultEffectsSpec(),
        label = "staggeredReveal",
        modifier = modifier
    ) { loading ->
        if (loading) {
            placeholder()
        } else {
            content()
        }
    }
}
