# Compose Motion Examples

This directory contains full, copy-paste ready sample code for **Compose Motion**.

## 🚀 Quick Start Samples

For users with zero knowledge of the library, we have prepared standalone samples that you can copy and paste directly into your IDE.

### 1. [Basic Interactions](./samples/BasicInteractions.kt)
Learn how to set up the `MotionTheme` and use interaction modifiers like `scaleOnPress` and `elevateOnPress`.

### 2. [Navigation & Shared Elements](./samples/NavigationSample.kt)
See how to implement professional screen transitions and hero-element transforms using `MotionNavHost`.

### 3. [Futuristic Launch Animation](./samples/LaunchAnimationSample.kt)
Experience the `LiquidGlassMorph` and other futuristic launch presets in a complete loading sequence.

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
    ) {
        Text("Press Me", color = Color.White, modifier = Modifier.align(Alignment.Center))
    }
}
```

### Scroll-Aware Animations

Use `fadeOnScroll` to create dynamic headers or parallax effects.

```kotlin
@Composable
fun ScrollAwareHeader(scrollState: ScrollState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .fadeOnScroll {
                // Calculate progress based on scroll offset
                (scrollState.value / 500f).coerceIn(0f, 1f)
            }
    ) {
        Text("I fade as you scroll up")
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
