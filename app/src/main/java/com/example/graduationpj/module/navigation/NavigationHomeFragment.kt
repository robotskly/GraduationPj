package com.example.graduationpj.module.navigation

import android.app.Activity
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.maps.*
import com.amap.api.maps.model.*
import com.amap.api.services.core.AMapException
import com.amap.api.services.core.AMapException.CODE_AMAP_SUCCESS
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.route.*
import com.example.graduationpj.MainActivity

import com.example.graduationpj.R
import com.example.graduationpj.support.base.page.BaseTitleFragment
import com.example.graduationpj.support.utils.ConvertM.dp2px
import com.example.graduationpj.support.utils.MapUtil
import kotlinx.android.synthetic.main.fragment_home_navigation.*

class NavigationHomeFragment : BaseTitleFragment() {
    private var mapView: MapView?=null
    private var aMap: AMap? = null
    private var mapUiSettings: UiSettings? = null


    companion object {
        fun newInstance(): NavigationHomeFragment {
            return NavigationHomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapView?.onCreate(savedInstanceState)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        activity
    }
    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        mapView?.onSaveInstanceState(outState)
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home_navigation, container, false)
        mapView = view.findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        if(aMap==null){
            aMap = mapView?.map
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = view.findViewById(R.id.mapView)
        initData()
        initView()
        initAction()
    }

    private fun initData() {

    }

    private fun initView() {

    }

    private fun initAction() {

    }

}