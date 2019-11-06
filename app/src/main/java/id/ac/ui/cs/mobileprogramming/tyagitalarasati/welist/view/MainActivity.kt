package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R

import kotlinx.android.synthetic.main.activity_main.*
import android.app.AlarmManager
import android.content.Context.ALARM_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.os.SystemClock
import android.app.PendingIntent
import android.content.Intent
import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat





class MainActivity : AppCompatActivity() {
    val NOTIFICATION_CHANNEL_ID = "10001"
    private val default_notification_channel_id = "default"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        scheduleNotification(getNotification( "Welcome to WeList. Let's create your wishlist!" ) , 5000 )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun scheduleNotification(notification: Notification, delay: Int) {
        val notificationIntent = Intent(this, NotificationPublisher::class.java)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val futureInMillis = SystemClock.elapsedRealtime() + delay
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent)
    }

    private fun getNotification(content: String): Notification {
        val builder = NotificationCompat.Builder(this, default_notification_channel_id)
        builder.setContentTitle("WeList Notification")
        builder.setContentText(content)
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
        builder.setAutoCancel(true)
        builder.setChannelId(NOTIFICATION_CHANNEL_ID)
        return builder.build()
    }
}
