package com.compose.motion.theme

import androidx.compose.ui.graphics.Brush
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
    val backgroundColor: Color get() = Color(0xFF0A0F14)
    val backgroundBrush: Brush? get() = null

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

        /** Aura Finance glassmorphism with blue accents. */
        fun auraGlass(): MotionStyle = AuraGlassStyle()

        /** Aura Finance glassmorphism with emerald accents. */
        fun auraEmerald(): MotionStyle = AuraEmeraldStyle()

        /** Aura Finance dark neumorphism. */
        fun auraNeumorphic(): MotionStyle = AuraNeumorphicStyle()

        /** Tactile indigo neumorphism. */
        fun tactileNeumorphic(): MotionStyle = TactileNeumorphicStyle()

        /** visionOS-inspired liquid glass with chromatic refraction. */
        fun visionOS(): MotionStyle = VisionOSStyle()

        /** Chromatic liquid glass with high saturation. */
        fun chromaticLiquid(): MotionStyle = ChromaticLiquidStyle()
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

class AuraGlassStyle : MotionStyle {
    override val blurRadius: Dp = 12.dp
    override val borderAlpha: Float = 0.1f
    override val shadowElevation: Dp = 8.dp
    override val cornerRadius: Dp = 16.dp
    override val primaryColorOverride: Color = Color(0xFF137FEC)
    override val backgroundColor: Color = Color(0xFF0A0F14)
    override val backgroundBrush: Brush = Brush.radialGradient(
        colors = listOf(Color(0xFF1E293B), Color(0xFF0A0F14)),
        center = androidx.compose.ui.geometry.Offset(500f, -200f),
        radius = 1000f
    )
    override val effectIntensity: Float = 1.0f
}

class AuraEmeraldStyle : MotionStyle {
    override val blurRadius: Dp = 24.dp
    override val borderAlpha: Float = 0.12f
    override val shadowElevation: Dp = 12.dp
    override val cornerRadius: Dp = 20.dp
    override val primaryColorOverride: Color = Color(0xFF10B981)
    override val backgroundColor: Color = Color(0xFF0A1219)
    override val backgroundBrush: Brush = Brush.radialGradient(
        colors = listOf(Color(0xFF064E3B), Color(0xFF0A1219), Color(0xFF020617)),
        center = androidx.compose.ui.geometry.Offset(0f, 0f),
        radius = 800f
    )
    override val effectIntensity: Float = 1.1f
}

class AuraNeumorphicStyle : MotionStyle {
    override val blurRadius: Dp = 0.dp
    override val borderAlpha: Float = 0f
    override val shadowElevation: Dp = 10.dp
    override val cornerRadius: Dp = 24.dp
    override val primaryColorOverride: Color = Color(0xFF0D46F2)
    override val backgroundColor: Color = Color(0xFF1A1F2E)
    override val effectIntensity: Float = 1.2f
}

class TactileNeumorphicStyle : MotionStyle {
    override val blurRadius: Dp = 0.dp
    override val borderAlpha: Float = 0f
    override val shadowElevation: Dp = 8.dp
    override val cornerRadius: Dp = 16.dp
    override val primaryColorOverride: Color = Color(0xFF0D46F2)
    override val backgroundColor: Color = Color(0xFF1B1F2B)
    override val effectIntensity: Float = 1.3f
}

class VisionOSStyle : MotionStyle {
    override val blurRadius: Dp = 40.dp
    override val borderAlpha: Float = 0.15f
    override val shadowElevation: Dp = 16.dp
    override val cornerRadius: Dp = 32.dp
    override val primaryColorOverride: Color = Color(0xFF2525F4)
    override val backgroundColor: Color = Color(0xFF05050A)
    override val backgroundBrush: Brush = Brush.linearGradient(
        listOf(Color(0xFF101022), Color(0xFF1A1A3A), Color(0xFF05050A))
    )
    override val effectIntensity: Float = 1.4f
}

class ChromaticLiquidStyle : MotionStyle {
    override val blurRadius: Dp = 24.dp
    override val borderAlpha: Float = 0.1f
    override val shadowElevation: Dp = 12.dp
    override val cornerRadius: Dp = 24.dp
    override val primaryColorOverride: Color = Color(0xFF6366F1)
    override val backgroundColor: Color = Color(0xFF0A0A14)
    override val backgroundBrush: Brush = Brush.linearGradient(
        listOf(Color(0xFF0A0A14), Color(0xFF1A1A3A))
    )
    override val effectIntensity: Float = 1.5f
}
