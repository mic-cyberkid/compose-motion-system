package com.compose.motion.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

/**
 * A text component that animates number changes with a sliding effect.
 */
@Composable
fun AnimatedCounter(
    count: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    prefix: String = "",
    suffix: String = ""
) {
    Row(modifier = modifier) {
        if (prefix.isNotEmpty()) Text(prefix, style = style)

        count.toString().forEach { char ->
            AnimatedContent(
                targetState = char,
                transitionSpec = {
                    slideInVertically { it } + fadeIn() togetherWith slideOutVertically { -it } + fadeOut()
                },
                label = "counter"
            ) { targetChar ->
                Text(targetChar.toString(), style = style)
            }
        }

        if (suffix.isNotEmpty()) Text(suffix, style = style)
    }
}
