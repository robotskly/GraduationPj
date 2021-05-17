package com.example.graduationpj.module.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationpj.R
import com.example.graduationpj.module.login.model.User
import com.example.graduationpj.module.login.task.MovieService
import com.example.graduationpj.support.base.page.BaseTitleFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginHomeFragment : BaseTitleFragment() {
    companion object {
        fun newInsatnce(): LoginHomeFragment {
            return LoginHomeFragment()
        }
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    fun onRequest() {

        val BaseUrl = "https://api.douban.com/v2/movie/"
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val movieService = retrofit.create(MovieService::class.java)
        val call: Call<User> = movieService.getTop250(0,20)

        call.enqueue(object :Callback<User>{
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                TODO("Not yet implemented")
            }

        })

    }


}