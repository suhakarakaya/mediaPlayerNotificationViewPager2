package com.suhakarakaya.myvievpagersample.NotificaitonPlayer

import android.content.Context
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suhakarakaya.myvievpagersample.R
import com.suhakarakaya.myvievpagersample.ViewPager2.IntroSlide
import kotlinx.android.synthetic.main.music_items.view.*


class MusicAdapter(var mContext: Context, var mFiles: ArrayList<MusicPlayerTemp>) :
    RecyclerView.Adapter<MusicAdapter.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fileName = view.findViewById<TextView>(R.id.music_file_name)
        val album_art = view.findViewById<ImageView>(R.id.music_img)

        fun getAlbumArt(uri: String): ByteArray {

            var retriever: MediaMetadataRetriever = MediaMetadataRetriever()
            retriever.setDataSource(uri)

            var art: ByteArray = retriever.embeddedPicture
            retriever.release()
            return art


        }

        fun bind(musicPlayerTemp: MusicPlayerTemp) {

            fileName.text = musicPlayerTemp.name
            album_art.setImageResource(musicPlayerTemp.image)
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicAdapter.MyViewHolder {
        var view: View = LayoutInflater.from(mContext).inflate(R.layout.music_items, parent, false)
        var myViewHolder: MyViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MusicAdapter.MyViewHolder, position: Int) {
        holder.bind(mFiles[position])
        holder.itemView.setOnClickListener {
            Intent(mContext, PlayerActivity::class.java).also {
                it.putExtra("position", position)
                mContext.startActivity(it)
            }
        }

        //holder.itemView.music_file_name.text = mFiles.get(position).name
        /*var image: ByteArray = holder.getAlbumArt(mFiles[position].path)
        if (image != null) {
            Glide.with(mContext).asBitmap().load(image).into(holder.album_art)
        } else {
            Glide.with(mContext).asBitmap().load(R.drawable.ziref).into(holder.album_art)
        }*/
    }

    override fun getItemCount(): Int {
        return mFiles.size
    }
}