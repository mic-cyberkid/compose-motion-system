package com.compose.motion.modifiers

import android.graphics.RuntimeShader
import android.os.Build
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer

/**
 * A modifier that applies a liquid distortion shader effect (API 33+).
 */
fun Modifier.liquidDistortion(
    intensity: Float = 1.0f
): Modifier = composed {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val shader = remember {
            RuntimeShader(LIQUID_SHADER)
        }
        shader.setFloatUniform("intensity", intensity)

        this.graphicsLayer {
            renderEffect = android.graphics.RenderEffect.createRuntimeShaderEffect(
                shader, "composable"
            ).asComposeRenderEffect()
        }
    } else {
        this
    }
}

/**
 * Applies a chromatic aberration (color fringing) effect.
 */
fun Modifier.chromaticAberration(
    intensity: Float = 1.0f
): Modifier = composed {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val shader = remember {
            RuntimeShader(CHROMATIC_SHADER)
        }
        shader.setFloatUniform("offset", intensity * 5f)

        this.graphicsLayer {
            renderEffect = android.graphics.RenderEffect.createRuntimeShaderEffect(
                shader, "composable"
            ).asComposeRenderEffect()
        }
    } else {
        this
    }
}

private const val CHROMATIC_SHADER = """
    uniform shader composable;
    uniform float offset;

    half4 main(float2 fragCoord) {
        half4 r = composable.eval(fragCoord + float2(offset, 0.0));
        half4 g = composable.eval(fragCoord);
        half4 b = composable.eval(fragCoord - float2(offset, 0.0));
        return half4(r.r, g.g, b.b, g.a);
    }
"""

private const val LIQUID_SHADER = """
    uniform shader composable;
    uniform float intensity;

    half4 main(float2 fragCoord) {
        float2 uv = fragCoord;
        uv.x += sin(uv.y * 0.01 + intensity) * 10.0;
        return composable.eval(uv);
    }
"""
