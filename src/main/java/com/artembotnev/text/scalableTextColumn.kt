package com.artembotnev.text

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
fun ScalableText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = TextStyle(fontSize = 14.sp),
    backgroundColor: Color = Color.White,
    minFactor: Float = 0.5f,
    maxFactor: Float = 3.0f,
) {
    var scale by remember { mutableStateOf(1f) }
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .background(backgroundColor)
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        awaitFirstDown()
                        do {
                            val event = awaitPointerEvent()
                            scale = (scale * event.calculateZoom()).coerceIn(minFactor, maxFactor)
                        } while (event.changes.any { it.pressed })
                    }
                }
            }
    ) {
        Text(
            text = text,
            style = style.copy(
                fontSize = style.fontSize.toScale(scale),
            ),
            modifier = modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

private fun TextUnit.toScale(scale: Float) = this * scale
