package com.example.graduationpj.module.logindemo.login

import com.example.graduationpj.module.logindemo.bean.Accounts
import com.example.graduationpj.module.logindemo.common.Constant
import com.example.graduationpj.module.logindemo.net.APIService
import com.example.graduationpj.module.logindemo.net.RetrofitManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Roman on 2021/1/26
 */
class LoginTask: LoginContract.Task {

    private var callBack: LoginContract.Presenter.OnLoginCallBack? = null


    override fun login(
        username: Int?,
        password: String?,
        onLoginCallBack: LoginContract.Presenter.OnLoginCallBack
    ) {
        callBack = onLoginCallBack
        val mLogin = RetrofitManager.getService(Constant.REQUEST_BASE_URL, APIService.Login::class.java)
        if (username != null && password!!.isNotEmpty()){
            val longCall = mLogin.toLogin(username, password)
            longCall.enqueue(object : Callback<Accounts> {

                override fun onFailure(call: Call<Accounts>, t: Throwable) {
                    callBack?.loginFail("登录失败")
                }

                override fun onResponse(call: Call<Accounts>, response: Response<Accounts>) {

                    var result: Accounts? = response.body()
                    if (result != null && 100 == result.code){
                        callBack?.loginSuccess()
                    }else{
                        callBack?.loginFail(result!!.msg)
                    }
                }
            })
        }
    }
}