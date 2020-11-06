package com.suhakarakaya.myvievpagersample

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.SeekBar
import com.suhakarakaya.myvievpagersample.MultiMusic.SongImage
import com.suhakarakaya.myvievpagersample.MultiMusic.SongTitle
import com.suhakarakaya.myvievpagersample.MultiMusic.Songs
import kotlinx.android.synthetic.main.activity_main2.*

class MediaPlayerActivity2 : AppCompatActivity() {

    var mediaPlayer = MediaPlayer()

    lateinit var audioManager: AudioManager
    var currentIndex: Int = 0
    var songsTitleList = ArrayList<SongTitle>()
    var songsList = ArrayList<Songs>()
    var songImageList = ArrayList<SongImage>()
    var handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager


        songsList.add(Songs(0, R.raw.buselik))
        songsList.add(Songs(1, R.raw.rast))
        songsList.add(Songs(2, R.raw.isfahan))
        songsList.add(Songs(3, R.raw.rehavi))
        songsList.add(Songs(4, R.raw.hicaz))
        songsList.add(Songs(5, R.raw.huseyni))
        songsList.add(Songs(6, R.raw.ussak))
        songsList.add(Songs(7, R.raw.buzurg))
        songsList.add(Songs(8, R.raw.neva))
        songsList.add(Songs(9, R.raw.zengule))
        songsList.add(Songs(10, R.raw.zirefkendl))
        songsList.add(Songs(11, R.raw.irak))

        songImageList.add(SongImage(0, R.drawable.buselik))
        songImageList.add(SongImage(1, R.drawable.rast))
        songImageList.add(SongImage(2, R.drawable.isfahan))
        songImageList.add(SongImage(3, R.drawable.rehavi))
        songImageList.add(SongImage(4, R.drawable.hicaz))
        songImageList.add(SongImage(5, R.drawable.huseyni))
        songImageList.add(SongImage(6, R.drawable.ussak))
        songImageList.add(SongImage(7, R.drawable.buzurg))
        songImageList.add(SongImage(8, R.drawable.neva))
        songImageList.add(SongImage(9, R.drawable.zergule))
        songImageList.add(SongImage(10, R.drawable.ziref))
        songImageList.add(SongImage(11, R.drawable.irak))


        songsTitleList.add(SongTitle(0, "buselik"))
        songsTitleList.add(SongTitle(1, "rast"))
        songsTitleList.add(SongTitle(2, "isfahan"))
        songsTitleList.add(SongTitle(3, "rehav"))
        songsTitleList.add(SongTitle(4, "hicaz"))
        songsTitleList.add(SongTitle(5, "huseyni"))
        songsTitleList.add(SongTitle(6, "ussak"))
        songsTitleList.add(SongTitle(7, "buzurg"))
        songsTitleList.add(SongTitle(8, "neva"))
        songsTitleList.add(SongTitle(9, "zengule"))
        songsTitleList.add(SongTitle(10, "zirefkendl"))
        songsTitleList.add(SongTitle(11, "irak"))

        mediaPlayer = MediaPlayer.create(applicationContext, songsList.get(currentIndex).name)

        var maxV: Int = audioManager.mediaCurrentVolume
        var curv: Int = audioManager.mediaMaxVolume

        seek_bar_volume.max = maxV
        seek_bar_volume.setProgress(curv)

        seek_bar_volume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                audioManager.setMediaVolume(AudioManager.STREAM_MUSIC, progress, 0)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        play.setOnClickListener {

            seek_bar_title.max = mediaPlayer.duration
            if (mediaPlayer != null && mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                play.setImageResource(R.drawable.play)
                img_play.setImageResource(songImageList.get(currentIndex).image)
            } else {
                mediaPlayer.start()
                play.setImageResource(R.drawable.pause)
                img_play.setImageResource(songImageList.get(currentIndex).image)
            }
            songDetails()

        }

        next.setOnClickListener {
            if (mediaPlayer != null) {
                play.setImageResource(R.drawable.pause)
                img_play.setImageResource(songImageList.get(currentIndex).image)
            }
            if (currentIndex < songsList.size - 1) {
                currentIndex++
                img_play.setImageResource(songImageList.get(currentIndex).image)
            } else {
                currentIndex = 0
                img_play.setImageResource(songImageList.get(currentIndex).image)
            }
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }

            mediaPlayer = MediaPlayer.create(applicationContext, songsList.get(currentIndex).name)
            mediaPlayer.start()
            songDetails()
        }

        prev.setOnClickListener {
            if (mediaPlayer != null) {
                play.setImageResource(R.drawable.pause)
                img_play.setImageResource(songImageList.get(currentIndex).image)
            }
            if (currentIndex > 0) {
                currentIndex--
                img_play.setImageResource(songImageList.get(currentIndex).image)
            } else {
                currentIndex = songsList.size - 1
                img_play.setImageResource(songImageList.get(currentIndex).image)
            }
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer = MediaPlayer.create(applicationContext, songsList.get(currentIndex).name)
            mediaPlayer.start()
            songDetails()

        }


        seek_bar_title.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                    seek_bar_title.setProgress(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })


    }

    private fun songDetails() {
        for (num in songsTitleList) {
            if (currentIndex == num.index) {
                song_title.text = num.name
            }
        }

        mediaPlayer.setOnPreparedListener {
            seek_bar_title.max = mediaPlayer.duration
            mediaPlayer.start()
        }

        Thread(object : Runnable {
            override fun run() {
                while (mediaPlayer != null) {
                    try {
                        if (mediaPlayer.isPlaying) {
                            var message = Message()
                            message.what = mediaPlayer.currentPosition
                            handler.handleMessage(message)
                            Thread.sleep(1000)
                        }
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        ).start()


    }

    val AudioManager.mediaMaxVolume: Int
        get() = this.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

    val AudioManager.mediaCurrentVolume: Int
        get() = this.getStreamVolume(AudioManager.STREAM_MUSIC)

    fun AudioManager.setMediaVolume(volumeIndex: Int, progress: Int, flags: Int) {
        // Set media volume level
        this.setStreamVolume(
            AudioManager.STREAM_MUSIC, // Stream type
            volumeIndex, // Volume index
            AudioManager.FLAG_SHOW_UI// Flags
        )
    }
}