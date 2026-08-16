package com.sumesh.budgettracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sumesh.budgettracker.TransactionViewModel
import com.sumesh.budgettracker.data.TransactionType
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(viewModel: TransactionViewModel, modifier: Modifier = Modifier) {
    val transactions by viewModel.allTransactions.collectAsState(initial = emptyList())
    val categories by viewModel.categories.collectAsState(initial = emptyList())
    val name by viewModel.fullName.collectAsState()
    val budget by viewModel.monthlyBudget.collectAsState()

    val expenses = transactions.filter { it.type == TransactionType.EXPENSE }
    val totalSpent = expenses.sumOf { it.amount }
    val remaining = budget - totalSpent
    val usedPercent = if (budget > 0) (totalSpent / budget * 100).coerceIn(0.0, 100.0) else 0.0

    val categoryTotals = expenses.groupBy { it.categoryId }
        .mapNotNull { (catId, txns) ->
            val cat = categories.find { it.id == catId } ?: return@mapNotNull null
            cat to txns.sumOf { it.amount }
        }
        .sortedByDescending { it.second }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text("Hello, $name! \uD83D\uDC4B", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text("Track your expenses wisely", color = Color.Gray, fontSize = 14.sp)
                }
                Text("Home", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF323232))
            }
        }

        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Brush.verticalGradient(listOf(Color(0xFF323232), Color(0xFF323232))))
                    .padding(20.dp)
            ) {
                Column {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text("Total Spent This Month", color = Color.White.copy(alpha = 0.85f), fontSize = 14.sp)
                            Text("$${"%.2f".format(totalSpent)}", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                        }
                        Box(
                            Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.AttachMoney, contentDescription = null, tint = Color.White)
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Budget: $${"%.0f".format(budget)}", color = Color.White, fontSize = 13.sp)
                        Text("Remaining: $${"%.0f".format(remaining)}", color = Color.White, fontSize = 13.sp)
                    }
                    Spacer(Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { (usedPercent / 100).toFloat() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = Color.Black,
                        trackColor = Color.White.copy(alpha = 0.3f)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text("${"%.1f".format(usedPercent)}% used", color = Color.White.copy(alpha = 0.85f), fontSize = 12.sp)
                }
            }
        }

        item {
            Card(shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp)) {
                    Text("Spending by Category", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(Modifier.height(16.dp))
                    if (categoryTotals.isEmpty()) {
                        Text("No expenses yet", color = Color.Gray)
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            DonutChart(
                                values = categoryTotals.map { it.second.toFloat() },
                                colors = categoryTotals.map { parseColor(it.first.colorHex) }
                            )
                            Spacer(Modifier.width(16.dp))
                            Column {
                                categoryTotals.forEach { (cat, amount) ->
                                    val percent = if (totalSpent > 0) (amount / totalSpent * 100) else 0.0
                                    Row(
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                Modifier
                                                    .size(10.dp)
                                                    .clip(CircleShape)
                                                    .background(parseColor(cat.colorHex))
                                            )
                                            Spacer(Modifier.width(6.dp))
                                            Text(cat.name, fontSize = 13.sp)
                                        }
                                        Column(horizontalAlignment = Alignment.End) {
                                            Text("$${"%.0f".format(amount)}", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                            Text("${"%.0f".format(percent)}%", color = Color.Gray, fontSize = 11.sp)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Card(shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Recent Transactions", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text("View All", color = Color(0xFF323232), fontSize = 13.sp)
                    }
                    Spacer(Modifier.height(12.dp))
                    val recent = transactions.sortedByDescending { it.date }.take(4)
                    if (recent.isEmpty()) {
                        Text("No transactions yet", color = Color.Gray)
                    } else {
                        recent.forEach { t ->
                            val cat = categories.find { it.id == t.categoryId }
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        Modifier
                                            .size(40.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(Color(0xFFECECEC)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(cat?.icon ?: "\uD83D\uDCB8", fontSize = 18.sp)
                                    }
                                    Spacer(Modifier.width(10.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        SmallTag(cat?.name ?: "Other")
                                        Spacer(Modifier.width(6.dp))
                                        Text(formatRelativeDate(t.date), color = Color.Gray, fontSize = 12.sp)
                                    }
                                }
                                val sign = if (t.type == TransactionType.EXPENSE) "-" else "+"
                                Text(
                                    "$sign$${"%.2f".format(t.amount)}",
                                    fontWeight = FontWeight.Bold,
                                    color = if (t.type == TransactionType.EXPENSE) Color.Black else Color(0xFF2E7D32)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SmallTag(text: String) {
    Box(
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF0F0F0))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(text, fontSize = 11.sp)
    }
}

fun parseColor(hex: String): Color {
    return try {
        Color(android.graphics.Color.parseColor(hex))
    } catch (e: Exception) {
        Color.Gray
    }
}

fun formatRelativeDate(date: Long): String {
    val today = Calendar.getInstance()
    val txnCal = Calendar.getInstance().apply { timeInMillis = date }
    val yesterday = (today.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, -1) }

    return when {
        isSameDay(today, txnCal) -> "Today"
        isSameDay(yesterday, txnCal) -> "Yesterday"
        else -> SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date(date))
    }
}

fun isSameDay(a: Calendar, b: Calendar): Boolean {
    return a.get(Calendar.YEAR) == b.get(Calendar.YEAR) && a.get(Calendar.DAY_OF_YEAR) == b.get(Calendar.DAY_OF_YEAR)
}
