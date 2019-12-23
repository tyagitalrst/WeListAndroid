package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.util

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.R

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private val songsId = (0..3).random()
    private val listOfSongs =
        listOf<Int>(R.raw.batchbug, R.raw.friendzoned, R.raw.success, R.raw.sunshine)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        mediaPlayer = MediaPlayer.create(this, listOfSongs.get(songsId))
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}