package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        var getResult = p1!!.getStringExtra("Extra")

        var serviceIntent = Intent(p0, RingtoneService::class.java)
        serviceIntent.putExtra("Extra", getResult)
        p0!!.startService(serviceIntent)
    }

}