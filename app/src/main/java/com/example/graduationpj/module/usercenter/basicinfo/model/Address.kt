package com.example.graduationpj.module.usercenter.basicinfo.model

import com.example.graduationpj.module.logindemo.bean.User
import java.io.Serializable

data class AddressMessage(
    val data: Address,
    val msg: String,
    val code: Int
) : Serializable

data class Address(
    var idaddress: Int,
    var country: String,
    var province: String,
    var city: String,
    var area: String,
    var street: String,
    var location: String,
    var userId:Int
):Serializable