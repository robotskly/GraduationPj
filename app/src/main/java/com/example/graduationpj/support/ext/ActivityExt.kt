package com.example.graduationpj.support.ext

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build

/**
 * 获取顶部状态栏高度
 */
@SuppressLint("ObsoleteSdkInt")
fun Context.getStatusBarHeight(): Int {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
        return 0
    }
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return resources.getDimensionPixelSize(resourceId)
}