package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.activity

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.util.NotificationPublisher

class SplashScreenActivity : AppCompatActivity() {

    private val NOTIFICATION_CHANNEL_ID = "10001"
    private val default_notification_channel_id = "default"
    private val SPLASH_TIME_OUT:Long = 5000
    private val id = (0..1).random()
    private val listOfNotif = listOf<String>("Let's check your long term wishlist & make it come true!",
        "Let's check your short term wishlist & make it come true!")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        scheduleNotification(getNotification( listOfNotif.get(id) ) , 10000 )

        Handler().postDelayed({
            startActivity(Intent(this,
                MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)

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
        var fragment = 0
        if (id.equals(0)) {
            fragment = R.id.listLongTermFragment
        } else {
            fragment = R.id.listFragment
        }
        val pendingIntent = NavDeepLinkBuilder(applicationContext)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.navigation)
            .setArguments(Bundle())
            .setDestination(fragment)
            .createPendingIntent()
        val builder = NotificationCompat.Builder(this, default_notification_channel_id)
        builder.setContentTitle("Hey, Don't Forget!")
        builder.setContentText(content)
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
        builder.setAutoCancel(true)
        builder.setChannelId(NOTIFICATION_CHANNEL_ID)
        builder.setContentIntent(pendingIntent)
        return builder.build()
    }
}
