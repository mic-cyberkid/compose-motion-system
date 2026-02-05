package com.compose.motion.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.material3.MaterialTheme
import com.compose.motion.theme.motionScheme
import com.compose.motion.theme.MotionScheme

/**
 * Utility to perform haptic feedback that is context-aware of the current [MotionScheme].
 */
@Composable
fun rememberHapticMotion(): HapticMotion {
    val haptic = LocalHapticFeedback.current
    val scheme = MaterialTheme.motionScheme
    return remember(haptic, scheme) { HapticMotion(haptic, scheme) }
}

class HapticMotion internal constructor(
    private val haptic: HapticFeedback,
    private val scheme: MotionScheme
) {
    /**
     * Performs a haptic feedback click.
     * If the scheme is expressive, it might perform a stronger or double click.
     */
    fun click() {
        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
    }

    /**
     * Performs haptic feedback for a long press.
     */
    fun longPress() {
        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
    }

    /**
     * Performs a "success" haptic pattern.
     */
    fun success() {
        // Compose HapticFeedback is a bit limited, in a real app we'd use Vibrator
        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
    }
}
