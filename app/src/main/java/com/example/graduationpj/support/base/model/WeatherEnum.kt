package com.example.graduationpj.support.base.model

import android.graphics.drawable.Drawable
import com.example.graduationpj.R
import com.google.gson.annotations.SerializedName
import java.lang.ref.Reference

enum class WeatherEnum(val value:Int,val weather:String) {
    @SerializedName("1")
    Sunny(1,"晴朗"),

    @SerializedName("2")
    Cloudy(2,"多云"),

    @SerializedName("3")
    Rainy(3,"下雨")


}