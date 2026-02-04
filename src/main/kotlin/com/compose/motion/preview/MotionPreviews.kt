package com.compose.motion.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.motion.content.MotionVisibility
import com.compose.motion.theme.ProvideMotionTheme
import com.compose.motion.components.MotionCard

@Preview(showBackground = true)
@Composable
fun MotionVisibilityPreview() {
    var visible by remember { mutableStateOf(true) }
    ProvideMotionTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            Button(onClick = { visible = !visible }) {
                Text("Toggle Visibility")
            }
            MotionVisibility(visible = visible) {
                Text("I am visible!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MotionCardPreview() {
    ProvideMotionTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            MotionCard(onClick = {}) {
                Text("Click me to see scale effect", modifier = Modifier.padding(16.dp))
            }
        }
    }
}
