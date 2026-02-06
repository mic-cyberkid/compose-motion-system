package com.compose.motion.examples.showcase

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.compose.motion.components.MotionSurface
import com.compose.motion.navigation.MotionNavHost
import com.compose.motion.navigation.motionComposable
import com.compose.motion.navigation.sharedElement

/**
 * Demonstrates advanced navigation transitions using [MotionNavHost].
 * It showcases a list-to-detail flow with Shared Element Transitions.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationShowcase() {
    val navController = rememberNavController()

    MotionNavHost(
        navController = navController,
        startDestination = "list"
    ) {
        motionComposable("list") {
            ListScreen(onItemClick = { navController.navigate("detail/$it") })
        }
        motionComposable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            DetailScreen(id = id, onBack = { navController.popBackStack() })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListScreen(onItemClick: (String) -> Unit) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Items") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(listOf("1", "2", "3", "4", "5")) { id ->
                MotionSurface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .sharedElement(key = "item_$id")
                        .clickable { onItemClick(id) }
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text("Item $id", style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScreen(id: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail $id") },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("Back") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            MotionSurface(
                modifier = Modifier
                    .size(200.dp)
                    .sharedElement(key = "item_$id")
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(id, style = MaterialTheme.typography.displayLarge)
                }
            }

            Text(
                "Shared Element Transition",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                "This screen demonstrates how a surface can seamlessly transition " +
                "from a list item to a detail view using MotionNavHost's shared element support.",
                modifier = Modifier.padding(32.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
