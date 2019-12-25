package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.util

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.app.Notification
import android.app.NotificationManager
import android.app.NotificationChannel
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.activity.MainActivity

class RingtoneService : Service() {

    companion object {
        lateinit var ringtone: Ringtone
//        var NOTIFICATION_ID = "notification-id"
//        var NOTIFICATION = "notification"
        var NOTIFICATION_CHANNEL_ID = "AlarmManager"
    }

    var id = 0
    var currId = 0
    var isRunning: Boolean = false

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var state = intent!!.getStringExtra("Extra")
        assert(state != null)
        when(state) {
            "On" -> id = 1
            "Off" -> id = 0
        }

        currId = id

        if(!isRunning && id == 1) {
            pushNotification()
            playAlarm()
            isRunning = true
            id = 0

        } else if(isRunning && id == 0) {

            ringtone.stop()
            isRunning = false
            id = 0

        } else if(isRunning && id == 0) {

            isRunning = false
            id = 0

        } else if(this.isRunning && id == 1) {

            isRunning = true
            id = 1

        }

        return START_NOT_STICKY
    }

    private fun pushNotification() {
        //this function still doesn't works

        val pendingIntent = NavDeepLinkBuilder(applicationContext)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.navigation)
            .setArguments(Bundle())
            .setDestination(R.id.reminderFragment)
            .createPendingIntent()
//        val main = Intent(this, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 0, main, 0)
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                NotificationPublisher.NOTIFICATION_CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NEW",
                importance
            )
            assert(notificationManager != null)
            notificationManager.createNotificationChannel(notificationChannel)
        }


        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        notificationBuilder.setContentTitle("Alarm is going off")
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background)
        notificationBuilder.setSound(defaultSound)
        notificationBuilder.setContentText("Click Me")
        notificationBuilder.setContentIntent(pendingIntent)
        notificationBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
        notificationBuilder.setAutoCancel(true)

        assert(notificationManager != null)
        notificationManager.notify(10, notificationBuilder.build())
    }

    private fun playAlarm() {
        var alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        if(alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }

        ringtone = RingtoneManager.getRingtone(baseContext, alarmUri)
        ringtone.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        id = currId
    }
}