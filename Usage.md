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

Compose Motion is built to be a first-class extension of Material 3. To get started, wrap your application in `ProvideMotionTheme`.

```kotlin
import com.compose.motion.theme.ProvideMotionTheme
import com.compose.motion.theme.MotionScheme
import com.compose.motion.theme.MotionStyle

@Composable
fun App() {
    MaterialTheme {
        // Provide the motion system and visual style to your app
        ProvideMotionTheme(
            scheme = MotionScheme.expressive(),
            style = MotionStyle.appleGlass() // Modern UI Preset
        ) {
            MainScreen()
        }
    }
}
```

### Accessing the Motion System
You can access the current motion scheme and visual style anywhere in your composables via `MaterialTheme`.

```kotlin
val scheme = MaterialTheme.motionScheme
val style = MaterialTheme.motionStyle
```

## 3. Modern UI Styles & Presets

The `MotionStyle` framework allows you to swap entire visual aesthetics instantly.

| Preset | Aesthetic | Key Features |
| :--- | :--- | :--- |
| **Apple Glass** | iOS-style Glassmorphism | High blur, subtle borders, specular highlights. |
| **Cyber Glitch** | Sci-fi / Cyberpunk | Neon borders, digital distortion, low corner radius. |
| **Neumorphic** | Soft UI | Double shadows, extruded surfaces, pill shapes. |
| **Minimal Fluid** | Clean & Transparent | Zero shadows, high transparency, ultra-rounded. |

### Using MotionSurface
`MotionSurface` is a themed container that automatically applies the correct background, border, and effects based on the current `MotionStyle`.

```kotlin
MotionSurface(modifier = Modifier.size(200.dp)) {
    // Automatically looks like Glass, Neumorphic, or Glitch based on theme
    Text("Adaptive Content")
}
```

## 4. Motion Tokens (Durations, Springs, Easings)

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

## 5. Motion Schemes (Standard vs Expressive)

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

## 6. Navigation Transitions & Shared Elements

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

## 7. Micro-interaction Modifiers

### Selection & Feedback
- `Modifier.scaleOnPress()`: Gently scales the element down when touched.
- `Modifier.tiltOnTouch()`: 3D parallax tilt based on touch position.
- `Modifier.magneticPull()`: Attracts the element toward the touch point.
- `Modifier.elasticDrag()`: Adds a "sticky" feel to dragged elements.

### Lighting & Glass
- `Modifier.glassmorphic()`: Applies background blur and specular edge highlights.
- `Modifier.spotlight()`: A light source that follows the user's touch.

```kotlin
Box(
    modifier = Modifier
        .size(100.dp)
        .glassmorphic()
        .spotlight() // Light follows finger
        .scaleOnPress()
)
```

## 8. Visual Effects & Shaders

- `Modifier.glitch()`: Cyber-style digital distortion.
- `Modifier.chromaticAberration()`: Realistic color fringing on touch or state change.
- `Modifier.liquidDistortion()`: AGSL-powered fluid warping (API 33+).
- `MagicExplosion(visible)`: A cinematic particle burst for celebrations.

## 9. Animated Components & Layouts

### Expressive Layouts
- `MotionCarousel`: A horizontal pager with 3D and scale transformations.
- `Modifier.revealOnScroll()`: Automatically animates elements as they enter the viewport.

```kotlin
MotionCarousel(itemCount = 10) { page ->
    Card { /* content */ }
}

// Staggered viewport entrance
Modifier.revealOnScroll(direction = RevealDirection.Up)
```

### Specialized Components
- `MotionSurface`: Adaptive themed container.
- `LiquidProgressIndicator`: Organic progress wave.
- `MotionText`: Animated character entrance.
- `AnimatedCounter`: Smoothly rolling numbers.

```kotlin
LiquidProgressIndicator(
    progress = downloadProgress,
    color = MaterialTheme.colorScheme.primary
)
```

## 10. Launch & Splash Screen Animations

Replace the static system splash screen with pure Compose animations using `MotionLaunch` or `SplashScreen`.

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

## 11. Advanced Usage & Best Practices

### Performance Optimization
- Use `Modifier.graphicsLayer` for any custom animations to ensure they run on the Render Thread.
- Prefer `MotionScheme` defaults over custom `AnimationSpec` to benefit from library-wide tuning.

### Best Practices
- **Respect User Accessibility**: The library automatically scales durations if the system "Remove animations" setting is enabled.
- **Don't Overdo It**: Use **Expressive** for branding/onboarding and **Standard** for data-heavy utility screens.
- **Modifier Ordering**: Always place `sharedElement` before layout modifiers like `padding` to ensure the bounds are captured correctly.

## 12. Full Example Gallery

Check out the `examples/` directory for ready-to-use implementations:
- `NavigationSample.kt`: Full shared-element flow.
- `InteractionSample.kt`: Mixing tilt, magnetic, and scale effects.
- `ModernUIStylesSample.kt`: Showcase of Glass, Glitch, and Neumorphic themes.
- `LaunchAnimationSample.kt`: Gallery of all splash styles.
- `LoadingGallery.kt`: Shimmer, skeleton, and liquid progress examples.
