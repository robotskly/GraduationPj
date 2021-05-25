package com.example.graduationpj.support.utils

import android.annotation.SuppressLint
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

open class ConvertUtil {
    companion object{
        fun dp2px(context: Context, dp:Float):Float=dp * context.resources.displayMetrics.density

        fun px2dp(context:Context,px:Float):Float =px / context.resources.displayMetrics.density

        @SuppressLint("SimpleDateFormat")
        fun date2StringYMDHMS(date: Date): String {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return simpleDateFormat.format(date)
        }

        @SuppressLint("SimpleDateFormat")
        fun date2StringYMD(date: Date): String {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            return simpleDateFormat.format(date)
        }
    }
}