package com.example.graduationpj.module.login.task

import com.example.graduationpj.module.login.model.User
import retrofit2.Call
import retrofit2.http.*


interface MovieService {
    @GET("top250")
    fun getTop250(@Query("strat") start:Int,@Query("count")count:Int):Call<User>

    /**
     * 使用POST 请求方式时，只需要更改方法定义的标签，
     * 用@POST 标签，参数标签用 @Field 或者@Body或者FieldMap，注意：使用POST 方式时注意2点，
     * 1，必须加上 @FormUrlEncoded标签，否则会抛异常。
     * 2，使用POST方式时，必须要有参数，否则会抛异常,
     */
    @FormUrlEncoded
    @POST("top250")
    fun postTop250(@Field("start") start: Int, @Field("count") count: Int): Call<User?>?
}