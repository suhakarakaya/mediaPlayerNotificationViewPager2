package com.suhakarakaya.myvievpagersample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suhakarakaya.myvievpagersample.NewNotificationPlayer.CreateNotificaiton
import com.suhakarakaya.myvievpagersample.NewNotificationPlayer.Services.OnClearFromRecentService
import com.suhakarakaya.myvievpagersample.NewNotificationPlayer.Services.Playable
import com.suhakarakaya.myvievpagersample.NewNotificationPlayer.Track
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.play
import kotlinx.android.synthetic.main.activity_new_notification4.*

class NewNotificationActivity4 : AppCompatActivity(), Playable {

    lateinit var notificationmanager: NotificationManager
    lateinit var tracks: ArrayList<Track>
    var position: Int = 0
    var isPlaying: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_notification4)

        populateTrack()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannler()
            registerReceiver(broadcastReceive, IntentFilter("TRACKS TRACKS"))
            startService(Intent(baseContext, OnClearFromRecentService::class.java))
        }
        play_notificaiton.setOnClickListener {
            if (isPlaying) {
                onTrackPause()
            } else {
                onTrackPlay()
            }
        }


    }

    fun createChannler() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel = NotificationChannel(
                CreateNotificaiton.CHANNEL_ID,
                "KOD Dev",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationmanager = getSystemService(NotificationManager::class.java)
            if (notificationmanager != null) {
                notificationmanager.createNotificationChannel(channel)
            }


        }
    }


    //populate list with tracks

    fun populateTrack() {

        tracks = ArrayList()
        tracks.add(Track("Track1", "Artist1", R.drawable.buselik, R.raw.buselik))
        tracks.add(Track("Track2", "Artist2", R.drawable.rast, R.raw.rast))
        tracks.add(Track("Track3", "Artist3", R.drawable.isfahan, R.raw.isfahan))
        tracks.add(Track("Track4", "Artist4", R.drawable.rehavi, R.raw.rehavi))
        tracks.add(Track("Track5", "Artist5", R.drawable.hicaz, R.raw.hicaz))
        tracks.add(Track("Track6", "Artist6", R.drawable.huseyni, R.raw.huseyni))
        tracks.add(Track("Track7", "Artist7", R.drawable.ussak, R.raw.ussak))
        tracks.add(Track("Track8", "Artist8", R.drawable.buzurg, R.raw.buzurg))
        tracks.add(Track("Track9", "Artist9", R.drawable.neva, R.raw.neva))
        tracks.add(Track("Track10", "Artist10", R.drawable.zergule, R.raw.zengule))
        tracks.add(Track("Track11", "Artist11", R.drawable.ziref, R.raw.zirefkendl))
        tracks.add(Track("Track12", "Artist12", R.drawable.irak, R.raw.irak))
    }

    var broadcastReceive: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var action = intent?.extras?.getString("actionname")

            when (action) {
                CreateNotificaiton.ACTION_PREVIOUS -> onTrackPrevious()
                CreateNotificaiton.ACTION_PLAY -> if (isPlaying) {
                    onTrackPause()
                } else {
                    onTrackPlay()
                }
                CreateNotificaiton.ACTION_NEXT -> onTrackNext()
            }
        }

    }


    override fun onTrackPrevious() {
        position--
        CreateNotificaiton.createNotification(
            this,
            tracks.get(position),
            R.drawable.pause_asset,
            position,
            tracks.size - 1
        )
        title_notificaiton.text = tracks.get(position).title
    }

    override fun onTrackPlay() {

        CreateNotificaiton.createNotification(
            this,
            tracks.get(position),
            R.drawable.pause_asset,
            position,
            tracks.size - 1
        )
        play_notificaiton.setImageResource(R.drawable.pause_asset)
        title_notificaiton.text = tracks.get(position).title
        isPlaying = true
    }

    override fun onTrackPause() {
        CreateNotificaiton.createNotification(
            this,
            tracks.get(position),
            R.drawable.play_asset,
            position,
            tracks.size - 1
        )
        play_notificaiton.setImageResource(R.drawable.play_asset)
        title_notificaiton.text = tracks.get(position).title
        isPlaying = false
    }

    override fun onTrackNext() {
        if (position <= tracks.size - 1) {
            position++
            CreateNotificaiton.createNotification(
                this,
                tracks.get(position),
                R.drawable.pause_asset,
                position,
                tracks.size - 1
            )
            title_notificaiton.text = tracks.get(position).title
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationmanager.cancelAll()
        }
        unregisterReceiver(broadcastReceive)
    }
}