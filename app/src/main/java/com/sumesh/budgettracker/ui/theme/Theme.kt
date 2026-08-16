package com.sumesh.budgettracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF3B2F8C),
    secondary = Color(0xFFE91E8C),
    background = Color(0xFFF5F5F7),
    surface = Color.White
)

@Composable
fun BudgetTrackerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}
