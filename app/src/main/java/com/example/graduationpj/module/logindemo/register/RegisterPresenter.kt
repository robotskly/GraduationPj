package com.example.graduationpj.module.logindemo.register

import com.example.graduationpj.module.logindemo.bean.Accounts
import com.wyq.logindemo.register.RegisterTask
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Roman on 2021/1/26
 */
class RegisterPresenter(val view: RegisterContract.View): RegisterContract.Presenter {
    private var registerTask: RegisterTask? = null
    init {
        view.setPresenter(this)
        registerTask = RegisterTask()
    }
    override fun register(name: String, pwd: String) {
        doAsync {
            registerTask?.goRegister(name,pwd,object : RegisterContract.Presenter.OnRegisterCallBack{

                override fun registerSuccess(userAccount: Accounts) {
                    uiThread {
                        view.registerSuccess(userAccount)
                    }
                }

                override fun registerFail(message: String) {
                    uiThread {
                        view.registerFail(message)
                    }
                }

            })
        }
    }

    override fun start() {

    }
}