package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.util

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.app.NotificationChannel
import android.content.BroadcastReceiver


class NotificationPublisher : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = intent.getParcelableExtra<Notification>(NOTIFICATION)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NAME",
                importance
            )
            assert(notificationManager != null)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val id = intent.getIntExtra(NOTIFICATION_ID, 0)
        assert(notificationManager != null)
        notificationManager.notify(id, notification)
    }



    companion object {
        var NOTIFICATION_ID = "notification-id"
        var NOTIFICATION = "notification"
        var NOTIFICATION_CHANNEL_ID = "10001"
    }
}
