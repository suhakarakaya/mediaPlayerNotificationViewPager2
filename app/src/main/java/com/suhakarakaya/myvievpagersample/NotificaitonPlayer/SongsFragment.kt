package com.suhakarakaya.myvievpagersample.NotificaitonPlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suhakarakaya.myvievpagersample.NotificaitonActivity3.Companion.musicFiles
import com.suhakarakaya.myvievpagersample.NotificaitonActivity3.Companion.musicList
import com.suhakarakaya.myvievpagersample.R
import kotlinx.android.synthetic.main.fragment_songs.*

class SongsFragment : Fragment() {

    lateinit var recyclerView1: RecyclerView
    lateinit var musicAdapter: MusicAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_songs, container, false)






        recyclerView1 = view.findViewById(R.id.recyclerView)


        //recyclerView1.setHasFixedSize(true)

        if (!(musicFiles.size < 1)) {
            musicAdapter = activity?.let { MusicAdapter(it, musicList) }!!
        }

        recyclerView1.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = musicAdapter
        }
        //recyclerView1.adapter = musicAdapter
        //recyclerView1.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


        return view
    }


}