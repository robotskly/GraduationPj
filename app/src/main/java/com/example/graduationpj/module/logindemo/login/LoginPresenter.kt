package com.example.graduationpj.module.logindemo.login

import com.example.graduationpj.module.logindemo.bean.User
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Roman on 2021/1/26
 */
class LoginPresenter(val view: LoginContract.View): LoginContract.Presenter, LoginContract.Presenter.OnLoginCallBack {
    var mTask: LoginContract.Task? = null
    init {
        view.setPresenter(this)
        mTask = LoginTask()
    }
    override fun goLogin(username: Int, password: String) {
        doAsync {
            mTask?.login(username,password,object : LoginContract.Presenter.OnLoginCallBack{

                override fun loginSuccess(user:User) {
                    uiThread {
                        view.loginSuccess(user)
                    }
                }

                override fun loginFail(message: String) {
                    uiThread {
                        view.loginFail(message)
                    }
                }

            })
        }
    }

    override fun start() {

    }

    override fun loginSuccess(user:User) {
        view.loginSuccess(user)
    }

    override fun loginFail(message: String) {
        view.loginFail(message)
    }
}