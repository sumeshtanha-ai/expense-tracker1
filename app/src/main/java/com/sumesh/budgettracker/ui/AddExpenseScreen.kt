package com.sumesh.budgettracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sumesh.budgettracker.TransactionViewModel
import com.sumesh.budgettracker.data.Category
import com.sumesh.budgettracker.data.TransactionType

@Composable
fun AddExpenseScreen(viewModel: TransactionViewModel, modifier: Modifier = Modifier) {
    val categories by viewModel.categories.collectAsState(initial = emptyList())
    var isIncome by remember { mutableStateOf(false) }
    var amountText by remember { mutableStateOf("") }
    var noteText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<Category?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Add Expense", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Add Expense", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF323232))
        }
        Spacer(Modifier.height(20.dp))

        Box(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFEFEFEF))
                .padding(24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text("AMOUNT", color = Color.Gray, fontSize = 13.sp)
                Spacer(Modifier.height(8.dp))
                TextField(
                    value = amountText,
                    onValueChange = { if (it.length <= 10) amountText = it },
                    textStyle = TextStyle(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    placeholder = {
                        Text(
                            "$0",
                            fontSize = 40.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White)
                        .padding(4.dp)
                ) {
                    ToggleChip("Income", isIncome) { isIncome = true }
                    ToggleChip("Expense", !isIncome) { isIncome = false }
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("Category", fontWeight = FontWeight.Medium, fontSize = 14.sp)
        Spacer(Modifier.height(8.dp))
        LazyRowCategories(categories, selectedCategory) { selectedCategory = it }

        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = noteText,
            onValueChange = { noteText = it },
            label = { Text("Note") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {
                val amount = amountText.toDoubleOrNull()
                if (amount != null && amount > 0) {
                    viewModel.addTransaction(
                        type = if (isIncome) TransactionType.INCOME else TransactionType.EXPENSE,
                        amount = amount,
                        note = noteText,
                        categoryId = selectedCategory?.id
                    )
                    amountText = ""
                    noteText = ""
                    selectedCategory = null
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF323232))
        ) {
            Text("Add Transaction", fontSize = 16.sp)
        }
    }
}

@Composable
fun ToggleChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (selected) Color(0xFF323232) else Color.Transparent)
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(label, color = if (selected) Color.White else Color.Gray, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun LazyRowCategories(categories: List<Category>, selected: Category?, onSelect: (Category) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(categories) { cat ->
            Box(
                Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (selected?.id == cat.id) Color(0xFF323232) else Color(0xFFF0F0F0))
                    .clickable { onSelect(cat) }
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                Text(
                    "${cat.icon} ${cat.name}",
                    color = if (selected?.id == cat.id) Color.White else Color.Black,
                    fontSize = 13.sp
                )
            }
        }
    }
}
