package com.compose.motion.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.compose.motion.navigation.MotionNavHost
import com.compose.motion.navigation.MotionTransition
import com.compose.motion.navigation.motionComposable
import com.compose.motion.navigation.sharedElement
import com.compose.motion.theme.ProvideMotionTheme
import com.compose.motion.components.MotionCard

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NavigationSharedElementPreview() {
    val navController = rememberNavController()

    ProvideMotionTheme {
        MotionNavHost(
            navController = navController,
            startDestination = "list",
            transition = MotionTransition.ContainerTransform,
            modifier = Modifier.fillMaxSize()
        ) {
            motionComposable("list") {
                Scaffold(
                    topBar = { TopAppBar(title = { Text("Items") }) }
                ) { padding ->
                    LazyColumn(modifier = Modifier.padding(padding)) {
                        items(List(10) { it }) { id ->
                            MotionCard(
                                onClick = { navController.navigate("detail/$id") },
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .sharedElement("item-$id")
                            ) {
                                Text("Item $id", modifier = Modifier.padding(16.dp))
                            }
                        }
                    }
                }
            }

            motionComposable("detail/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Detail $id") },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Text("<") // Simple back button for preview
                                }
                            }
                        )
                    }
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize()
                            .sharedElement("item-$id")
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                        )
                        Text(
                            "Detail view for item $id",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
            }
        }
    }
}
