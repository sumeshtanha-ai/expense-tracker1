package com.sumesh.budgettracker.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun DonutChart(values: List<Float>, colors: List<Color>, modifier: Modifier = Modifier) {
    val total = values.sum().takeIf { it > 0 } ?: 1f
    Canvas(modifier = modifier.size(150.dp)) {
        var startAngle = -90f
        val strokeWidth = size.minDimension * 0.24f
        values.forEachIndexed { index, value ->
            val sweep = (value / total) * 360f
            drawArc(
                color = colors.getOrElse(index) { Color.Gray },
                startAngle = startAngle,
                sweepAngle = sweep,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Butt),
                size = Size(size.width - strokeWidth, size.height - strokeWidth),
                topLeft = Offset(strokeWidth / 2, strokeWidth / 2)
            )
            startAngle += sweep
        }
    }
}
