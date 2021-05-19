package com.example.graduationpj.module.logindemo.utils

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.graduationpj.MyApplication
import com.example.graduationpj.module.logindemo.common.Constant

/**
 * Created by Roman on 2021/1/11
 */
object SPUtil {
    /**
     *
     */
    @Synchronized
    private fun getSP(): SharedPreferences {
        return MyApplication.context.getSharedPreferences("music", MODE_PRIVATE)
    }

    /**
     * 用户是否登录
     */
    @Synchronized
    fun saveLogin(currentProgress: Boolean) {
        getSP().edit().putBoolean(Constant.IS_LOGIN,currentProgress).apply()
    }

    @Synchronized
    fun isLogin():Boolean{
        return getSP().getBoolean(Constant.IS_LOGIN, false)
    }
}