package com.example.graduationpj.module.navigation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.*
import com.amap.api.maps.LocationSource.OnLocationChangedListener
import com.amap.api.maps.model.*
import com.amap.api.services.core.AMapException
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.core.PoiItem
import com.amap.api.services.geocoder.GeocodeSearch
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener
import com.amap.api.services.route.*
import com.amap.api.services.route.RouteSearch.DriveRouteQuery
import com.amap.api.services.route.RouteSearch.FromAndTo
import com.example.graduationpj.MyApplication
import com.example.graduationpj.R
import com.example.graduationpj.module.navigation.adapter.AddressAdapter
import com.example.graduationpj.module.navigation.navigation.GPSNaviFragment
import com.example.graduationpj.module.navigation.overlays.DrivingRouteOverlay
import com.example.graduationpj.support.base.page.BaseTitleFragment
import com.example.graduationpj.support.utils.AMapUtil
import com.example.graduationpj.support.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_home_navigation.*


class NavigationHomeFragment : BaseTitleFragment(), AMapLocationListener, LocationSource,
    RouteSearch.OnRouteSearchListener, OnPoiSearchListener {
    //GeocodeSearch.OnGeocodeSearchListener
    private var mMap: AMap? = null
    private var mapView: MapView? = null
    private var mListener: OnLocationChangedListener? = null
    private var mLocationClient: AMapLocationClient? = null
    private var mLocationOption: AMapLocationClientOption? = null
    private var myLocation: LatLng? = null
    private var startPoint: LatLonPoint? = null
    private var endPoint: LatLonPoint? = null
    private var uiSettings: UiSettings? = null
    private var cameraUpdate: CameraUpdate? = null
    private var routeSearch: RouteSearch? = null
    private var geocoderSearch: GeocodeSearch? = null
    private val ROUTE_TYPE_DRIVE = 2
    private var driveRouteResult: DriveRouteResult? = null
    private var startGeoMarker: Marker? = null//起点marker
    private var endGeoMarker: Marker? = null//终点marker
    private var poiSearch: PoiSearch? = null

    companion object {
        fun newInstance(): NavigationHomeFragment {
            return NavigationHomeFragment()
        }
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = View.inflate(context, R.layout.fragment_home_navigation, null)
        mapView = view?.findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
    }

    private fun initView() {
        initBasicMap()
        addressRv.isVisible = false
    }

    /**
     * 初始化定位
    //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。
    //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
    //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
    //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
    //以下三种模式从5.1.0版本开始提供
    //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
    //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。
     */

    //初始化基本地图属性
    private fun initBasicMap() {
        if (mMap == null) {
            mMap = mapView?.map
        }
        mMap?.setTrafficEnabled(true);// 显示实时交通状况
        //地图UI显示
        uiSettings = mMap!!.uiSettings
        uiSettings?.isZoomControlsEnabled = true//支持缩放
        uiSettings?.isMyLocationButtonEnabled = true // 设置默认定位按钮是否显示
        uiSettings?.isCompassEnabled = true //  指南针
        startGeoMarker = mMap?.addMarker(
            MarkerOptions()
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )
        endGeoMarker = mMap?.addMarker(
            MarkerOptions()
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )
        cameraUpdate = CameraUpdateFactory.zoomTo(15f)
        mMap?.moveCamera(cameraUpdate)
        /**
         *         地理信息编码
         */
//        geocoderSearch = GeocodeSearch(MyApplication.context)
//        geocoderSearch?.setOnGeocodeSearchListener(this)
        /**
         *         初始化定位
         */
        mMap?.setLocationSource(this) // 设置定位监听
        //设置定位蓝点样式
        val myLocationStyle = MyLocationStyle()
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.icon_start_place))
        myLocationStyle.strokeWidth(0f)
        myLocationStyle.radiusFillColor(Color.TRANSPARENT)
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
        mMap?.myLocationStyle = myLocationStyle
        mMap?.isMyLocationEnabled = true//true表示显示定位层并可触发定位 false表示不显示定位层并不可以触发定位
        /**
         * 驾车路线搜索
         */
        routeSearch = RouteSearch(context)
        routeSearch?.setRouteSearchListener(this)


    }

    private fun initAction() {
        exitIv.setOnClickListener { pop() }
        confirmTv.setOnClickListener {
            if (startInfoEt.text == null || endInfoEt.text == null) {
                Toast.makeText(context, "起点或终点不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            start(GPSNaviFragment())
            //   searchRouteResult(ROUTE_TYPE_DRIVE, RouteSearch.DrivingDefault)
        }
        endInfoEt.addTextChangedListener {
            poi_Search(endInfoEt.text.toString())
            // getLatlon(endInfoEt.text.toString())
        }
    }

    //设置定位初始化以及启动定位回掉
    override fun activate(onLocationChangedListener: OnLocationChangedListener) {
        mListener = onLocationChangedListener
        if (mLocationClient == null) {
            mLocationClient = AMapLocationClient(MyApplication.context)
            mLocationOption = AMapLocationClientOption()
            //设置定位监听
            mLocationClient?.setLocationListener(this)
            //设置为高精度定位模式
            mLocationOption?.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
            //设置定位参数
            //mLocationOption?.setOnceLocation(true) //只定位一次
            mLocationOption?.httpTimeOut = 2000
            mLocationClient?.setLocationOption(mLocationOption)
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mLocationClient?.startLocation() //开始定位
        }
    }

    //停止定位回掉
    override fun deactivate() {
        mListener = null
        if (mLocationClient != null) {
            mLocationClient?.stopLocation()
            mLocationClient?.onDestroy()
        }
        mLocationClient = null
    }

    //1。显示定位
    // 在定位回调中设置显示定位小蓝点
    // 在回调方法中调用“mListener.onLocationChanged(amapLocation);”可以在地图上显示系统小蓝点。
    override fun onLocationChanged(aMapLocation: AMapLocation?) {
        if (aMapLocation != null && aMapLocation.errorCode == 0) {
            mListener?.onLocationChanged(aMapLocation)
            //获取当前经纬度坐标
            val address = aMapLocation.address
            myLocation = LatLng(aMapLocation.latitude, aMapLocation.longitude)
            //将起点设置为当前定位位置
            startInfoEt.setText(address)
            startPoint = LatLonPoint(aMapLocation.latitude, aMapLocation.longitude)
        }
    }

    /**
     * 2。确定终点 开始搜索地理位置
     * 响应地理编码
     * 根据输入的地点开始搜索
     */

    private fun poi_Search(str: String) {
        val mPoiSearchQuery = PoiSearch.Query(str, "", "苏州市")
        mPoiSearchQuery.requireSubPois(true) //true 搜索结果包含POI父子关系; false
        mPoiSearchQuery.pageSize = 10
        mPoiSearchQuery.pageNum = 0
        val poiSearch = PoiSearch(context, mPoiSearchQuery)
        poiSearch.setOnPoiSearchListener(this)
        poiSearch.searchPOIAsyn()
    }

    /**
     * 3。得到地理位置搜索结果
     * 搜索之后得到对应地理信息的回掉
     */

    //搜索到一个POi数据
    override fun onPoiItemSearched(item: PoiItem, rCode: Int) {
        addressRv.layoutManager = LinearLayoutManager(context)
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            val poiItems: MutableList<PoiItem> =
                java.util.ArrayList()
            poiItems.add(item)
            val mpoiadapter =
                AddressAdapter(
                    poiItems,
                    object :
                        AddressAdapter.GetAddress {
                        override fun getAddress(index: Int) {
                            endPoint = poiItems[index].latLonPoint
                        }
                    })
            addressRv.adapter = mpoiadapter
        } else {
            ToastUtil.showerror(context, rCode)
        }
    }

    //搜索到多个数据
    override fun onPoiSearched(result: PoiResult?, rCode: Int) {
        addressRv.layoutManager = LinearLayoutManager(context)
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null) {
                val poiItems: List<PoiItem> = result.pois
                val mpoiadapter =
                    AddressAdapter(
                        poiItems,
                        object :
                            AddressAdapter.GetAddress {
                            override fun getAddress(index: Int) {

                                //用户点击选择之后
                                val address = poiItems[index]
                                mMap?.animateCamera(
                                    CameraUpdateFactory.newLatLngZoom(
                                        AMapUtil.convertToLatLng(address.latLonPoint),
                                        15f
                                    )
                                )
                                endGeoMarker?.position =
                                    AMapUtil.convertToLatLng(address.latLonPoint)
                                endPoint = address.latLonPoint
                                val addressName = """
                    经纬度值:${address.latLonPoint}
                    位置描述:${address.businessArea}
                    """.trimIndent()
                                searchRouteResult(ROUTE_TYPE_DRIVE, RouteSearch.DrivingDefault)
                                addressRv.isVisible = false
                                ToastUtil.show(context, addressName)
                            }
                        })
                addressRv.adapter = mpoiadapter
                addressRv.isVisible = true
            }
        } else {
            ToastUtil.showerror(context, rCode)
        }
    }


    /**
     * 4。显示输入结果的结果列表
     */


    /**
     * 5。确定了起点和终点
     * 开始搜索路径规划方案
     */
    fun searchRouteResult(routeType: Int, mode: Int) {
        if (startPoint == null) {
            Toast.makeText(context, "定位中，稍后再试...", Toast.LENGTH_SHORT)
            return
        }
        if (endPoint == null) {
            ToastUtil.show(context, "终点未设置")
        }
        // showLoadingDialog()
        val fromAndTo = FromAndTo(
            startPoint, endPoint
        )
        if (routeType == ROUTE_TYPE_DRIVE) { // 驾车路径规划
            val query = DriveRouteQuery(
                fromAndTo, mode, null,
                null, ""
            ) // 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
            routeSearch?.calculateDriveRouteAsyn(query) // 异步路径规划驾车模式查询
        }
    }

    /**
     * 6。搜索路线之后的回掉
     * 将路线图层添加到地图
     */
    /**
     通过 DriveRouteQuery(RouteSearch.FromAndTo fromAndTo, int mode, List<LatLonPoint> passedByPoints, List<List<LatLonPoint>> avoidpolygons, String avoidRoad) 设置搜索条件，方法对应的参数说明如下：
    fromAndTo，路径的起点终点；
    mode，路径规划的策略，可选，默认为0-速度优先；详细策略请见驾车策略说明；
    passedByPoints，途经点，可选；
    avoidpolygons，避让区域，可选，支持32个避让区域，每个区域最多可有16个顶点。如果是四边形则有4个坐标点，如果是五边形则有5个坐标点。
    avoidRoad，避让道路，只支持一条避让道路，避让区域和避让道路同时设置，只有避让道路生效。
     */
    override fun onDriveRouteSearched(result: DriveRouteResult?, rCode: Int) {
        mMap?.clear() // 清理地图上的所有覆盖物
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.paths != null) {
                if (result.paths.size > 0) {
                    driveRouteResult = result
                    driveRouteResult?.paths?.forEachIndexed { index, drivePath ->
                        val drivingRouteOverlay =
                            DrivingRouteOverlay(
                                context, mMap, drivePath,
                                driveRouteResult?.startPos,
                                driveRouteResult?.targetPos, null
                            )
                        drivingRouteOverlay.setNodeIconVisibility(false) //设置节点marker是否显示
                        drivingRouteOverlay.setIsColorfulline(true) //是否用颜色展示交通拥堵情况，默认true
                        drivingRouteOverlay.removeFromMap()
                        drivingRouteOverlay.addToMap()
                        drivingRouteOverlay.zoomToSpan()
                    }
                }
            }
        }
    }

    override fun onBusRouteSearched(p0: BusRouteResult?, p1: Int) {}

    override fun onRideRouteSearched(p0: RideRouteResult?, p1: Int) {}

    override fun onWalkRouteSearched(p0: WalkRouteResult?, p1: Int) {}

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationOption = null
        mLocationClient = null
        mMap = null
        mapView!!.onDestroy()
    }
}