package com.sumesh.budgettracker.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LineChart(values: List<Float>, labels: List<String>, modifier: Modifier = Modifier) {
    Column(modifier) {
        Canvas(
            Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            if (values.isEmpty()) return@Canvas
            val max = values.max().takeIf { it > 0 } ?: 1f
            val min = values.min()
            val range = (max - min).takeIf { it > 0 } ?: 1f
            val stepX = size.width / (values.size - 1).coerceAtLeast(1)
            val points = values.mapIndexed { i, v ->
                Offset(
                    x = i * stepX,
                    y = size.height - ((v - min) / range) * size.height
                )
            }
            for (i in 0 until points.size - 1) {
                drawLine(
                    color = Color(0xFF323232),
                    start = points[i],
                    end = points[i + 1],
                    strokeWidth = 6f
                )
            }
            points.forEach { p ->
                drawCircle(color = Color(0xFF323232), radius = 8f, center = p)
            }
        }
        Row(Modifier.fillMaxWidth()) {
            labels.forEach { label ->
                Text(label, fontSize = 12.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            }
        }
    }
}
