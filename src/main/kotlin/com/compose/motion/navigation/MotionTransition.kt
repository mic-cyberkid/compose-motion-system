package com.compose.motion.navigation

/**
 * Defines standard navigation transitions supported by [MotionNavHost].
 */
sealed class MotionTransition {
    /** Simple cross-fade transition. */
    object Fade : MotionTransition()

    /** Simple slide transition. */
    object Slide : MotionTransition()

    /**
     * Shared axis X transition: used for sibling-to-sibling transitions (e.g., pager, tabs).
     * Elements slide and fade horizontally.
     */
    object SharedAxisX : MotionTransition()

    /**
     * Shared axis Y transition: used for vertical transitions (e.g., top-level destinations).
     * Elements slide and fade vertically.
     */
    object SharedAxisY : MotionTransition()

    /**
     * Container transform transition: used for hero-style transitions where a
     * container (like a card) expands into a full screen.
     *
     * For best results, use this in conjunction with [Modifier.sharedElement].
     */
    object ContainerTransform : MotionTransition()
}
