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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sumesh.budgettracker.TransactionViewModel
import com.sumesh.budgettracker.data.Category

@Composable
fun CategoriesScreen(viewModel: TransactionViewModel, modifier: Modifier = Modifier) {
    val categories by viewModel.categories.collectAsState(initial = emptyList())
    val transactions by viewModel.allTransactions.collectAsState(initial = emptyList())
    var showAddDialog by remember { mutableStateOf(false) }
    var editingCategory by remember { mutableStateOf<Category?>(null) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Sell, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Custom Categories", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Box(
                    Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF323232)),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { showAddDialog = true }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add", tint = Color.White)
                    }
                }
            }
        }
        item {
            Text("Categories", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF323232))
        }

        items(categories) { cat ->
            val count = transactions.count { it.categoryId == cat.id }
            Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(parseColor(cat.colorHex)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(cat.icon, fontSize = 20.sp)
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(cat.name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Text("$count transactions", color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                    Row {
                        IconButton(onClick = { editingCategory = cat }) {
                            Icon(Icons.Filled.Edit, contentDescription = "Edit", tint = Color.Gray)
                        }
                        IconButton(onClick = { viewModel.deleteCategory(cat) }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = Color(0xFFE53935))
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        CategoryDialog(
            title = "Add Category",
            onDismiss = { showAddDialog = false },
            onConfirm = { name, icon, color ->
                viewModel.addCategory(name, icon, color)
                showAddDialog = false
            }
        )
    }

    editingCategory?.let { cat ->
        CategoryDialog(
            title = "Edit Category",
            initialName = cat.name,
            initialIcon = cat.icon,
            onDismiss = { editingCategory = null },
            onConfirm = { name, icon, color ->
                viewModel.updateCategory(cat.copy(name = name, icon = icon, colorHex = color))
                editingCategory = null
            }
        )
    }
}

@Composable
fun CategoryDialog(
    title: String,
    initialName: String = "",
    initialIcon: String = "\uD83C\uDFF7\uFE0F",
    onDismiss: () -> Unit,
    onConfirm: (String, String, String) -> Unit
) {
    var name by remember { mutableStateOf(initialName) }
    var icon by remember { mutableStateOf(initialIcon) }
    val colors = listOf("#F44336", "#2196F3", "#4CAF50", "#FFC107", "#FF7043", "#3F51B5", "#9E9E9E", "#8E24AA")
    var selectedColor by remember { mutableStateOf(colors.first()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(value = icon, onValueChange = { icon = it }, label = { Text("Emoji icon") })
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    colors.forEach { c ->
                        Box(
                            Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(parseColor(c))
                                .clickable { selectedColor = c }
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (name.isNotBlank()) onConfirm(name, icon, selectedColor)
            }) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
