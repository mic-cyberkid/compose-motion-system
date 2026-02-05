package com.compose.motion.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.compose.motion.theme.motionScheme

/**
 * A tab-like row where the selection indicator morphs between positions.
 */
@Composable
fun PillMorphRow(
    options: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var width by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val scheme = MaterialTheme.motionScheme

    val offset by animateDpAsState(
        targetValue = width * selectedIndex,
        animationSpec = scheme.defaultSpatialSpec(),
        label = "pillOffset"
    )

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
            .padding(4.dp)
            .onGloballyPositioned {
                width = with(density) { (it.size.width / options.size).toDp() }
            }
    ) {
        // Selection Indicator
        Box(
            modifier = Modifier
                .offset(x = offset)
                .width(width)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primary, CircleShape)
        )

        Row(modifier = Modifier.fillMaxSize()) {
            options.forEachIndexed { index, option ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(CircleShape)
                        .clickable { onOptionSelected(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = option,
                        color = if (selectedIndex == index) MaterialTheme.colorScheme.onPrimary
                               else MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}
