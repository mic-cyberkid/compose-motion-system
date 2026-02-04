# Compose Motion

**Compose Motion** is a lightweight, production-grade motion system for Jetpack Compose, designed to provide Material-aligned, themeable, and token-driven animations for large-scale Android applications.

Built with enterprise, fintech, and media apps in mind, Compose Motion feels like a first-class extension of Material 3, prioritizing subtlety, performance, and API stability.

---

## 🎯 Project Vision

To build a motion library that isn't just a collection of widgets, but a cohesive design system integration. All motion is driven by design tokens that resolve to the current theme, ensuring a consistent user experience across the entire app.

## ✨ Core Capabilities

- **Theme-aware Motion Tokens**: Powered by a custom `MotionScheme` integration (Standard/Expressive).
- **Professional Micro-interactions**: Smooth scale, elevation, and clickable feedback.
- **Component-level Animations**: Pre-configured entrance/exit transitions for common UI patterns.
- **Navigation & Shared Elements**: Drop-in `NavHost` replacement with built-in support for Container Transforms and Shared Axis transitions.
- **Pure-Compose Launch Animations**: Beautiful app-launch sequences (including futuristic presets like Holographic, Liquid Glass, Aurora, and Kinetic Scan) without relying on the system SplashScreen API.
- **Predictive Back Ready**: Fully compatible with modern Android back-gesture previews.

---

## 🚀 Getting Started

### Installation

Add the following to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.compose.motion:compose-motion:0.1.0")
}
```

### Basic Setup

Wrap your application in `ProvideMotionTheme` to enable the motion system:

```kotlin
MaterialTheme {
    ProvideMotionTheme(theme = MotionThemeDefaults.Expressive) {
        // Your App Content
    }
}
```

---

## 🎨 Core Concepts

### Motion Schemes

Compose Motion supports two primary motion "personalities":

1.  **Standard**: Focused on productivity. Subtle easings, shorter durations, and no-bounce transitions. Ideal for functional or data-heavy interfaces.
2.  **Expressive**: Focused on personality. Playful, bouncy springs and fluid durations. Ideal for consumer-facing apps.

### Tokens

All motion is driven by tokens that resolve through `MaterialTheme.motionScheme`:

- **Spatial**: For resizing, moving, and shape changes (`fastSpatial`, `defaultSpatial`, `slowSpatial`).
- **Effects**: For alpha fades, color changes, and elevation (`fastEffects`, `defaultEffects`, `slowEffects`).

---

## 🧩 Usage Examples

### Interaction Modifiers

Enhance any component with themed micro-interactions:

```kotlin
Modifier
    .motionClickable { /* handle click */ }
    .scaleOnPress()
    .elevateOnPress()
```

### Navigation & Shared Elements

Replace your standard `NavHost` with `MotionNavHost` for instant high-quality transitions:

```kotlin
MotionNavHost(
    navController = navController,
    startDestination = "home",
    transition = MotionTransition.ContainerTransform
) {
    motionComposable("home") {
        HomeScreen(onNavigate = { navController.navigate("detail") })
    }
    motionComposable("detail") {
        DetailScreen(
            modifier = Modifier.sharedElement(key = "hero-image")
        )
    }
}
```

### Launch Animations

Create a seamless app entry sequence with futuristic styles:

```kotlin
MotionLaunch(
    visible = isLoading,
    style = LaunchStyle.LiquidGlassMorph // Futuristic liquid glass effect
) {
    AppLogo()
}
```

---

## 🏗️ Project Structure

The library is strictly organized for separation of concerns:

- `tokens/`: Design tokens backed by `MotionScheme`.
- `theme/`: Theme integration and `CompositionLocal` management.
- `effects/`: Reusable `AnimationSpec` and `Transition` factories.
- `modifiers/`: Public, intent-based modifiers (`scaleOnPress`, `fadeOnScroll`).
- `content/`: High-level animated containers (`MotionVisibility`, `MotionContainer`).
- `navigation/`: Drop-in navigation host with shared element support.
- `launch/`: Pure-Compose launch animations.
- `components/`: Opinionated Material component wrappers (Scaffold, Card).
- `internal/`: Private math and logic utilities.

---

## 🧠 Design Principles

1.  **Motion is a Design Token**: Durations, easings, and springs are never hardcoded in UI components.
2.  **Declarative First**: Motion is driven by state changes, not imperative triggers.
3.  **Material-First**: Default behavior aligns with Material 3 specs while allowing for expressive overrides.
4.  **Production Discipline**: Subtle by default, performant on all hardware, and highly accessible.

## ✅ Quality Gates

- **Full KDoc Coverage**: Every public API is fully documented.
- **Binary Compatibility**: Interfaces use `@JvmDefaultWithCompatibility`.
- **Zero Dependencies**: Beyond `androidx.compose.material3` and `androidx.navigation`.
- **Modern Standards**: Built with Compose 1.7+, Material 3.2+ alignment, and JVM 17.

---

## 📜 License

See the `LICENSE` file for details (MIT/Apache 2.0).
