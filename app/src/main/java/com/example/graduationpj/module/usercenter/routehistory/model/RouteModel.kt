package com.example.graduationpj.module.usercenter.routehistory.model

import com.example.graduationpj.module.logindemo.bean.User
import java.io.Serializable
import java.util.*


data class RouteMessage(
    val data: MutableList<RouteModel>,
    val msg: String,
    val code: Int
) : Serializable

data class RouteModel(
    var idroute:Int?=null,
    var startplace:String? = null,
    var endplace:String? = null,
    var routeduration:Int?=null,
    var routestartdate:String?=null,
    var vehicleid:Int?=null
):Serializable