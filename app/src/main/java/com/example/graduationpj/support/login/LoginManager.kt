package com.example.graduationpj.support.login

import androidx.core.view.isVisible
import com.example.graduationpj.module.logindemo.bean.User
import com.example.graduationpj.module.logindemo.net.APIService
import com.example.graduationpj.module.usercenter.basicinfo.model.Address
import com.example.graduationpj.module.usercenter.basicinfo.model.AddressMessage
import com.example.graduationpj.module.usercenter.basicinfo.model.Vehicle
import com.example.graduationpj.module.usercenter.basicinfo.model.VehicleMessage
import com.example.graduationpj.module.usercenter.routehistory.model.RouteMessage
import com.example.graduationpj.support.network.ConfigConst
import com.example.graduationpj.support.network.RetrofitManager
import com.example.graduationpj.support.utils.ConvertUtil
import kotlinx.android.synthetic.main.fragment_history_route.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class LoginManager {
    var user :User?=null


    companion object{
        fun newInstance():LoginManager{
            return LoginManager()
        }
    }

}