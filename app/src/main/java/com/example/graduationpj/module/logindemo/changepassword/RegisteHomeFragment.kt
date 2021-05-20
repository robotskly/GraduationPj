package com.example.graduationpj.module.logindemo.changepassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.graduationpj.R
import com.example.graduationpj.module.HomeFragment
import com.example.graduationpj.module.logindemo.bean.Accounts
import com.example.graduationpj.module.logindemo.net.APIService
import com.example.graduationpj.support.base.page.BaseTitleFragment
import com.example.graduationpj.support.network.ConfigConst
import com.example.graduationpj.support.network.RetrofitManager
import kotlinx.android.synthetic.main.fragment_home_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterHomeFragment :BaseTitleFragment(){

    private var registerNameTmp:String?=null
    private var registerName:Int?=null
    private var registerPass:String?=null
    private var reconfirmPass:String?=null

    companion object{
        fun newInstance():RegisterHomeFragment{
            return RegisterHomeFragment()
        }
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_register,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initAction()
    }

    fun initData(){

    }
    fun initView(){

    }
    fun initAction(){
        registerTv.setOnClickListener {
            registerNameTmp = loginNameEt.text.toString()
            registerPass = passwordEt.text.toString()
            reconfirmPass = passwordConfirmEt.text.toString()
            checkFormat()
        }

    }
    private fun checkFormat(){
        if(registerName==null && registerPass == null && reconfirmPass== null){
            Toast.makeText(context,"密码或用户明不能为空",Toast.LENGTH_SHORT).show()
            return
        }
        for (index in 1..registerNameTmp?.length!!){
            if(registerNameTmp?.get(index-1)?.isDigit() == false){
                Toast.makeText(context,"注册账号智能包含数字",Toast.LENGTH_SHORT).show()
                return
            }
        }
        registerName = registerNameTmp?.toInt()

        if(!reconfirmPass.equals(registerPass)){
            Toast.makeText(context,"两次密码不同",Toast.LENGTH_SHORT).show()
            return
        }

        requestRegister{
            if(it){
                start(HomeFragment.newInstance())
            }else{
                Toast.makeText(context,"注册失败",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestRegister(callBack: (Boolean) -> Unit){

        val mRegister = RetrofitManager.getService(ConfigConst.REQUEST_BASE_URL,APIService.Register::class.java)

        val registerCall = mRegister.toRegister(username = registerName?:0,password = registerPass?:"")
        registerCall.enqueue(object :Callback<Accounts>{
            override fun onFailure(call: Call<Accounts>?, t: Throwable?) {
                callBack.invoke(false)
            }

            override fun onResponse(call: Call<Accounts>?, response: Response<Accounts>?) {
                var result: Accounts? = response?.body()
                if (result != null && 100 == result.code){
                    callBack.invoke(true)
                }else{
                    callBack.invoke(false)
                }
            }

        })


    }


}