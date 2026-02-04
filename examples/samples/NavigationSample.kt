package com.compose.motion.examples

import androidx.compose.animation.*
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
 * A robust, premium navigation sample with Shared Element transitions.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationSample() {
    val navController = rememberNavController()

    ProvideMotionTheme {
        MotionNavHost(
            navController = navController,
            startDestination = "list",
            transition = MotionTransition.ContainerTransform,
            modifier = Modifier.fillMaxSize()
        ) {
            // Use motionComposable to enable shared element support
            motionComposable("list") {
                ListScreen(onItemClick = { id -> navController.navigate("detail/$id") })
            }

            motionComposable("detail/{itemId}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("itemId") ?: ""
                DetailScreen(id = id, onBack = { navController.popBackStack() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListScreen(onItemClick: (String) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Explore") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(List(10) { it.toString() }) { id ->
                MotionCard(
                    onClick = { onItemClick(id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .sharedElement("item_container_$id") // Shared container key
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Awesome Content #$id", style = MaterialTheme.typography.titleLarge)
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
                title = { Text("Details") },
                navigationIcon = { IconButton(onClick = onBack) { Text("←") } }
            )
        }
    ) { padding ->
        // We use sharedElement on the root layout of the detail screen
        // to morph the container from the card into the full screen.
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .sharedElement("item_container_$id")
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            )
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    "Shared Item Detail #$id",
                    style = MaterialTheme.typography.displaySmall
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "This transition is handled by MotionNavHost using the ContainerTransform preset. " +
                    "Notice how the content morphs from a card into the full page content.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
