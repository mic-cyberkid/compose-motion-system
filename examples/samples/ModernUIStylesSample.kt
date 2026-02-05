package com.compose.motion.examples

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.motion.components.MotionSurface
import com.compose.motion.content.MotionCarousel
import com.compose.motion.modifiers.*
import com.compose.motion.theme.MotionStyle
import com.compose.motion.theme.ProvideMotionTheme
import com.compose.motion.theme.MotionScheme

@Composable
fun ModernUIStylesSample() {
    var selectedStyle by remember { mutableStateOf(MotionStyle.standard()) }

    ProvideMotionTheme(
        scheme = MotionScheme.expressive(),
        style = selectedStyle
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Modern UI Styles") },
                    actions = {
                        StyleSelector(onStyleSelected = { selectedStyle = it })
                    }
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier.padding(padding).fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text("Interactive Carousel", style = MaterialTheme.typography.titleMedium)
                    MotionCarousel(itemCount = 5, modifier = Modifier.height(200.dp)) { index ->
                        MotionSurface(modifier = Modifier.fillMaxSize()) {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                Text("Page $index", style = MaterialTheme.typography.headlineMedium)
                            }
                        }
                    }
                }

                item {
                    Text("Advanced Gestures", style = MaterialTheme.typography.titleMedium)
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        MotionSurface(
                            modifier = Modifier
                                .size(100.dp)
                                .elasticDrag()
                        ) {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                Text("Drag Me", style = MaterialTheme.typography.labelSmall)
                            }
                        }

                        MotionSurface(
                            modifier = Modifier
                                .size(100.dp)
                                .spotlight()
                        ) {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                Text("Spotlight", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }

                itemsIndexed(List(10) { "Scrolling Item $it" }) { index, item ->
                    MotionSurface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .revealOnScroll(direction = if (index % 2 == 0) RevealDirection.Right else RevealDirection.Left)
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            Text(item)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StyleSelector(onStyleSelected: (MotionStyle) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(androidx.compose.material.icons.Icons.Default.Settings, contentDescription = "Styles")
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        DropdownMenuItem(text = { Text("Standard") }, onClick = { onStyleSelected(MotionStyle.standard()); expanded = false })
        DropdownMenuItem(text = { Text("Apple Glass") }, onClick = { onStyleSelected(MotionStyle.appleGlass()); expanded = false })
        DropdownMenuItem(text = { Text("Cyber Glitch") }, onClick = { onStyleSelected(MotionStyle.cyberGlitch()); expanded = false })
        DropdownMenuItem(text = { Text("Neumorphic") }, onClick = { onStyleSelected(MotionStyle.neumorphic()); expanded = false })
        DropdownMenuItem(text = { Text("Minimal Fluid") }, onClick = { onStyleSelected(MotionStyle.minimalFluid()); expanded = false })
    }
}
