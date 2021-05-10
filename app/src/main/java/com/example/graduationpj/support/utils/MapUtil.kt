package com.example.graduationpj.support.utils

import android.content.Context
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption

class MapUtil(val context: Context) {

    //定位对象和配置
    private var locationClient: AMapLocationClient? = null
    private var locationOption: AMapLocationClientOption? = null

    fun currentLocation(callBack: ((Boolean, AMapLocation?) -> Unit)) {
        locationClient = AMapLocationClient(context)
        locationOption = AMapLocationClientOption()
        locationOption?.isOnceLocation = true
        locationOption?.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        locationClient?.setLocationOption(locationOption)
        locationClient?.startLocation()
        locationClient?.setLocationListener { aMapLocation ->
            if (aMapLocation != null) {
                if (aMapLocation.errorCode == 0) {
                    callBack.invoke(true, aMapLocation)
                } else {
                    callBack.invoke(false, null)
                }
            } else {
                callBack.invoke(false, null)
            }
            release()
        }
    }

    private fun release() {
        if (locationClient != null) {
            locationClient?.stopLocation()
            locationClient?.onDestroy()
        }
        locationClient = null
    }


}