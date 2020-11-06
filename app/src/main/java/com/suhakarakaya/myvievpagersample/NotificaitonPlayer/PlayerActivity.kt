package com.suhakarakaya.myvievpagersample.NotificaitonPlayer

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.suhakarakaya.myvievpagersample.NotificaitonActivity3.Companion.musicList
import com.suhakarakaya.myvievpagersample.R
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    var position: Int = -1
    var handler = Handler()

    companion object {
        var listSongs: ArrayList<MusicPlayerTemp> = ArrayList<MusicPlayerTemp>()
        var mediaPlayer = MediaPlayer()
        lateinit var uri: Uri
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        getIntentMethod()
        seekBaar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        runOnUiThread(object : Runnable {
            override fun run() {
                if (mediaPlayer != null) {
                    var mCurrentPosition: Int = mediaPlayer.currentPosition / 1000
                    durationPlayed.text = formattedTime(mCurrentPosition)
                }
                handler.postDelayed(this, 1000)
            }

        })
    }

    fun formattedTime(mCurrentPosition: Int): String {
        var totalout = ""
        var totalNew = ""
        var seconds: String = (mCurrentPosition % 60).toString()
        var minutes: String = (mCurrentPosition / 60).toString()
        totalout = minutes + ":" + seconds
        totalNew = minutes + ":" + "0" + seconds
        if (seconds.length == 1) {
            return totalNew
        } else {
            return totalout
        }
    }


    private fun getIntentMethod() {

        position = intent.getIntExtra("position", -1)
        listSongs = musicList

        if (listSongs != null) {
            play_pause.setImageResource(R.drawable.pause_asset)
            //uri = Uri.parse(listSongs.get(position).name)
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop()
            mediaPlayer.release()
            //mediaPlayer = MediaPlayer.create(applicationContext, uri)
            mediaPlayer = MediaPlayer.create(applicationContext, listSongs.get(position).music)
            mediaPlayer.start()
        } else {
            //mediaPlayer = MediaPlayer.create(applicationContext, uri)
            mediaPlayer = MediaPlayer.create(applicationContext, listSongs.get(position).music)
            mediaPlayer.start()
        }
        seekBaar.max = mediaPlayer.duration / 100
    }
}