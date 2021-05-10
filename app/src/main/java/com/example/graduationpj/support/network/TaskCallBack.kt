package com.example.graduationpj.support.network

import java.lang.Exception

interface TaskCallBack {
    open fun onSuccess(res:String)

    open fun onFail(e:Exception)
}