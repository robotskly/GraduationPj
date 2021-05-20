package com.example.graduationpj.module.logindemo.base

import com.example.graduationpj.MyApplication
import com.example.graduationpj.support.utils.toast
import java.io.Serializable

/**
 * Created by Roman on 2021/1/26
 */
class BaseBean<T> :Serializable {

    var data: T? = null

    //	SUCCESS
    var resultCode : String? = null
    //	成功,系统处理正常
    var resultDesc: String? = null

    fun isSuccess(): Boolean {
        return "SUCCESS" == resultCode
    }

    fun showErrorMsg() {
        MyApplication.context.toast(resultDesc.toString())
    }
}