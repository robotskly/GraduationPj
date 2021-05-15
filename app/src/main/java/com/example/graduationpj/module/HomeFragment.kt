package com.example.graduationpj.module

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.graduationpj.R
import com.example.graduationpj.module.communication.CommunicationHomeFragment
import com.example.graduationpj.module.media.MediaHomeFragment
import com.example.graduationpj.module.navigation.CurrentLocationFragment
import com.example.graduationpj.module.navigation.NavigationHomeFragment
import com.example.graduationpj.module.note.NoteHomeFragment
import com.example.graduationpj.module.service.ServiceHomeFragment
import com.example.graduationpj.module.usercenter.UserCenterHomeFragment
import com.example.graduationpj.support.base.page.BaseTitleFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseTitleFragment() {

    private val text = arrayOf("首页", "通讯录", "发现", "我")
    private var iconDrawable: ArrayList<Drawable> = arrayListOf()
    private var iconDrawablePress: ArrayList<Drawable> = arrayListOf()

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initAction()
    }

    private fun initData() {
        val drawable =
            resources.getDrawable(R.drawable.icon_home_page)
        val drawablePress =
            resources.getDrawable(R.drawable.icon_home_page_press)
        iconDrawable =
            arrayListOf(drawable, drawable, drawable, drawable)
        iconDrawablePress = arrayListOf(
            drawablePress,
            drawablePress,
            drawablePress,
            drawablePress
        )
    }

    private fun initView() {
        mMenu.setText(text)
        mMenu.setIconDrawable(iconDrawable)
        mMenu.setIconDrawablePress(iconDrawablePress)
        //设置默认选中第一项
        //设置默认选中第一项
        mMenu.setPressState(0, MotionEvent.ACTION_DOWN)
        mMenu.setUnReadCount(0, 100)
        mMenu.setUnReadCount(1, 15)
        mMenu.setVisibilityMore(2, View.VISIBLE)
        mMenu.setVisibilityNew(3, View.VISIBLE)
    }

    private fun initAction() {
        mMenu.setOnItemClickListener { position ->
            when (position) {
                0 -> {

                }
                1 -> {

                }
                2 -> {

                }
                3 -> {

                }
                else -> {

                }
            }
        }

        navigationTv.setOnClickListener {
            //start(NavigationHomeFragment.newInstance())
            start(CurrentLocationFragment.newInstance())
        }
        noteTv.setOnClickListener {
            start(NoteHomeFragment.newInstance())
        }
        communicationTv.setOnClickListener {
            start(CommunicationHomeFragment.newInstance())
        }
        mediaTv.setOnClickListener {
            start(MediaHomeFragment.newInstance())
        }
        serviceTv.setOnClickListener {
            start(ServiceHomeFragment.newInstance())
        }
        userCenterTv.setOnClickListener {
            start(UserCenterHomeFragment.newInstance())
        }
    }
}