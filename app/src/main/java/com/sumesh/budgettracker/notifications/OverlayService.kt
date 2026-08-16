package com.sumesh.budgettracker.notifications

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import com.sumesh.budgettracker.data.AppDatabase
import com.sumesh.budgettracker.data.Transaction
import com.sumesh.budgettracker.data.TransactionType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private var popupView: View? = null
    private var amount: Double = 0.0

    private val categories = listOf(
        "Food" to "\uD83C\uDF54",
        "Transport" to "\uD83D\uDE97",
        "Bills" to "\uD83D\uDCC4",
        "Shopping" to "\uD83D\uDECD\uFE0F",
        "Coffee & Drinks" to "\u2615",
        "Others" to "\u26AA"
    )

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        amount = intent?.getDoubleExtra("amount", 0.0) ?: 0.0
        showPopup()
        return START_NOT_STICKY
    }

    private fun showPopup() {
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        val container = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 32, 40, 32)
            setBackgroundColor(0xF0323232.toInt())
        }

        val title = TextView(this).apply {
            text = "Rs.${"%.2f".format(amount)} spent \u2014 pick category"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 14f
            setPadding(0, 0, 0, 24)
            gravity = Gravity.CENTER
        }
        container.addView(title)

        val row1 = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
        val row2 = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }

        categories.forEachIndexed { index, (name, icon) ->
            val btn = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(24, 16, 24, 16)
                isClickable = true
                setOnClickListener {
                    saveTransaction(name)
                    removePopup()
                }
            }
            val iconView = TextView(this).apply {
                text = icon
                textSize = 26f
                gravity = Gravity.CENTER
            }
            val labelView = TextView(this).apply {
                text = name
                textSize = 10f
                setTextColor(0xFFFFFFFF.toInt())
                gravity = Gravity.CENTER
            }
            btn.addView(iconView)
            btn.addView(labelView)

            if (index < 3) row1.addView(btn) else row2.addView(btn)
        }

        val column = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(row1)
            addView(row2)
        }
        container.addView(column)

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )
        params.gravity = Gravity.CENTER

        popupView = container
        windowManager.addView(popupView, params)
    }

    private fun saveTransaction(categoryName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getInstance(applicationContext)
            val category = db.categoryDao().getAll().first().find { it.name == categoryName }
            db.transactionDao().insert(
                Transaction(
                    type = TransactionType.EXPENSE,
                    amount = amount,
                    note = "Auto-detected",
                    categoryId = category?.id
                )
            )
        }
    }

    private fun removePopup() {
        popupView?.let {
            try { windowManager.removeView(it) } catch (e: Exception) {}
            popupView = null
        }
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        popupView?.let {
            try { windowManager.removeView(it) } catch (e: Exception) {}
        }
    }
}
