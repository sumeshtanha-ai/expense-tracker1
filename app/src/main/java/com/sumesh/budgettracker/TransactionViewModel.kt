package com.sumesh.budgettracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sumesh.budgettracker.data.AppDatabase
import com.sumesh.budgettracker.data.Category
import com.sumesh.budgettracker.data.Transaction
import com.sumesh.budgettracker.data.TransactionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val transactionDao = db.transactionDao()
    private val categoryDao = db.categoryDao()
    private val prefs = PreferencesManager(application)

    val allTransactions: Flow<List<Transaction>> = transactionDao.getAll()
    val categories: Flow<List<Category>> = categoryDao.getAll()

    private val _fullName = MutableStateFlow(prefs.getName())
    val fullName: StateFlow<String> = _fullName.asStateFlow()

    private val _monthlyBudget = MutableStateFlow(prefs.getBudget())
    val monthlyBudget: StateFlow<Double> = _monthlyBudget.asStateFlow()

    init {
        viewModelScope.launch {
            seedDefaultCategoriesIfNeeded()
        }
    }

    private suspend fun seedDefaultCategoriesIfNeeded() {
        val existing = categoryDao.getAll().first()
        if (existing.isEmpty()) {
            listOf(
                Category(name = "Food", icon = "\uD83C\uDF54", colorHex = "#F44336"),
                Category(name = "Transport", icon = "\uD83D\uDE97", colorHex = "#2196F3"),
                Category(name = "Bills", icon = "\uD83D\uDCC4", colorHex = "#4CAF50"),
                Category(name = "Shopping", icon = "\uD83D\uDECD\uFE0F", colorHex = "#FFC107"),
                Category(name = "Coffee & Drinks", icon = "\u2615", colorHex = "#FF7043"),
                Category(name = "Gym & Fitness", icon = "\uD83D\uDCAA", colorHex = "#43A047"),
                Category(name = "Pet Expenses", icon = "\uD83D\uDC3E", colorHex = "#3F51B5"),
                Category(name = "Others", icon = "\u26AA", colorHex = "#9E9E9E")
            ).forEach { categoryDao.insert(it) }
        }
    }

    fun addTransaction(type: TransactionType, amount: Double, note: String, categoryId: Int?) {
        viewModelScope.launch {
            transactionDao.insert(Transaction(type = type, amount = amount, note = note, categoryId = categoryId))
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionDao.delete(transaction)
        }
    }

    fun addCategory(name: String, icon: String, colorHex: String) {
        viewModelScope.launch {
            categoryDao.insert(Category(name = name, icon = icon, colorHex = colorHex))
        }
    }

    fun updateCategory(category: Category) {
        viewModelScope.launch { categoryDao.update(category) }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch { categoryDao.delete(category) }
    }

    fun updateName(name: String) {
        _fullName.value = name
        prefs.setName(name)
    }

    fun updateBudget(budget: Double) {
        _monthlyBudget.value = budget
        prefs.setBudget(budget)
    }
}
