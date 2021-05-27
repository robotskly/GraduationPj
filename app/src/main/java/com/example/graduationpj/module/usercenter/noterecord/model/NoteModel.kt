package com.example.graduationpj.module.usercenter.noterecord.model

import com.example.graduationpj.module.usercenter.routehistory.model.RouteModel
import java.io.Serializable
import java.util.*

data class NoteMessage(
    val data: MutableList<NoteModel>,
    val msg: String,
    val code: Int
):Serializable

data class NoteModel(
    var idnote:Int?,
    var notetitle:String?,
    var notecontent:String?=null,
    var notedate:String? =null,
    var userid:Int? = 0,
    var routeid:Int? = 0
):Serializable