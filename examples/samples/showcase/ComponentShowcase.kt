package com.compose.motion.examples.showcase

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.compose.motion.components.MotionScaffold
import com.compose.motion.components.MotionSurface
import com.compose.motion.components.MotionTopAppBar

/**
 * Demonstrates the use of high-level motion components.
 * It showcases [MotionScaffold] and [MotionTopAppBar] with scroll-linked effects.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentShowcase() {
    MotionScaffold(
        topBar = {
            MotionTopAppBar(
                title = { Text("Component Showcase") },
                animateTitleOnScroll = true
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    "Motion-Aware Layout",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    "The components below are hosted within a MotionScaffold, " +
                    "which coordinates animations between the TopAppBar and the content.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            items(10) { index ->
                MotionSurface(
                    modifier = Modifier.fillMaxWidth().height(100.dp)
                ) {
                    ListItem(
                        headlineContent = { Text("Surface Card $index") },
                        supportingContent = { Text("Adapts to the current MotionStyle") }
                    )
                }
            }
        }
    }
}
