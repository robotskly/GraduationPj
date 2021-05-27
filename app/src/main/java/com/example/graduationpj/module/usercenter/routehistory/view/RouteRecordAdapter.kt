package com.example.graduationpj.module.usercenter.routehistory.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationpj.MyApplication
import com.example.graduationpj.R
import com.example.graduationpj.module.usercenter.routehistory.model.RouteModel

class RouteRecordAdapter(data: List<RouteModel>?) :
    RecyclerView.Adapter<RouteRecordAdapter.RouteViewHolder>() {

    var data: List<RouteModel>? = null
    var context: Context? = null

    init {
        this.data = data
        this.context = MyApplication.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        return RouteViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.view_route_record_card
                , parent
                , false
            )
        )
    }

    override fun getItemCount(): Int {
        return data?.count() ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        holder.routeStart.text = data?.get(position)?.startplace ?: ""
        holder.routeEnd.text = data?.get(position)?.endplace ?: ""
        holder.routeDuration.text = data?.get(position)?.routeduration.toString() +"min"
        holder.routeStartTime.text = data?.get(position)?.routestartdate
        holder.routeMiles.text = data?.get(position)?.vehicleid.toString() +"公里"
    }

    class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var routeStart = itemView.findViewById<TextView>(R.id.startPlaceTv)
        var routeEnd = itemView.findViewById<TextView>(R.id.endPlaceTv)
        var routeDuration = itemView.findViewById<TextView>(R.id.routeDuration)
        var routeStartTime = itemView.findViewById<TextView>(R.id.routeTime)
        var routeMiles = itemView.findViewById<TextView>(R.id.routeMiles)
    }


}