# Compose Motion Examples

This directory provides detailed examples of how to use **Compose Motion** in your Jetpack Compose applications.

## 1. Setting Up the Theme

The first step is to wrap your app in `ProvideMotionTheme`. You can choose between `Standard` (subtle) or `Expressive` (bouncy) personalities.

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

---

## 2. Using Interaction Modifiers

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

---

## 3. Advanced Navigation with Shared Elements

`MotionNavHost` simplifies shared element transitions and container transforms.

```kotlin
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    MotionNavHost(
        navController = navController,
        startDestination = "list",
        transition = MotionTransition.ContainerTransform
    ) {
        motionComposable("list") {
            ListScreen(onItemClick = { id -> navController.navigate("detail/$id") })
        }

        motionComposable("detail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            DetailScreen(
                itemId = itemId ?: "",
                modifier = Modifier.sharedElement(key = "item-$itemId")
            )
        }
    }
}
```

---

## 4. Animated Visibility and Content

Use `MotionVisibility` and `MotionContent` for theme-aligned transitions.

```kotlin
@Composable
fun VisibilityExample(visible: Boolean) {
    MotionVisibility(visible = visible) {
        Card {
            Text("I appear with a themed fade + scale animation!")
        }
    }
}

@Composable
fun ContentSwapExample(state: ScreenState) {
    MotionContent(targetState = state) { target ->
        when (target) {
            ScreenState.Loading -> LoadingView()
            ScreenState.Success -> DataView()
            ScreenState.Error -> ErrorView()
        }
    }
}
```

---

## 5. Pure-Compose Launch Screen

Replace your splash screen with a `MotionLaunch` sequence.

```kotlin
@Composable
fun LaunchSequence() {
    var showLaunch by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)
        showLaunch = false
    }

    Box {
        MainAppContent()

        MotionLaunch(
            visible = showLaunch,
            style = LaunchStyle.FadeScale
        ) {
            // Your Splash Screen Content (e.g. Logo)
            Image(painterResource(R.drawable.logo), contentDescription = null)
        }
    }
}
```

---

## 6. Scroll-Aware Animations

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
