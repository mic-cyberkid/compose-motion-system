package com.compose.motion.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

/**
 * A horizontal pager with expressive 3D and scale transformations.
 */
@Composable
fun MotionCarousel(
    itemCount: Int,
    modifier: Modifier = Modifier,
    state: PagerState = rememberPagerState(pageCount = { itemCount }),
    contentPadding: PaddingValues = PaddingValues(horizontal = 32.dp),
    pageSpacing: androidx.compose.ui.unit.Dp = 16.dp,
    content: @Composable (index: Int) -> Unit
) {
    HorizontalPager(
        state = state,
        modifier = modifier,
        contentPadding = contentPadding,
        pageSpacing = pageSpacing
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    // Calculate the absolute distance from the current page
                    val pageOffset = (
                        (state.currentPage - page) + state.currentPageOffsetFraction
                    ).absoluteValue

                    // Scale transformation
                    val scale = lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    scaleX = scale
                    scaleY = scale

                    // 3D rotation
                    rotationY = lerp(
                        start = 0f,
                        stop = 45f,
                        fraction = pageOffset.coerceIn(0f, 1f)
                    ) * (if (state.currentPage > page) 1 else -1)

                    // Alpha fade
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
        ) {
            content(page)
        }
    }
}
