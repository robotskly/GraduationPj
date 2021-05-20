package com.wyq.logindemo.register

import com.example.graduationpj.module.logindemo.register.RegisterContract
import com.example.graduationpj.module.logindemo.bean.Accounts
import com.example.graduationpj.support.network.ConfigConst
import com.example.graduationpj.module.logindemo.net.APIService
import com.example.graduationpj.support.network.RetrofitManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Roman on 2021/1/26
 */
class RegisterTask : RegisterContract.Task {
    private var callback: RegisterContract.Presenter.OnRegisterCallBack? = null

    override fun goRegister(
        name: String,
        pwd: String,
        onRegisterCallBack: RegisterContract.Presenter.OnRegisterCallBack
    ) {
        callback = onRegisterCallBack

        val registerService =
            RetrofitManager.getService(ConfigConst.REQUEST_BASE_URL, APIService.Register::class.java)
        val registerCallBack = registerService.toRegister(name.toInt(), pwd)
        registerCallBack.enqueue(object : Callback<Accounts> {

            override fun onFailure(call: Call<Accounts>, t: Throwable) {
                callback?.registerFail("注册失败")
            }

            override fun onResponse(call: Call<Accounts>, response: Response<Accounts>) {
                var result: Accounts? = response!!.body()
                if (result != null && 100== result.code) {
                    callback?.registerSuccess(result)
                } else {
                    callback?.registerFail(result!!.msg)
                }
            }
        })
    }
}