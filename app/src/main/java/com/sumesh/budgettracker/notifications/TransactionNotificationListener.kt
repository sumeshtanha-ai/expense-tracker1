package com.sumesh.budgettracker.notifications

import android.content.Intent
import android.provider.Settings
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class TransactionNotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        val extras = sbn.notification.extras
        val title = extras.getCharSequence("android.title")?.toString() ?: ""
        val text = extras.getCharSequence("android.text")?.toString() ?: ""
        val fullText = "$title $text"

        Log.d("AmdhaniListener", "Package: ${sbn.packageName} | Title: $title | Text: $text")

        val parsed = TransactionParser.parse(fullText)
        if (parsed != null && parsed.isDebit && parsed.amount > 0) {
            Log.d("AmdhaniListener", "Detected debit: ${parsed.amount}")
            if (Settings.canDrawOverlays(this)) {
                val intent = Intent(this, OverlayService::class.java)
                intent.putExtra("amount", parsed.amount)
                startService(intent)
            } else {
                Log.d("AmdhaniListener", "Overlay permission not granted, skipping popup")
            }
        }
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.d("AmdhaniListener", "Listener connected")
    }
}
