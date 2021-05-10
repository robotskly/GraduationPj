package com.example.graduationpj

import android.app.Application
import me.yokeyword.fragmentation.Fragmentation


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // 设置 栈视图 模式为 （默认）悬浮球模式
        //	SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
        Fragmentation.builder()
            .stackViewMode(Fragmentation.BUBBLE)
            .debug(BuildConfig.DEBUG).install();
    }
}