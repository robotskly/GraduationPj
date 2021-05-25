package com.example.graduationpj.module.logindemo.login

import com.example.graduationpj.module.logindemo.base.BasePresenter
import com.example.graduationpj.module.logindemo.base.BaseView
import com.example.graduationpj.module.logindemo.bean.User

/**
 * Created by Roman on 2021/1/26
 */
interface LoginContract {

    interface View: BaseView<Presenter> {
        /**
         * 获取用户名字
         */
        fun getUserName(): Int

        /**
         * 获取用户密码
         */
        fun getPwd(): String

        /**
         * 登陆成功
         */
        fun loginSuccess(user:User)

        /**
         * 登陆失败
         */
        fun loginFail(msg: String)
    }

    interface Presenter: BasePresenter {
        /**
         * 开始登录
         */
        fun goLogin(username:Int,password:String)

        interface OnLoginCallBack{
            fun loginSuccess(user:User)
            fun loginFail(message: String)
        }
    }

    interface Task {
        fun login(
            username: Int?,
            password: String?,
            onLoginCallBack: Presenter.OnLoginCallBack
        )
    }

}