package com.sumesh.budgettracker

import android.content.Context

class PreferencesManager(context: Context) {
    private val prefs = context.getSharedPreferences("amdhani_prefs", Context.MODE_PRIVATE)

    fun getName(): String = prefs.getString("full_name", "Your Name") ?: "Your Name"
    fun setName(name: String) = prefs.edit().putString("full_name", name).apply()

    fun getBudget(): Double = prefs.getFloat("monthly_budget", 3500f).toDouble()
    fun setBudget(budget: Double) = prefs.edit().putFloat("monthly_budget", budget.toFloat()).apply()
}
