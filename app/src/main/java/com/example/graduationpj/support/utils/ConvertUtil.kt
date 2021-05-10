package com.example.graduationpj.support.utils

import android.content.Context

open class ConvertUtil {
    companion object{
        fun dp2px(context: Context, dp:Float):Float=dp * context.resources.displayMetrics.density

        fun px2dp(context:Context,px:Float):Float =px / context.resources.displayMetrics.density
    }
}