package com.simple.composeapplication

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun CustomView(modifier: Modifier) {
    var progress by remember { mutableFloatStateOf(0.0f) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircleProgress(progress)
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = {
            progress = (progress + 0.1f).coerceIn(0f, 1f)
        }) {
            Text("+1")
        }
    }
}

/**
 * 自定义圆形进度条
 */
@Composable
fun CircleProgress(progress: Float, modifier: Modifier = Modifier) {
    Canvas(modifier.size(80.dp)) {
        val stroke = 16.dp.toPx()
        val arcSize = Size(size.minDimension, size.minDimension)
        val topLeft = Offset(
            (size.width - arcSize.width) / 2f,
            (size.height - arcSize.height) / 2f
        )
        drawArc(
            color = Color.LightGray,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            topLeft = topLeft,
            size = arcSize,
            style = Stroke(stroke)
        )
        drawArc(
            color = Color.Green,
            startAngle = -90f,
            sweepAngle = 360f * progress.coerceIn(0f, 1f),
            useCenter = false,
            topLeft = topLeft,
            size = arcSize,
            style = Stroke(stroke, cap = StrokeCap.Round)
        )
    }
}