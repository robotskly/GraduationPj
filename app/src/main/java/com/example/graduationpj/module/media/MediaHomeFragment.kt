package com.example.graduationpj.module.media

import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationpj.R
import com.example.graduationpj.module.media.model.Song
import com.example.graduationpj.module.media.view.SongCardView
import com.example.graduationpj.support.base.page.BaseTitleFragment
import com.example.graduationpj.support.utils.MusicUtil
import java.io.IOException


class MediaHomeFragment :BaseTitleFragment(){
    private val mediaPlayer:MediaPlayer = MediaPlayer()
    private var songArrayList:ArrayList<Song> = arrayListOf()
    private var songCardViewList:ArrayList<SongCardView> = arrayListOf()
    companion object{
        fun newInstance():MediaHomeFragment{
            return MediaHomeFragment()
        }
    }
    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_media,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initAction()
    }
    private fun initData(){
        songArrayList = MusicUtil.newInstance().getMusic(context)
    }
    private fun initView(){
        songArrayList.forEach {
            val songCardView = context?.let { it1 -> SongCardView(it1) }
            songCardView?.updateView(song = it)
            songCardViewList.add(songCardView!!)
        }
    }
    private fun initAction(){
        songCardViewList.forEach { cardView->
            cardView.setOnClickListener {
                play(cardView.getSongPath())
            }
        }
    }
    fun play(path: String?) {
        try {
            //        重置音频文件，防止多次点击会报错
            mediaPlayer.reset()
            //        调用方法传进播放地址
            mediaPlayer.setDataSource(path)
            //            异步准备资源，防止卡顿
            mediaPlayer.prepareAsync()
            //            调用音频的监听方法，音频准备完毕后响应该方法进行音乐播放
            mediaPlayer.setOnPreparedListener(OnPreparedListener { mediaPlayer -> mediaPlayer.start() })
        } catch (e: IOException) {
            e.printStackTrace()
        }
        updateView()
    }

    private fun updateView(){

    }
}