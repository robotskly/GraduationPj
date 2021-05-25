package com.example.graduationpj.support.login

import com.example.graduationpj.module.logindemo.bean.User

class LoginManager {
    companion object{
        var user :User?=null

        fun newInstance():LoginManager{
            return LoginManager()
        }
    }
}