package com.example.graduationpj.support.base.page

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.graduationpj.R
import com.example.graduationpj.support.base.model.WeatherEnum
import com.example.graduationpj.support.base.page.view.BaseTitleView
import com.example.graduationpj.support.ext.getStatusBarHeight
import com.example.graduationpj.support.network.NetWorkAPI
import com.example.graduationpj.support.network.NetWorkTask
import com.example.graduationpj.support.network.TaskCallBack
import kotlinx.android.synthetic.main.fragment_base_title.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception
import kotlin.concurrent.thread

abstract class BaseTitleFragment : BaseFragment() {
    abstract fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    //abstract fun initTopBarData(userIcon:Drawable?=null,userName:String ?=null,weatherType: WeatherEnum?=null)

    protected lateinit var mStatusBarBgView: View
    protected lateinit var mRootView: View
    private var userIcon: Drawable? = null
    private var userName: String? = null
    private var weatherEnum: WeatherEnum? = null
    private var weatherIcon: Drawable? = null
    private var weatherText: String? = null

    var isTitleViewHidden: Boolean = false
        set(value) {
            field = value
        }
    var isStatusBarBgViewHidden: Boolean = false
        set(value) {
            field = value
            updateStatusBarBgView()
        }

    open val backGroundRes: Int = R.color.white

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(R.layout.fragment_base_title, container, false)
        mRootView.findViewById<View>(R.id.containerCl).setBackgroundResource(backGroundRes)
        initStatusBarBgView()
        val contentFl = mRootView.findViewById<FrameLayout>(R.id.contentFl)
        contentFl.addView(onCreateContentView(inflater, container, savedInstanceState))
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initTopBarData()
        requestWeatherInfo()
        updateTopBarView()
    }

    private fun updateTopBarView() {
        //getWeatherInfo getUserInfo
        userIconIv.setImageDrawable(userIcon)
        userNameTv.text = userName
        weatherIconIv.setImageDrawable(context?.getDrawable(R.drawable.img_empty))
        weatherTextTv.text = weatherText
    }

    private fun initStatusBarBgView() {
        mStatusBarBgView = mRootView.findViewById(R.id.statusBarBgView)
        val lp = mStatusBarBgView.layoutParams
        lp.height = requireActivity().getStatusBarHeight()
        mStatusBarBgView.layoutParams = lp
        updateStatusBarBgView()
    }

    private fun updateStatusBarBgView() {
        mStatusBarBgView.visibility = if (isStatusBarBgViewHidden) View.GONE else View.VISIBLE
    }

    private fun requestWeatherInfo() {
        NetWorkTask.newTask().requestInfo(NetWorkAPI.WeatherAPI+"北京",object :TaskCallBack{
            override fun onFail(e: Exception) {
                return
            }

            override fun onSuccess(res: String) {
                return
            }
        })
    }
}