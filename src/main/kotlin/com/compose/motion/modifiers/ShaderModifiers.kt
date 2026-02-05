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

private const val LIQUID_SHADER = """
    uniform shader composable;
    uniform float intensity;

    half4 main(float2 fragCoord) {
        float2 uv = fragCoord;
        uv.x += sin(uv.y * 0.01 + intensity) * 10.0;
        return composable.eval(uv);
    }
"""
