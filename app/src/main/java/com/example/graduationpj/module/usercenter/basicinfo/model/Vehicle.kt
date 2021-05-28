package com.example.graduationpj.module.usercenter.basicinfo.model

import java.io.Serializable

data class VehicleMessage(
    val data: MutableList<Vehicle>,
    val msg: String,
    val code: Int
) : Serializable

data class Vehicle(
    var idvehicle: Int,
    var vechielname: String,
    var vehicletype: String,
    var vehicledate: String,
    var vehiclemiles: Int,
    var userid: Int? = null
):Serializable