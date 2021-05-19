package com.example.graduationpj.module.login.model

import com.example.graduationpj.support.base.model.BaseModel

data class User(
            val iduser: Int? = null,
            val username: String? = null,
            val usersex: String? = null,
            val userhomeaddress: Int? = null,
            val userworkaddress: Int? = null,
): BaseModel()