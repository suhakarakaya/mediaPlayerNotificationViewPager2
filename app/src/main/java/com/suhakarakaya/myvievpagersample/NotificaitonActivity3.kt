package com.suhakarakaya.myvievpagersample

import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.suhakarakaya.myvievpagersample.NotificaitonPlayer.*
import kotlinx.android.synthetic.main.activity_notificaiton3.*
import java.util.jar.Manifest
import java.util.jar.Pack200

class NotificaitonActivity3 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaiton3)
        permisssion()

        var viewPagerAdapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragments(SongsFragment(), "Songs")
        viewPagerAdapter.addFragments(AlbumFragment(), "Albums")
        viewpager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(viewpager)

        musicList.add(MusicPlayerTemp("buselik", R.raw.buselik, R.drawable.buselik))
        musicList.add(MusicPlayerTemp("rast", R.raw.rast, R.drawable.rast))
        musicList.add(MusicPlayerTemp("isfahan", R.raw.isfahan, R.drawable.isfahan))
        musicList.add(MusicPlayerTemp("rehavi", R.raw.rehavi, R.drawable.rehavi))
        musicList.add(MusicPlayerTemp("hicaz", R.raw.hicaz, R.drawable.hicaz))
        musicList.add(MusicPlayerTemp("huseyni", R.raw.huseyni, R.drawable.huseyni))
        musicList.add(MusicPlayerTemp("ussak", R.raw.ussak, R.drawable.ussak))
        musicList.add(MusicPlayerTemp("buzurg", R.raw.buzurg, R.drawable.buzurg))
        musicList.add(MusicPlayerTemp("zergule", R.raw.zengule, R.drawable.zergule))
        musicList.add(MusicPlayerTemp("neva", R.raw.neva, R.drawable.neva))
        musicList.add(MusicPlayerTemp("ziref", R.raw.zirefkendl, R.drawable.ziref))
        musicList.add(MusicPlayerTemp("ırak", R.raw.irak, R.drawable.irak))

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted !", Toast.LENGTH_SHORT).show()
            musicFiles = getAudioAll(this)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE
            )
        }
    }

    fun permisssion() {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE
            )
        } else {
            Toast.makeText(this, "Permission Granted !", Toast.LENGTH_SHORT).show()
            musicFiles = getAudioAll(this)
        }
    }


    companion object {

        var REQUEST_CODE: Int = 1
        lateinit var musicFiles: ArrayList<MusicFiles>
        var musicList = ArrayList<MusicPlayerTemp>()



        fun getAudioAll(context: Context): ArrayList<MusicFiles> {

            var tempAudioList = ArrayList<MusicFiles>()
            var uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            var projection = arrayOf<String>(
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST
            )
            var cursor: Cursor? = context.contentResolver.query(uri, projection, null, null, null)

            if (cursor != null) {

                while (cursor.moveToNext()) {
                    var album: String = cursor.getString(0)
                    var title: String = cursor.getString(1)
                    var duration: String = cursor.getString(2)
                    var path: String = cursor.getString(3)
                    var artist: String = cursor.getString(4)


                    var musicFiles: MusicFiles = MusicFiles(path, title, artist, album, duration)
                    Log.e("Path" + path, "Album" + album)
                    tempAudioList.add(musicFiles)

                }
                cursor.close()
            }

            return tempAudioList
        }


    }


}