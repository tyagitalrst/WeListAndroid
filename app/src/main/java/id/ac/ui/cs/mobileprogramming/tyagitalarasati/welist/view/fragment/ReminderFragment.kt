package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.fragment


import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat

import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.util.AlarmReceiver
import kotlinx.android.synthetic.main.fragment_reminder.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ReminderFragment : Fragment() {


    private var hour = 0
    private var minute = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminder, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(activity, AlarmReceiver::class.java)
        val calendar = Calendar.getInstance()

        buttonSetAlarm.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
                calendar.set(Calendar.MINUTE, timePicker.minute)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                hour = timePicker.hour
                minute = timePicker.minute
            } else {
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.currentHour)
                calendar.set(Calendar.MINUTE, timePicker.currentMinute)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                hour = timePicker.currentHour
                minute = timePicker.currentMinute
            }

            var hour_str = hour.toString()
            var minute_str = minute.toString()
            var am_pm = ""

            if(hour > 12) {
                hour_str = (hour - 12).toString()
                am_pm = " PM"
            } else {
                am_pm = " AM"
            }


            if(minute < 10) {
                minute_str = "0$minute"
            }

            val output = hour_str +":"+ minute_str + am_pm
            alarmSet.setText(R.string.state_reminder)
            clock.setText(output)
            alarmIntent.putExtra("Extra", "On")

            val pendingIntent = PendingIntent.getBroadcast(activity, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }

        buttonStopAlarm.setOnClickListener {
            alarmSet.setText("Alarm off")
            clock.setText("")
            alarmIntent.putExtra("Extra", "Off")


            val pendingIntent = PendingIntent.getBroadcast(activity, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager.cancel(pendingIntent)
            getActivity()?.sendBroadcast(alarmIntent)
        }

    }



}
