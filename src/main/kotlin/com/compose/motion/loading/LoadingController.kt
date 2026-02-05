package com.compose.motion.loading

import androidx.compose.runtime.*
import com.compose.motion.ExperimentalMotionApi

/**
 * Represents the current state of a loading operation.
 */
@ExperimentalMotionApi
enum class LoadingState {
    Idle,
    Loading,
    Loaded,
    Error
}

/**
 * A controller to manage loading state and progress.
 */
@ExperimentalMotionApi
@Stable
class LoadingController(
    initialState: LoadingState = LoadingState.Idle,
    initialProgress: Float = 0f
) {
    var state by mutableStateOf(initialState)
    var progress by mutableStateOf(initialProgress)
    var message by mutableStateOf<String?>(null)

    fun startLoading(msg: String? = null) {
        state = LoadingState.Loading
        progress = 0f
        message = msg
    }

    fun updateProgress(newProgress: Float) {
        progress = newProgress.coerceIn(0f, 1f)
    }

    fun complete(msg: String? = null) {
        state = LoadingState.Loaded
        progress = 1f
        message = msg
    }

    fun error(msg: String? = null) {
        state = LoadingState.Error
        message = msg
    }

    fun reset() {
        state = LoadingState.Idle
        progress = 0f
        message = null
    }
}

/**
 * Remembers a [LoadingController] across recompositions.
 */
@ExperimentalMotionApi
@Composable
fun rememberLoadingController(
    initialState: LoadingState = LoadingState.Idle,
    initialProgress: Float = 0f
): LoadingController {
    return remember { LoadingController(initialState, initialProgress) }
}

/**
 * Scope for providing progress information to loading indicators.
 */
@ExperimentalMotionApi
interface MotionLoadingScope {
    val progress: Float
    val state: LoadingState
    val message: String?
}

@ExperimentalMotionApi
internal class MotionLoadingScopeImpl(
    override val progress: Float,
    override val state: LoadingState,
    override val message: String?
) : MotionLoadingScope
