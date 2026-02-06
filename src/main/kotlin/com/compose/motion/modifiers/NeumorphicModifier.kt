package com.compose.motion.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Applies a neumorphic effect with dual shadows (light and dark).
 *
 * @param shape The shape of the surface.
 * @param lightShadowColor Color of the light shadow (top-left).
 * @param darkShadowColor Color of the dark shadow (bottom-right).
 * @param shadowElevation Elevation/blur of the shadows.
 * @param lightSourceOffset Offset for the light shadow relative to the dark shadow.
 */
fun Modifier.neumorphic(
    shape: Shape,
    lightShadowColor: Color,
    darkShadowColor: Color,
    shadowElevation: Dp = 8.dp,
    lightSourceOffset: Dp = (-4).dp
): Modifier = drawBehind {
    val shadowRadius = shadowElevation.toPx()
    val offset = lightSourceOffset.toPx()

    drawIntoCanvas { canvas ->
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()

        // Dark shadow (bottom-right)
        frameworkPaint.color = darkShadowColor.toArgb()
        frameworkPaint.setShadowLayer(
            shadowRadius,
            -offset,
            -offset,
            darkShadowColor.toArgb()
        )
        val outline = shape.createOutline(size, layoutDirection, this)
        canvas.drawOutline(outline, paint)

        // Light shadow (top-left)
        frameworkPaint.color = lightShadowColor.toArgb()
        frameworkPaint.setShadowLayer(
            shadowRadius,
            offset,
            offset,
            lightShadowColor.toArgb()
        )
        canvas.drawOutline(outline, paint)
    }
}

/**
 * Applies an inset neumorphic shadow effect.
 */
fun Modifier.neumorphicInset(
    shape: Shape,
    lightShadowColor: Color,
    darkShadowColor: Color,
    shadowElevation: Dp = 4.dp,
    lightSourceOffset: Dp = (-2).dp
): Modifier = drawBehind {
    val shadowRadius = shadowElevation.toPx()
    val offset = lightSourceOffset.toPx()

    drawIntoCanvas { canvas ->
        val outline = shape.createOutline(size, layoutDirection, this)
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.style = android.graphics.Paint.Style.STROKE
        frameworkPaint.strokeWidth = shadowRadius

        canvas.save()
        when (outline) {
            is Outline.Rectangle -> canvas.clipRect(outline.rect)
            is Outline.Rounded -> canvas.clipPath(Path().apply { addRoundRect(outline.roundRect) })
            is Outline.Generic -> canvas.clipPath(outline.path)
        }

        // Dark inset (top-left)
        frameworkPaint.color = Color.Transparent.toArgb()
        frameworkPaint.setShadowLayer(shadowRadius, -offset, -offset, darkShadowColor.toArgb())
        canvas.drawOutline(outline, paint)

        // Light inset (bottom-right)
        frameworkPaint.color = Color.Transparent.toArgb()
        frameworkPaint.setShadowLayer(shadowRadius, offset, offset, lightShadowColor.toArgb())
        canvas.drawOutline(outline, paint)

        canvas.restore()
    }
}
