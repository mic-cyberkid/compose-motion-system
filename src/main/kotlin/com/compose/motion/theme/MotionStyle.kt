package com.compose.motion.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Defines the visual style and surface treatment for the motion system.
 * This allows for global presets like Glassmorphism, Neumorphism, etc.
 */
interface MotionStyle {
    val blurRadius: Dp
    val borderAlpha: Float
    val shadowElevation: Dp
    val cornerRadius: Dp
    val primaryColorOverride: Color?
    val effectIntensity: Float

    companion object {
        /** Standard Material 3 styling. */
        fun standard(): MotionStyle = StandardMotionStyle()

        /** Apple-inspired glassmorphism with high blur and subtle borders. */
        fun appleGlass(): MotionStyle = AppleGlassStyle()

        /** Cyber-punk style with glitch tendencies and high-contrast effects. */
        fun cyberGlitch(): MotionStyle = CyberGlitchStyle()

        /** Ultra-minimalist, fluid style with zero shadows and high transparency. */
        fun minimalFluid(): MotionStyle = MinimalFluidStyle()

        /** Neumorphic style with soft double-shadows and pill shapes. */
        fun neumorphic(): MotionStyle = NeumorphicStyle()
    }
}

class StandardMotionStyle : MotionStyle {
    override val blurRadius: Dp = 0.dp
    override val borderAlpha: Float = 0f
    override val shadowElevation: Dp = 2.dp
    override val cornerRadius: Dp = 12.dp
    override val primaryColorOverride: Color? = null
    override val effectIntensity: Float = 1.0f
}

class AppleGlassStyle : MotionStyle {
    override val blurRadius: Dp = 20.dp
    override val borderAlpha: Float = 0.15f
    override val shadowElevation: Dp = 4.dp
    override val cornerRadius: Dp = 20.dp
    override val primaryColorOverride: Color? = null
    override val effectIntensity: Float = 0.8f
}

class CyberGlitchStyle : MotionStyle {
    override val blurRadius: Dp = 0.dp
    override val borderAlpha: Float = 0.5f
    override val shadowElevation: Dp = 0.dp
    override val cornerRadius: Dp = 2.dp
    override val primaryColorOverride: Color = Color(0xFF00FF41) // Matrix Green
    override val effectIntensity: Float = 1.5f
}

class MinimalFluidStyle : MotionStyle {
    override val blurRadius: Dp = 0.dp
    override val borderAlpha: Float = 0.05f
    override val shadowElevation: Dp = 0.dp
    override val cornerRadius: Dp = 32.dp
    override val primaryColorOverride: Color? = null
    override val effectIntensity: Float = 0.5f
}

class NeumorphicStyle : MotionStyle {
    override val blurRadius: Dp = 0.dp
    override val borderAlpha: Float = 0f
    override val shadowElevation: Dp = 6.dp
    override val cornerRadius: Dp = 24.dp
    override val primaryColorOverride: Color? = null
    override val effectIntensity: Float = 1.2f
}
