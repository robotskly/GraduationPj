package com.example.graduationpj.module.usercenter.noterecord.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationpj.MyApplication
import com.example.graduationpj.R
import com.example.graduationpj.module.usercenter.noterecord.model.NoteModel
import com.example.graduationpj.support.utils.ConvertUtil

class NoteAdapter(data:MutableList<NoteModel>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var data:List<NoteModel>?=null

    init{
        this.data = data
    }
    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var noteTitleTv = itemView.findViewById<TextView>(R.id.noteTitleTv)
        var noteDateTv = itemView.findViewById<TextView>(R.id.noteDateTv)
        var noteContentTv = itemView.findViewById<TextView>(R.id.noteContentTv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(MyApplication.context)
                .inflate(R.layout.view_note_card
                    ,parent
                    ,false)
        )
    }

    override fun getItemCount(): Int {
        return data?.count()?:0
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.noteContentTv.text = data?.get(position)?.notecontent ?: ""
        holder.noteTitleTv.text = data?.get(position)?.notetitle?:""
        holder.noteDateTv.text  = data?.get(position)?.notedate?:""
    }
}