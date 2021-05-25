package com.example.graduationpj.module.usercenter.routehistory.model

import com.example.graduationpj.module.logindemo.bean.User
import java.io.Serializable
import java.util.*


data class RouteMessage(
    val data: List<RouteModel>,
    val msg: String,
    val code: Int
) : Serializable

data class RouteModel(
    var routeId:Int?=null,
    var routeStartPlace:String? = null,
    var routeEndPlace:String? = null,
    var routeDuration:Int?=null,
    var routeStartTime:Date?=null,
    var routeMiles:Int?=null
):Serializable