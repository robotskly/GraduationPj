package com.example.graduationpj.module.usercenter.basicinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.graduationpj.R
import com.example.graduationpj.module.logindemo.net.APIService
import com.example.graduationpj.module.usercenter.basicinfo.model.Address
import com.example.graduationpj.module.usercenter.basicinfo.model.AddressMessage
import com.example.graduationpj.module.usercenter.basicinfo.model.Vehicle
import com.example.graduationpj.module.usercenter.basicinfo.model.VehicleMessage
import com.example.graduationpj.support.base.page.BaseTitleFragment
import com.example.graduationpj.support.login.LoginManager
import com.example.graduationpj.support.network.ConfigConst
import com.example.graduationpj.support.network.RetrofitManager
import kotlinx.android.synthetic.main.fragment_user_info_show.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.LogManager

class BasicInfoFragment:BaseTitleFragment() {
    val user = LoginManager.newInstance().user
    private lateinit var homeAddress :Address
    private lateinit var workAddress :Address
    private lateinit var vehicleList:MutableList<Vehicle>
    companion object{
        fun newInstance():BasicInfoFragment{
            return BasicInfoFragment().apply {

            }
        }
    }
    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_user_info_show,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initAction()
    }

    fun initData(){
//        requestHomeAddress(user?.iduser?:1) {home->
//            if(home){
//                requestWorkAddress(user?.iduser?:1){work->
//                    if(work){
//                        requestVehicle(user?.iduser?:1){vehicle->
//                            if(vehicle){
//                                initView()
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }

    fun initView(){
        userIdLl.updateView(user?.iduser.toString())
        userHomeAddrLl.updateView(user?.iduser.toString())
        userNameLl.updateView(user?.iduser.toString())
        userSexLl.updateView(user?.iduser.toString())
        userWorkAddrLl.updateView(workAddress?.city.toString()+workAddress?.location.toString())
        userHomeAddrLl.updateView(homeAddress?.city.toString()+homeAddress?.location.toString())
        userVehicle1Ll.updateView(vehicleList?.get(0)?.vechielname.toString())
        userVehicle2Ll.updateView(vehicleList?.get(1)?.vechielname.toString())
    }

    fun initAction(){

    }

    private fun requestHomeAddress(idUser:Int,callback:(Boolean)->Unit){
        val homeAddrTask = RetrofitManager.getService(ConfigConst.REQUEST_BASE_URL, APIService.GetAddressById::class.java)
        val homeAddrTaskCall  = homeAddrTask.toGetAddr(idUser)
        homeAddrTaskCall.enqueue(object: Callback<AddressMessage> {
            override fun onFailure(call: Call<AddressMessage>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<AddressMessage>?, response: Response<AddressMessage>?) {
                if(response?.body()?.data!=null){
                    homeAddress = response?.body()?.data!!
                    callback.invoke(true)
                }else{
                    Toast.makeText(context,"网络错误",Toast.LENGTH_SHORT).show()
                    callback.invoke(false)
                }
            }
        })
    }
    private fun requestWorkAddress(idUser:Int,callback:(Boolean)->Unit){
        val workAddrTask = RetrofitManager.getService(ConfigConst.REQUEST_BASE_URL, APIService.GetAddressById::class.java)
        val workAddrTaskCall  = workAddrTask.toGetAddr(idUser)
        workAddrTaskCall.enqueue(object: Callback<AddressMessage> {
            override fun onFailure(call: Call<AddressMessage>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<AddressMessage>?, response: Response<AddressMessage>?) {
                if(response?.body()?.data!=null){
                    workAddress = response?.body()?.data!!
                    callback.invoke(true)
                }else{
                    Toast.makeText(context,"网络错误", Toast.LENGTH_SHORT).show()
                    callback.invoke(false)
                }
            }
        })
    }
    private fun requestVehicle(idUser:Int,callback:(Boolean)->Unit){
        val vehicleTask = RetrofitManager.getService(ConfigConst.REQUEST_BASE_URL, APIService.GetVehicleById::class.java)
        val vehicleTaskCall  = vehicleTask.toGetVehicleList(idUser)
        vehicleTaskCall.enqueue(object: Callback<VehicleMessage> {
            override fun onFailure(call: Call<VehicleMessage>?, t: Throwable?) {
            }
            override fun onResponse(call: Call<VehicleMessage>?, response: Response<VehicleMessage>?) {
                if(response?.body()?.data!=null){
                    vehicleList =  response?.body()?.data!!
                    callback.invoke(true)
                }else{
                    Toast.makeText(context,"网络错误",Toast.LENGTH_SHORT).show()
                    callback.invoke(false)
                }
            }
        })
    }

}