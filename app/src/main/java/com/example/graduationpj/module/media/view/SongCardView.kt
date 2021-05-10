package com.example.graduationpj.module.media.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.graduationpj.R
import com.example.graduationpj.module.media.model.Song
import kotlinx.android.synthetic.main.view_song_card.view.*

class SongCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defAttr) {

    private var song:Song?=null
    private var songPic: Drawable? = null
    private var songTitle: String? = ""
    private var songSinger: String? = ""
    private var songDuration:Int?=0

    init {
        View.inflate(context, R.layout.view_song_card, this)
        initData(attributeSet)
        initView()
    }

    private fun initView() {
        songPicIv.setImageDrawable(songPic)
        songTitleTv.text = songTitle
        singerInfoTv.text = songSinger
        durationTv.text = songDuration.toString()
    }

    private fun initData(attributeSet: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.SongCardView)
        this.songPic = (typedArray.getDrawable(R.styleable.SongCardView_songPic))
            ?: context.getDrawable(R.drawable.img_empty)
        songTitleTv.text = (typedArray.getText(R.styleable.SongCardView_songTitle))
        singerInfoTv.text = (typedArray.getText(R.styleable.SongCardView_songSinger))
        typedArray.recycle()
    }

    fun updateView(song:Song) {
        //this.songPic = context?.getDrawable(song.songPicPath)
        this.song = song
        this.songTitle = song.songName
        this.songSinger = song.songSinger
        this.songDuration = song.songDuration
        initView()
    }

    fun getSongPath():String{
        return song?.songPath?:""
    }
}