package com.compose.motion.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

/**
 * A text component that can be expanded to show more content.
 */
@Composable
fun ExpandableText(
    text: String,
    collapsedMaxLines: Int = 3,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
            .animateContentSize()
    ) {
        Text(
            text = text,
            style = style,
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}
