package com.sumesh.budgettracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class TransactionType { INCOME, EXPENSE }

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: TransactionType,
    val amount: Double,
    val note: String,
    val categoryId: Int? = null,
    val date: Long = System.currentTimeMillis()
)
