package com.sumesh.budgettracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun HistoryScreen(viewModel: TransactionViewModel, modifier: Modifier = Modifier) {
    val transactions by viewModel.allTransactions.collectAsState(initial = emptyList())
    val categories by viewModel.categories.collectAsState(initial = emptyList())
    val expenses = transactions.filter { it.type == TransactionType.EXPENSE }

    val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())
    val monthKeyFormat = SimpleDateFormat("yyyy-MM", Locale.getDefault())

    val now = Calendar.getInstance()
    val monthsBack = 6
    val monthCals = (monthsBack - 1 downTo 0).map {
        val cal = now.clone() as Calendar
        cal.add(Calendar.MONTH, -it)
        cal
    }
    val monthlyTotals = monthCals.map { cal ->
        val key = monthKeyFormat.format(cal.time)
        expenses.filter { monthKeyFormat.format(Date(it.date)) == key }.sumOf { it.amount }
    }
    val labels = monthCals.map { monthFormat.format(it.time) }

    val thisMonthKey = monthKeyFormat.format(now.time)
    val lastMonthCal = (now.clone() as Calendar).apply { add(Calendar.MONTH, -1) }
    val lastMonthKey = monthKeyFormat.format(lastMonthCal.time)

    val thisMonthTotal = expenses.filter { monthKeyFormat.format(Date(it.date)) == thisMonthKey }.sumOf { it.amount }
    val lastMonthTotal = expenses.filter { monthKeyFormat.format(Date(it.date)) == lastMonthKey }.sumOf { it.amount }
    val percentChange = if (lastMonthTotal > 0) ((thisMonthTotal - lastMonthTotal) / lastMonthTotal * 100) else 0.0

    val categoryThisMonth = categories.map { cat ->
        Triple(
            cat,
            expenses.filter { it.categoryId == cat.id && monthKeyFormat.format(Date(it.date)) == thisMonthKey }.sumOf { it.amount },
            expenses.filter { it.categoryId == cat.id && monthKeyFormat.format(Date(it.date)) == lastMonthKey }.sumOf { it.amount }
        )
    }.filter { it.second > 0 || it.third > 0 }
        .sortedByDescending { it.second }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Spending Trend", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("History", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF323232))
        }

        Card(shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(20.dp)) {
                LineChart(values = monthlyTotals.map { it.toFloat() }, labels = labels)
                Spacer(Modifier.height(16.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (percentChange >= 0) Color(0xFFE8F5E9) else Color(0xFFFFEBEE))
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "${if (percentChange >= 0) "\u2191" else "\u2193"} ${"%.1f".format(kotlin.math.abs(percentChange))}% vs last month",
                            color = if (percentChange >= 0) Color(0xFF2E7D32) else Color(0xFFC62828),
                            fontSize = 12.sp
                        )
                    }
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFFF0F0F0))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(now.time), fontSize = 12.sp)
                    }
                }
            }
        }

        Card(shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(20.dp)) {
                Text("Category Breakdown", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(Modifier.height(16.dp))
                if (categoryThisMonth.isEmpty()) {
                    Text("No data yet", color = Color.Gray)
                } else {
                    val maxAmount = categoryThisMonth.maxOf { maxOf(it.second, it.third) }.takeIf { it > 0 } ?: 1.0
                    categoryThisMonth.forEach { (cat, thisM, lastM) ->
                        Column(Modifier.padding(vertical = 8.dp)) {
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(cat.name, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                                Text("$${"%.0f".format(thisM)}", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            }
                            Spacer(Modifier.height(6.dp))
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .height(8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Color(0xFFE0E0E0))
                                ) {
                                    Box(
                                        Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth((thisM / maxAmount).toFloat().coerceIn(0f, 1f))
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(parseColor(cat.colorHex))
                                    )
                                }
                                Box(
                                    Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Color(0xFFE0E0E0))
                                ) {
                                    Box(
                                        Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth((lastM / maxAmount).toFloat().coerceIn(0f, 1f))
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(Color(0xFFBDBDBD))
                                    )
                                }
                            }
                            Spacer(Modifier.height(4.dp))
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("This Month", fontSize = 11.sp, color = Color.Gray)
                                Text("Last Month", fontSize = 11.sp, color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }
}
