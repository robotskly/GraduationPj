package com.example.graduationpj.support.network

import android.util.Log
import okhttp3.*
import java.io.IOException

class NetWorkTask {

    companion object {
        val client: OkHttpClient? = OkHttpClient()
        fun newTask(): NetWorkTask {
            return NetWorkTask()
        }
    }

    fun requestInfo(url: String, taskCallBack: TaskCallBack) {
        val request: Request = Request.Builder()
            .get()
            .url(url)
            .build()

        val call: Call? = client?.newCall(request)
        call?.enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                println("————失败了$e")
                taskCallBack.onFail(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val stA = response.body()?.string()
                println("————成功 $stA")
                taskCallBack.onSuccess(response.body()?.string()?:"")
            }
               // val stA = response.body()?.string()

//                runOnUiThread(){
//                    // 回到主线程刷新UI吧
//                    textView22!!.text = stA
//                }

        })
    }
}