package com.compose.motion.examples.showcase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.motion.components.MotionSurface
import com.compose.motion.theme.MotionStyle
import com.compose.motion.theme.MotionThemeDefaults
import com.compose.motion.theme.ProvideMotionTheme
import com.compose.motion.theme.motionStyle

/**
 * A comprehensive showcase of all built-in [MotionStyle] presets.
 * It demonstrates how the entire UI adapts to different visual languages
 * (Glassmorphism, Neumorphism, Material, etc.) using [MotionThemeDefaults].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeShowcase() {
    var currentTheme by remember { mutableStateOf(MotionThemeDefaults.AuraGlass) }
    var expanded by remember { mutableStateOf(false) }

    ProvideMotionTheme(currentTheme) {
        val style = MaterialTheme.motionStyle

        Scaffold(
            containerColor = style.backgroundColor,
            topBar = {
                TopAppBar(
                    title = { Text("Theme Showcase", color = Color.White) },
                    actions = {
                        Box {
                            IconButton(onClick = { expanded = true }) {
                                Icon(Icons.Default.Settings, contentDescription = "Select Theme", tint = Color.White)
                            }
                            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                                ThemeMenuItem("Aura Glass", MotionThemeDefaults.AuraGlass) { currentTheme = it; expanded = false }
                                ThemeMenuItem("Aura Emerald", MotionThemeDefaults.AuraEmerald) { currentTheme = it; expanded = false }
                                ThemeMenuItem("Aura Neumorphic", MotionThemeDefaults.AuraNeumorphic) { currentTheme = it; expanded = false }
                                ThemeMenuItem("Tactile Neumorphic", MotionThemeDefaults.TactileNeumorphic) { currentTheme = it; expanded = false }
                                ThemeMenuItem("visionOS", MotionThemeDefaults.VisionOS) { currentTheme = it; expanded = false }
                                ThemeMenuItem("Chromatic Liquid", MotionThemeDefaults.ChromaticLiquid) { currentTheme = it; expanded = false }
                                HorizontalDivider()
                                ThemeMenuItem("Apple Glass", MotionThemeDefaults.AppleGlass) { currentTheme = it; expanded = false }
                                ThemeMenuItem("Cyber Glitch", MotionThemeDefaults.CyberGlitch) { currentTheme = it; expanded = false }
                                ThemeMenuItem("Standard", MotionThemeDefaults.Default) { currentTheme = it; expanded = false }
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .then(
                        style.backgroundBrush?.let { Modifier.background(it) } ?: Modifier
                    )
                    .padding(padding)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Balance Card
                    item {
                        MotionSurface(
                            modifier = Modifier.fillMaxWidth().height(160.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp).fillMaxSize(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text("Total Balance", style = MaterialTheme.typography.labelMedium, color = Color.White.copy(alpha = 0.6f))
                                Text("$124,500.00", style = MaterialTheme.typography.displaySmall, color = Color.White)
                            }
                        }
                    }

                    // Quick Actions
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            listOf("Deposit", "Withdraw", "Swap").forEach { action ->
                                MotionSurface(
                                    modifier = Modifier.weight(1f).height(60.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        Text(action, style = MaterialTheme.typography.labelLarge, color = Color.White)
                                    }
                                }
                            }
                        }
                    }

                    // Asset List
                    items(3) { index ->
                        MotionSurface(
                            modifier = Modifier.fillMaxWidth().height(80.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp).fillMaxSize(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(if (index == 0) "Bitcoin" else if (index == 1) "Ethereum" else "Solana", color = Color.White)
                                    Text("Crypto", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(alpha = 0.4f))
                                }
                                Text(if (index == 0) "+2.4%" else "-1.2%", color = if (index == 0) Color.Green else Color.Red)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ThemeMenuItem(name: String, theme: com.compose.motion.theme.MotionTheme, onClick: (com.compose.motion.theme.MotionTheme) -> Unit) {
    DropdownMenuItem(text = { Text(name) }, onClick = { onClick(theme) })
}
