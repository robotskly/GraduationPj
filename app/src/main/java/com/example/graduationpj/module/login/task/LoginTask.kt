package com.example.graduationpj.module.login.task

import com.example.graduationpj.module.login.model.User
import com.example.graduationpj.support.dep_network.ObjectLoader
import com.example.graduationpj.support.dep_network.RetrofitServiceManager
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class LoginLoader() : ObjectLoader() {

    private var loginTask:LoginTask?=null

    init {
        loginTask = RetrofitServiceManager.getInstance().create(LoginTask::class.java)
    }

    /**
     *
    public Observable<List<Movie>> getMovie(int start, int count){
    return observe(mMovieService.getTop250(start,count))
    .map(new Func1<MovieSubject, List<Movie>>() {
    @Override
    public List<Movie> call(MovieSubject movieSubject) {
    return movieSubject.subjects;
    }
    });
    }
     */
    fun login(iduser:Int,password:String):rx.Observable<User>{
        return observe(loginTask?.login(iduser,password))
            .map { t: User? ->  t}
    }


    /**
     *   //获取豆瓣Top250 榜单
    @GET("top250")
    Observable<MovieSubject> getTop250(@Query("start") int start, @Query("count")int count);

    @FormUrlEncoded
    @POST("/x3/weather")
    Observable<String> getWeather(@Field("cityId") String cityId, @Field("key") String key);

     */

    interface LoginTask {
        @POST("login")
        @FormUrlEncoded
        fun login(
            @Field("iduser") iduser: Int,
            @Field("password") password:String
        ): rx.Observable<User>
    }
}