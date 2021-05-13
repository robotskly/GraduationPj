package com.example.graduationpj.module.media.view

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationpj.R
import com.example.graduationpj.module.media.model.Song

class LocalMusicAdapter(context:Context,list:ArrayList<Song>): RecyclerView.Adapter<LocalMusicAdapter.MusicViewHolder>() {

    var list: ArrayList<Song>? = null
    var inflater: LayoutInflater? = null
    var context: Context? = null

    init {
        this.list = list
        this.inflater = LayoutInflater.from(context)
        this.context = context
    }
    class MusicViewHolder(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView!!) {
        //        var text: TextView = itemView?.findViewById(R.id.tv) as TextView
        var songTitleTv = itemView?.findViewById<TextView>(R.id.songTitleTv)
        var songSingerTv = itemView?.findViewById<TextView>(R.id.singerInfoTv)
        var durationTv = itemView?.findViewById<TextView>(R.id.durationTv)
        var songPicIv = itemView?.findViewById<ImageView>(R.id.songPicIv)
    }
    override fun getItemCount(): Int {
        return list?.size ?:0
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.songTitleTv?.text = list?.get(position)?.songName
        holder.songSingerTv?.text = list?.get(position)?.songSinger
        holder.durationTv?.text = list?.get(position)?.songDuration.toString()
        //holder?.songPicIv?.setImageDrawable(list?.get(position)?.songPicPath)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(inflater?.inflate(R.layout.view_song_card, parent, false), context!!)
    }


}