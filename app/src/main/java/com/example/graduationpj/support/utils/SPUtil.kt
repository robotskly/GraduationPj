package com.example.graduationpj.support.utils

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.graduationpj.MyApplication
import com.example.graduationpj.support.network.ConfigConst

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
        getSP()
            .edit().putBoolean(ConfigConst.IS_LOGIN,currentProgress).apply()
    }

    @Synchronized
    fun isLogin():Boolean{
        return getSP()
            .getBoolean(ConfigConst.IS_LOGIN, false)
    }
}