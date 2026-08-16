package com.sumesh.budgettracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class NavTab(val label: String, val icon: ImageVector)

val navTabs = listOf(
    NavTab("Home", Icons.Filled.Home),
    NavTab("History", Icons.Filled.CalendarMonth),
    NavTab("Add Expense", Icons.Filled.Add),
    NavTab("Categories", Icons.Filled.Category),
    NavTab("Accounts", Icons.Filled.Person)
)

@Composable
fun BottomNavBar(selected: Int, onSelect: (Int) -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(Color(0xFFE8E8E8)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navTabs.forEachIndexed { index, tab ->
                if (index == 2) {
                    Box(
                        Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF323232))
                            .clickable { onSelect(index) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(tab.icon, contentDescription = tab.label, tint = Color.White)
                    }
                } else {
                    Box(
                        Modifier
                            .size(40.dp)
                            .clickable { onSelect(index) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            tab.icon,
                            contentDescription = tab.label,
                            tint = if (selected == index) Color(0xFF323232) else Color(0xFF9E9E9E)
                        )
                    }
                }
            }
        }
    }
}
