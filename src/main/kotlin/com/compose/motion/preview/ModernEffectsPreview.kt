package com.compose.motion.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.motion.components.*
import com.compose.motion.content.StaggeredContentReveal
import com.compose.motion.content.StaggeredEntrance
import com.compose.motion.effects.*
import com.compose.motion.modifiers.*
import com.compose.motion.theme.ProvideMotionTheme

@Preview(showBackground = true)
@Composable
fun ModernEffectsPreview() {
    var trigger by remember { mutableStateOf(0) }
    var selectedTab by remember { mutableStateOf(0) }
    var isLoading by remember { mutableStateOf(true) }

    ProvideMotionTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text("Flip Card", style = MaterialTheme.typography.headlineSmall)
                var isFlipped by remember { mutableStateOf(false) }
                FlipCard(
                    isFlipped = isFlipped,
                    modifier = Modifier.size(width = 200.dp, height = 120.dp),
                    front = {
                        Box(Modifier.fillMaxSize().background(Color.Blue).motionClickable { isFlipped = true }, contentAlignment = Alignment.Center) {
                            Text("FRONT", color = Color.White)
                        }
                    },
                    back = {
                        Box(Modifier.fillMaxSize().background(Color.Red).motionClickable { isFlipped = false }, contentAlignment = Alignment.Center) {
                            Text("BACK", color = Color.White)
                        }
                    }
                )
            }

            item {
                Text("Pill Morph Row", style = MaterialTheme.typography.headlineSmall)
                PillMorphRow(
                    options = listOf("Home", "Search", "Profile"),
                    selectedIndex = selectedTab,
                    onOptionSelected = { selectedTab = it },
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                )
            }

            item {
                Text("Animated Counter", style = MaterialTheme.typography.headlineSmall)
                var count by remember { mutableStateOf(123) }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { count-- }) { Text("-") }
                    AnimatedCounter(count = count, prefix = "$", style = MaterialTheme.typography.headlineMedium)
                    IconButton(onClick = { count++ }) { Text("+") }
                }
            }

            item {
                Text("Staggered Content Reveal", style = MaterialTheme.typography.headlineSmall)
                Button(onClick = { isLoading = !isLoading }) { Text("Toggle Loading") }
                StaggeredContentReveal(isLoading = isLoading, modifier = Modifier.fillMaxWidth().height(60.dp)) {
                    Box(Modifier.fillMaxSize().background(Color.Green), contentAlignment = Alignment.Center) {
                        Text("Real Content Loaded")
                    }
                }
            }

            item {
                Text("Magic Explosion", style = MaterialTheme.typography.headlineSmall)
                Button(onClick = { trigger++ }) { Text("BOOM") }
                Box(Modifier.fillMaxWidth().height(100.dp)) {
                    MagicExplosion(trigger = trigger)
                }
            }

            item {
                Text("Kinetic Text", style = MaterialTheme.typography.headlineSmall)
                MotionText("BOUNCY REVEAL", bounce = true, style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}
