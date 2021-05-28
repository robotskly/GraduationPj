package com.example.graduationpj.module.logindemo.net

import com.example.graduationpj.module.logindemo.bean.Accounts
import com.example.graduationpj.module.usercenter.basicinfo.model.AddressMessage
import com.example.graduationpj.module.usercenter.basicinfo.model.VehicleMessage
import com.example.graduationpj.module.usercenter.noterecord.model.NoteMessage
import com.example.graduationpj.module.usercenter.routehistory.model.RouteMessage
import com.example.graduationpj.module.usercenter.routehistory.model.RouteModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.sql.Date
import java.util.*

/**
 * Created by Roman on 2021/1/24
 */
class APIService {

    /**
     * 注册
     */
    interface Register {
        @FormUrlEncoded
        @POST("register")
        fun toRegister(
            @Field("username") username: Int,
            @Field("password") password: String
        ): Call<Accounts>
    }

    /**
     * 登录
     */
    interface Login {
        @FormUrlEncoded
        @POST("login")
        fun toLogin(
            @Field("username") username: Int,
            @Field("password") password: String
        ):Call<Accounts>
    }

    interface GetRouteList{
        @FormUrlEncoded
        @POST("route")
        fun toGetRoute(
            @Field("username") username:Int,
            @Field("date") date:String
        ):Call<RouteMessage>
    }

    interface GetNoteListByDate{
        @FormUrlEncoded
        @POST("getNoteByDate")
        fun toGetNoteList(
            @Field("userId") userId:Int,
            @Field("date") date:String
        ):Call<NoteMessage>
    }

    interface GetNoteListByTitle{
        @FormUrlEncoded
        @POST("getNoteByTitle")
        fun toGetNoteList(
            @Field("userId") userId:Int,
            @Field("date") date:String,
            @Field("content")content:String
        ):Call<NoteMessage>
    }

    interface GetAddressById{
        @FormUrlEncoded
        @POST("address")
        fun toGetAddr(
            @Field("userId") userId:Int,
        ):Call<AddressMessage>
    }

    interface GetVehicleById{
        @FormUrlEncoded
        @POST("vehicle")
        fun toGetVehicleList(
            @Field("userId") userId:Int,
        ):Call<VehicleMessage>
    }
}
