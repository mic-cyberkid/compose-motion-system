# Compose Motion – Usage Guide

## 1. Installation

Add the dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.compose.motion:compose-motion:1.0.0")
}
```

> **Note**: Compose Motion requires Compose 1.7.0+ and Material 3 1.3.1+.

## 2. Quick Start & Theme Setup

Compose Motion is built to be a first-class extension of Material 3. To get started, wrap your application in `ProvideMotionTheme`. By default, it uses the **Standard** motion scheme.

```kotlin
import com.compose.motion.theme.ProvideMotionTheme
import com.compose.motion.theme.MotionScheme

@Composable
fun App() {
    MaterialTheme {
        // Provide the motion system to your app
        ProvideMotionTheme(scheme = MotionScheme.standard()) {
            MainScreen()
        }
    }
}
```

### Accessing the Motion System
You can access the current motion scheme anywhere in your composables via `MaterialTheme.motionScheme`.

```kotlin
val scheme = MaterialTheme.motionScheme
val spatialSpec = scheme.defaultSpatialSpec<Float>()
```

## 3. Motion Tokens (Durations, Springs, Easings)

Never hardcode durations or easings. Use the semantic tokens provided by the library.

### Durations
Tokens follow the Material 3 naming convention:
- `MotionDurations.Short1` (50ms) ... `Short4` (200ms)
- `MotionDurations.Medium1` (250ms) ... `Medium4` (400ms)
- `MotionDurations.Long1` (450ms) ... `Long4` (600ms)

### Easings
- `MotionEasings.Standard`: For general movement.
- `MotionEasings.Emphasized`: For more expressive, fluid movement.
- `MotionEasings.Accelerate`: For exiting elements.
- `MotionEasings.Decelerate`: For entering elements.

## 4. Motion Schemes (Standard vs Expressive)

The `MotionScheme` interface determines how tokens resolve to actual animation specs.

| Scheme | Feel | Technical Detail |
| :--- | :--- | :--- |
| **Standard** | Productive, clean | Uses `tween` with Standard easing. |
| **Expressive** | Playful, fluid | Uses `spring` with low bounciness. |

### Switching Schemes
```kotlin
ProvideMotionTheme(scheme = MotionScheme.expressive()) {
    // All library modifiers and components now use spring-based motion
}
```

## 5. Navigation Transitions & Shared Elements

`MotionNavHost` provides a drop-in replacement for standard Navigation that automatically handles themed transitions and shared elements.

### Basic Navigation
```kotlin
import com.compose.motion.navigation.MotionNavHost
import com.compose.motion.navigation.MotionTransition

MotionNavHost(
    navController = navController,
    startDestination = "home",
    transition = MotionTransition.SharedAxisX // Themed horizontal slide
) {
    composable("home") { HomeScreen() }
    composable("details") { DetailsScreen() }
}
```

### Shared Element Transitions
To use shared elements, use `motionComposable` and the `Modifier.sharedElement` modifier.

```kotlin
// In the source screen
Modifier.sharedElement(key = "item_image_${item.id}")

// In the destination screen (motionComposable provides the scope)
motionComposable("details/{id}") {
    DetailsScreen(
        modifier = Modifier.sharedElement(key = "item_image_$id")
    )
}
```

## 6. Micro-interaction Modifiers

These intent-based modifiers provide instant feedback with zero configuration.

### Selection & Feedback
- `Modifier.scaleOnPress()`: Gently scales the element down when touched.
- `Modifier.elevateOnPress()`: Increases shadow elevation on press.
- `Modifier.tiltOnTouch()`: 3D parallax tilt based on touch position.
- `Modifier.magneticPull()`: Attracts the element toward the touch point.

```kotlin
Box(
    modifier = Modifier
        .size(100.dp)
        .tiltOnTouch() // Interactive 3D tilt
        .scaleOnPress() // Semantic feedback
        .motionClickable { /* action */ }
)
```

### Motion-Aware Modifiers
- `Modifier.waveRipple()`: An organic, non-circular ripple effect.
- `Modifier.spatialHover()`: Gentle floating effect for desktop/web.

## 7. Visual Effects

High-performance visual enhancements that adapt to your theme.

- `Modifier.shimmer()`: Standard skeleton loading effect.
- `Modifier.glitch()`: Cyber-style digital distortion.
- `Modifier.confetti()`: Particle burst effect (ideal for success states).
- `Modifier.parallax(factor)`: Relative movement for layered backgrounds.

```kotlin
Image(
    painter = painter,
    modifier = Modifier.glitch(active = isError)
)
```

## 8. Animated Components

Opinionated Material 3 components with built-in motion.

### Containers
- `MotionVisibility`: A theme-aware version of `AnimatedVisibility`.
- `MotionContent`: Transition between different states of content.
- `StaggeredEntrance(index)`: Applies a staggered entrance animation to its children based on the index.

### Specialized Components
- `MotionCard`: A card that supports expansion and shared element bounds.
- `LiquidProgressIndicator`: A fluid, organic alternative to standard progress bars.
- `MotionText`: Text that animates character-by-character or via "typewriter" effect.
- `AnimatedCounter`: Smoothly rolls numbers up or down.

```kotlin
LiquidProgressIndicator(
    progress = downloadProgress,
    color = MaterialTheme.colorScheme.primary
)
```

## 9. Launch Screen Animations

Replace the static system splash screen with pure Compose animations using `MotionLaunch`.

```kotlin
@OptIn(ExperimentalMotionApi::class)
MotionLaunch(
    visible = isLoading,
    style = SplashStyle.LiquidGlassMorph
) {
    Logo()
}
```

### Available Launch Styles
- `SplashStyle.FadeScale`: Clean Material entrance.
- `SplashStyle.HolographicPulse`: Tech-forward chromatic aberration.
- `SplashStyle.MatrixRain`: Digital "code" reveal.
- `SplashStyle.BentoReveal`: Modern grid-based entrance.
- `SplashStyle.OrganicMorph`: Fluid shape transformation.

## 10. Advanced Usage & Best Practices

### Performance Optimization
- Use `Modifier.graphicsLayer` for any custom animations to ensure they run on the Render Thread.
- Prefer `MotionScheme` defaults over custom `AnimationSpec` to benefit from library-wide tuning.

### Best Practices
- **Respect User Accessibility**: The library automatically scales durations if the system "Remove animations" setting is enabled.
- **Don't Overdo It**: Use **Expressive** for branding/onboarding and **Standard** for data-heavy utility screens.
- **Modifier Ordering**: Always place `sharedElement` before layout modifiers like `padding` to ensure the bounds are captured correctly.

## 11. Full Example Gallery

Check out the `examples/` directory for ready-to-use implementations:
- `NavigationSample.kt`: Full shared-element flow.
- `InteractionSample.kt`: Mixing tilt, magnetic, and scale effects.
- `LaunchAnimationSample.kt`: Gallery of all splash styles.
- `LoadingGallery.kt`: Shimmer, skeleton, and liquid progress examples.
