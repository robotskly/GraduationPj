package com.example.graduationpj.module.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amap.api.services.core.PoiItem
import com.example.graduationpj.MyApplication
import com.example.graduationpj.R

class AddressAdapter(dl: List<PoiItem>, getAddress:GetAddress): RecyclerView.Adapter<AddressAdapter.MyViewHolder>() {

    var data:List<PoiItem>?=null
    var getAddress:GetAddress?=null
    var context:Context?=null
    init {
        this.data = dl
        this.getAddress = getAddress
    }
    class MyViewHolder(itemView: View?, var context: Context) : RecyclerView.ViewHolder(itemView!!) {
        var addressTv = itemView?.findViewById<TextView>(R.id.addressStringTv)
    }

    interface GetAddress{
        fun getAddress(index:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(MyApplication.context).inflate(R.layout.view_string_list_item,parent,false),MyApplication.context)
    }

    override fun getItemCount(): Int {
       return data?.size?:0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.addressTv?.text= data?.get(position)?.adName+data?.get((position))?.businessArea+data?.get((position))?.distance
        this.context = holder.context
        holder.itemView.setOnClickListener{
            getAddress?.getAddress(index = position)
        }
    }

}