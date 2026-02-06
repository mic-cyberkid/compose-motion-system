package com.compose.motion.examples.showcase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.motion.modifiers.*

/**
 * Showcases the various visual modifiers available in the library.
 * This includes Shaders (Liquid, Chromatic), Glassmorphism, and Neumorphism.
 */
@Composable
fun EffectsShowcase() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Visual Effects Gallery", style = MaterialTheme.typography.headlineMedium)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                EffectItem("Glassmorphic", Modifier.glassmorphic(shape = RoundedCornerShape(16.dp), blurRadius = 20.dp))
            }
            item {
                EffectItem("Neumorphic", Modifier.neumorphic(
                    shape = RoundedCornerShape(16.dp),
                    lightShadowColor = Color.White.copy(alpha = 0.5f),
                    darkShadowColor = Color.Black.copy(alpha = 0.2f)
                ))
            }
            item {
                EffectItem("Liquid Distortion", Modifier.liquidDistortion(intensity = 1.5f))
            }
            item {
                EffectItem("Chromatic Aberration", Modifier.chromaticAberration(intensity = 1.0f))
            }
            item {
                EffectItem("Chromatic Border", Modifier.chromaticBorder(shape = RoundedCornerShape(16.dp)))
            }
            item {
                EffectItem("Neumorphic Inset", Modifier.neumorphicInset(
                    shape = RoundedCornerShape(16.dp),
                    lightShadowColor = Color.White.copy(alpha = 0.5f),
                    darkShadowColor = Color.Black.copy(alpha = 0.2f)
                ))
            }
        }
    }
}

@Composable
private fun EffectItem(label: String, modifier: Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .then(modifier)
                .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(label, style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(8.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}
