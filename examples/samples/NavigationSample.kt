package com.compose.motion.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.compose.motion.components.MotionCard
import com.compose.motion.navigation.MotionNavHost
import com.compose.motion.navigation.MotionTransition
import com.compose.motion.navigation.motionComposable
import com.compose.motion.navigation.sharedElement
import com.compose.motion.theme.ProvideMotionTheme

/**
 * A complete, copy-paste ready example of MotionNavHost with Shared Element transitions.
 */
@Composable
fun NavigationSample() {
    val navController = rememberNavController()

    ProvideMotionTheme {
        MotionNavHost(
            navController = navController,
            startDestination = "list",
            transition = MotionTransition.ContainerTransform, // Default transition for the host
            modifier = Modifier.fillMaxSize()
        ) {
            // Use motionComposable to enable shared element support
            motionComposable("list") {
                ListScreen(
                    onItemClick = { id -> navController.navigate("detail/$id") }
                )
            }

            motionComposable("detail/{itemId}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("itemId") ?: ""
                DetailScreen(
                    id = id,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListScreen(onItemClick: (Int) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Shared Elements") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(List(10) { it }) { id ->
                // MotionCard has built-in scale-on-press
                MotionCard(
                    onClick = { onItemClick(id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        // Define this as a shared element!
                        .sharedElement("card-$id")
                ) {
                    Text(
                        "Item #$id",
                        modifier = Modifier.padding(24.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
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
                title = { Text("Detail View") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text("←")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                // Match the key from the list screen to enable the transition
                .sharedElement("card-$id")
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            )
            Text(
                "This screen transition used ContainerTransform.",
                modifier = Modifier.padding(24.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
