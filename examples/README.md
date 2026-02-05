# Compose Motion Examples

This directory contains full, copy-paste ready sample code for **Compose Motion**.

## 🚀 Quick Start Samples

For users with zero knowledge of the library, we have prepared standalone samples that you can copy and paste directly into your IDE.

### 1. [Basic Interactions](./samples/BasicInteractions.kt)
Learn how to set up the `MotionTheme` and use interaction modifiers like `scaleOnPress` and `elevateOnPress`.

### 2. [Navigation & Shared Elements](./samples/NavigationSample.kt)
See how to implement professional screen transitions and hero-element transforms using `MotionNavHost`.

### 3. [Futuristic Launch Animation](./samples/LaunchAnimationSample.kt)
Experience `LiquidGlassMorph`, `AuroraGradient`, `BentoReveal`, and other futuristic launch presets in a complete loading sequence.

---

## 🎨 Advanced Animations & Effects

Compose Motion now includes 10+ new advanced effects categorized across three phases:

### Micro-interactions
- **tiltOnTouch**: Rotates elements in 3D based on touch position.
- **magneticPull**: Elements attract to the touch point for a tactile feel.
- **shimmer**: Customizable skeleton loading shimmer effect.
- **ConfettiBurst**: Celebratory particle burst triggered by state changes.

### Layout Animations
- **StaggeredEntrance**: sequential reveal for list items or layout blocks.
- **parallax**: Scroll-aware position shifting for depth effects.
- **LiquidProgressIndicator**: A wave-animated progress bar.

### Creative Effects
- **glitch**: Cyber-modern visual distortion effect.
- **waveRipple**: Custom touch-triggered circular wave expansion.
- **MotionText**: Character-by-character animated text reveal.
- **DepthZoom**: A cinematic navigation transition where screens scale and fade in depth.

---

## 📖 Guided Examples

### Setting Up the Theme

Wrap your app in `ProvideMotionTheme`. You can choose between `Standard` (subtle) or `Expressive` (bouncy).

```kotlin
@Composable
fun MyApp() {
    MaterialTheme {
        ProvideMotionTheme(theme = MotionThemeDefaults.Expressive) {
            MainScreen()
        }
    }
}
```

### Using Interaction Modifiers

Add micro-interactions to any composable using intent-based modifiers.

```kotlin
@Composable
fun InteractiveButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Blue, RoundedCornerShape(8.dp))
            .motionClickable(onClick = onClick)
            .scaleOnPress()      // Subtle shrink when pressed
            .elevateOnPress()    // Increase shadow when pressed
            .tiltOnTouch()       // 3D Tilt feedback
    ) {
        Text("Press Me", color = Color.White, modifier = Alignment.Center)
    }
}
```

---

## 🛠️ Project Setup for Samples

To run these samples, ensure you have the following dependencies in your `libs.versions.toml` or `build.gradle.kts`:

```toml
[libraries]
compose-motion = { group = "com.compose.motion", name = "compose-motion", version = "0.1.0" }
```
