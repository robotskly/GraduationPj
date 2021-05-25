package com.example.graduationpj.module.usercenter.routehistory.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.graduationpj.R
import com.example.graduationpj.module.usercenter.routehistory.model.RouteModel
import kotlinx.android.synthetic.main.view_route_record_card.view.*
import kotlinx.android.synthetic.main.view_song_card.view.*
import java.text.SimpleDateFormat
import java.util.*

class RouteRecordCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defAttributes: Int = 0
) : ConstraintLayout(context, attributeSet, defAttributes) {

    init {
        View.inflate(context, R.layout.view_route_record_card,null)
    }

    fun updateView(routeModel:RouteModel) {
        startPlaceTv.text = routeModel.routeStartPlace
        endPlaceTv.text = routeModel.routeEndPlace
        durationTv.text = routeModel.routeDuration.toString()
        routeMiles.text = routeModel.routeMiles.toString()
        routeTime.text = getTime(routeModel.routeStartTime!!)
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime(date: Date): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return simpleDateFormat.format(date)
    }

}