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

## 🎨 Advanced Animations & Effects (New!)

Compose Motion now includes 20+ advanced effects categorized by priority:

### High Priority & Widely Requested
- **particleTrail**: Follow the user's drag gesture with subtle glowing particles.
- **dragMagneticFeedback**: Provide tactile feedback on drag handles by attracting toward the finger.
- **scrollHeaderEffects**: Scale, blur, and scrim backgrounds dynamically as the user scrolls.
- **StaggeredContentReveal**: Professional placeholder-to-content transition with crossfade.
- **MeshGradient**: A subtle, breathing animated background that feels fluid and modern.

### Polish & Delight
- **FlipCard**: A high-quality 3D Y-axis flip component with perspective.
- **spatialHover**: Add desktop-class hover effects (scale + glow) for tablets and foldables.
- **AnimatedCounter**: Fluid sliding transitions for numeric values.
- **PillMorphRow**: A tab indicator that morphs shape and position between selections.
- **ExpandableText**: Smooth inline expansion with gradient fade.
- **ProfileRipple**: A breathing radial pulse around circular elements like avatars.

### Creative & Experimental
- **MagicExplosion**: Physics-based particle burst for "likes" or celebrations.
- **liquidDistortion**: AGSL-powered liquid ripple distortion (API 33+).
- **MotionText (Kinetic)**: Characters squash and bounce into place on entrance.
- **overscrollGlow**: Chromatic aberration glow effect triggered at layout boundaries.

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
            .particleTrail()     // Follow drag with particles
    ) {
        Text("Press Me", color = Color.White, modifier = Alignment.Center)
    }
}
```
