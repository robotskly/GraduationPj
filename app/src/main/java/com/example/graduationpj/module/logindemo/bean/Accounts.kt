package com.example.graduationpj.module.logindemo.bean

import java.io.Serializable


/**
 * Created by Roman on 2021/1/22
 * 登录返回
 */

data class Accounts(
    val data: User,
    val msg: String,
    val code: Int
) : Serializable

data class User(
    var iduser: Int? = null,
    var username: String? = null,
    var usersex: Int? = null,
    var userhomeaddress: Int? = null,
    var userworkaddress: Int? = null,
    var userPassword: String? = null
)