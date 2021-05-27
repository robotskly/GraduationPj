package com.example.graduationpj.module.usercenter.routehistory.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.graduationpj.R
import com.example.graduationpj.module.usercenter.routehistory.model.RouteModel
import kotlinx.android.synthetic.main.view_route_record_card.view.*
import kotlinx.android.synthetic.main.view_song_card.view.*

class RouteRecordCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defAttributes: Int = 0
) : ConstraintLayout(context, attributeSet, defAttributes) {

    init {
        View.inflate(context, R.layout.view_route_record_card,null)
    }

    fun updateView(routeModel:RouteModel) {
        startPlaceTv.text = routeModel.startplace
        endPlaceTv.text = routeModel.endplace
        durationTv.text = routeModel.routeduration.toString()
        routeMiles.text = routeModel.vehicleid.toString()
        routeTime.text = routeModel.routestartdate
    }
}