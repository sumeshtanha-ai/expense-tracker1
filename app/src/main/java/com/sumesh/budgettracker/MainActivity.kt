package com.sumesh.budgettracker

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sumesh.budgettracker.ui.AccountsScreen
import com.sumesh.budgettracker.ui.AddExpenseScreen
import com.sumesh.budgettracker.ui.BottomNavBar
import com.sumesh.budgettracker.ui.CategoriesScreen
import com.sumesh.budgettracker.ui.HistoryScreen
import com.sumesh.budgettracker.ui.HomeScreen
import com.sumesh.budgettracker.ui.theme.BudgetTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            BudgetTrackerTheme {
                AppRoot()
            }
        }
    }
}

@Composable
fun AppRoot() {
    val viewModel: TransactionViewModel = viewModel()
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavBar(selected = selectedTab, onSelect = { selectedTab = it })
        }
    ) { padding ->
        val contentModifier = Modifier.padding(padding)
        when (selectedTab) {
            0 -> HomeScreen(viewModel, contentModifier)
            1 -> HistoryScreen(viewModel, contentModifier)
            2 -> AddExpenseScreen(viewModel, contentModifier)
            3 -> CategoriesScreen(viewModel, contentModifier)
            4 -> AccountsScreen(viewModel, contentModifier)
        }
    }
}
