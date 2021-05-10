package com.example.graduationpj.module.navigation

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
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.route.*
import com.example.graduationpj.R
import com.example.graduationpj.support.base.page.BaseTitleFragment
import com.example.graduationpj.support.utils.ConvertM.dp2px
import com.example.graduationpj.support.utils.MapUtil
import kotlinx.android.synthetic.main.fragment_home_navigation.*

class NavigationHomeFragment : BaseTitleFragment(),RouteSearch.OnRouteSearchListener {
    private var mapView:MapView?=null
    private var aMap: AMap? = null
    private var mapUiSettings: UiSettings? = null
    //路线规划
    private var routeSearch: RouteSearch? = null
    //当前位置的经纬度
    private var currentPoint: LatLonPoint? = null

    //目标位置的经纬度
    private var targetPoint: LatLonPoint? = null

    //定位配置和监听
    private var locationClient: AMapLocationClient? = null
    private var locationOption: AMapLocationClientOption? = null
    private var locationChangedListener: LocationSource.OnLocationChangedListener? = null

    //地图工具
    private var mapUtil: MapUtil? = null

    private var destination:String?=null
    private var startPlace:String?=null

    companion object {
        fun newInstance(): NavigationHomeFragment {
            return NavigationHomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapView?.onCreate(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
        if (null != locationClient) {
            locationClient?.onDestroy()
        }
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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initAction()
    }

    private fun initData() {
        aMap = mapView?.map
        mapUiSettings = aMap?.uiSettings
        destination = endPlaceTv.text.toString()
        startPlace = startPlaceTv.text.toString()
    }

    private fun initView() {
        initMapBase()

    }

    private fun initAction() {

    }

    private fun initMapBase(){
        targetPoint = LatLonPoint(39.985937, 116.393105)
        mapUiSettings?.isCompassEnabled =true
        mapUiSettings?.isZoomGesturesEnabled = true
        mapUiSettings?.isZoomControlsEnabled = true
        mapUiSettings?.isMyLocationButtonEnabled = true
        mapUiSettings?.isScaleControlsEnabled = true
        mapUiSettings?.isScrollGesturesEnabled = true
    }

    private fun initLocationListener() {
        aMap?.setLocationSource(object : LocationSource {
            //激活定位
            override fun activate(p0: LocationSource.OnLocationChangedListener?) {
                locationChangedListener = p0
                if (locationClient == null) {
                    locationClient = AMapLocationClient(context)
                    locationOption = AMapLocationClientOption()
                    //设置为高精度定位模式
                    locationOption?.isOnceLocation = true
                    locationOption?.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
                    //定位成功监听
                    locationClient?.setLocationListener {
                        if (locationChangedListener != null && it != null) {
                            if (it.errorCode == 0) {
                                locationChangedListener?.onLocationChanged(it)
                                currentPoint = LatLonPoint(it.latitude, it.longitude)
                                routeSearch()
                            } else {
                                val errText = "location error :" + it.errorCode + ": " + it.errorInfo
                                Toast.makeText(context,errText,Toast.LENGTH_SHORT).show()
                            }
                        }
                        deactivate()
                    }
                    locationClient?.setLocationOption(locationOption)
                    locationClient?.startLocation()
                }
            }

            override fun deactivate() {
                locationChangedListener = null
                if (locationClient != null) {
                    locationClient?.stopLocation()
                    locationClient?.onDestroy()
                }
                locationClient = null
            }
        })
    }

    private fun routeSearch() {
        routeSearch = RouteSearch(context)
        routeSearch?.setRouteSearchListener(this)
        val fromAndTo = RouteSearch.FromAndTo(currentPoint, targetPoint)
        val query = RouteSearch.DriveRouteQuery(
            fromAndTo,
            RouteSearch.DRIVING_SINGLE_DEFAULT,
            null,
            null,
            ""
        )
        routeSearch?.calculateDriveRouteAsyn(query)
    }


    override fun onSupportInvisible() {
        super.onSupportInvisible()
        mapView?.onPause()
        locationChangedListener = null
        if (locationClient != null) {
            locationClient?.stopLocation()
            locationClient?.onDestroy()
        }
        locationClient = null
    }

    override fun onBusRouteSearched(result: BusRouteResult?, errorCode: Int) {}
    override fun onWalkRouteSearched(result: WalkRouteResult?, errorCode: Int) {}
    override fun onRideRouteSearched(result: RideRouteResult?, errorCode: Int) {}
    override fun onDriveRouteSearched(result: DriveRouteResult?, errorCode: Int) {
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result?.paths != null && result.paths.count() > 0) {
                //构建路径点集合
                val latLngPoints = arrayListOf<LatLonPoint>()
                val steps = result.paths.firstOrNull()?.steps
                steps?.forEach { latLngPoints.addAll(it?.polyline ?: arrayListOf()) }
                //移动相机
                moveCameraBound(latLngPoints)
                //画路径线
                drawRouteLine(latLngPoints)
                //画终点标记
                drawTargetMarker(LatLng(latLngPoints.lastOrNull()!!.latitude, latLngPoints.lastOrNull()!!.longitude))
            } else {
                Toast.makeText(context,context?.getString(R.string.no_route),Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context,errorCode,Toast.LENGTH_SHORT).show()
        }
    }

    private fun moveCameraBound(latLngPoints: ArrayList<LatLonPoint>) {
        val boundsBuilder = LatLngBounds.Builder()
        latLngPoints.forEach { boundsBuilder.include(LatLng(it.latitude, it.longitude)) }
        aMap?.moveCamera(CameraUpdateFactory.newLatLngBoundsRect(boundsBuilder.build(), dp2px(context,50f), dp2px(context,50f), dp2px(context,100f), dp2px(context,180f)))
    }

    private fun drawRouteLine(latLngPoints: ArrayList<LatLonPoint>) {
        aMap?.addPolyline((PolylineOptions())
            .addAll(latLngPoints.map { LatLng(it.latitude, it.longitude) })
            .width(20f)
            .setDottedLine(false)
            .geodesic(true)
            .color(Color.parseColor("#539CFF")))
    }

    private fun drawTargetMarker(latLng: LatLng) {
        aMap?.addMarker(MarkerOptions().apply {
            position(latLng)
            visible(true)
            draggable(false)
            icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(resources, R.drawable.icon_target_dot)))
        })
    }
}