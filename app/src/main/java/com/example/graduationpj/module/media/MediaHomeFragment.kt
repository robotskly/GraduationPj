package com.example.graduationpj.module.media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationpj.R
import com.example.graduationpj.module.media.model.Song
import com.example.graduationpj.module.media.view.LocalMusicAdapter
import com.example.graduationpj.module.media.view.SongCardView
import com.example.graduationpj.support.base.page.BaseTitleFragment
import com.example.graduationpj.support.utils.MusicUtil
import kotlinx.android.synthetic.main.fragment_home_media.*
import okhttp3.internal.notify


class MediaHomeFragment : BaseTitleFragment() {
    private var songArrayList: ArrayList<Song> = arrayListOf()
    private var songCardViewList: ArrayList<SongCardView> = arrayListOf()
    private var localMusicAdapter:LocalMusicAdapter?=null

    companion object {
        fun newInstance(): MediaHomeFragment {
            return MediaHomeFragment()
        }
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_media, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initAction()
    }

    private fun initData() {
        songArrayList = MusicUtil.newInstance().getMusic(context)
    }

    private fun initView() {
        songArrayList.forEach {
            val songCardView = context?.let { it1 -> SongCardView(it1) }
            songCardView?.updateView(song = it)
            songCardViewList.add(songCardView!!)
        }
        //initRv
        musicListRv.layoutManager = LinearLayoutManager(context)
        // musicListRv.addItemDecoration()
        localMusicAdapter = LocalMusicAdapter(context!!,songArrayList,object :LocalMusicAdapter.NotifyUI{
            override fun notifyUI(song: Song?) {
                songTitleTv.text = song?.songName
                singerInfoTv.text = song?.songSinger
            }

        })
        musicListRv.adapter = localMusicAdapter
    }

    private fun initAction() {
        moreSongIv.setOnClickListener{
            musicListRv.isVisible = !musicListRv.isVisible
            music_bg.isVisible = !musicListRv.isVisible
        }
    }


    private fun updateView() {

    }
}