package com.example.graduationpj.module.usercenter.basicinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.graduationpj.R
import com.example.graduationpj.support.base.page.BaseTitleFragment
import kotlinx.android.synthetic.main.fragment_basic_info.*

class BasicInfoFragment:BaseTitleFragment() {
    var vehicleCount = 1
    companion object{
        fun newInstance():BasicInfoFragment{
            return BasicInfoFragment()
        }
    }
    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_basic_info,container,false)
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
        locationIv.setOnClickListener {

        }
        addVehicleTv.setOnClickListener {
            vehicleCount +=1
            when(vehicleCount){
                1->{
                    vehicle1Cl.isVisible = true
                    addVehicleTv.isEnabled = true
                }
                2->{
                    vehicle2Cl.isVisible = true
                    addVehicleTv.isEnabled = true
                }
                3->{
                    vehicle3Cl.isVisible = true
                    addVehicleTv.isEnabled = false
                }
                4->{
                    Toast.makeText(context,"最多只能添加三辆车",Toast.LENGTH_SHORT).show()
                    addVehicleTv.isEnabled = false
                }
            }

        }
        confirmTv.setOnClickListener {
            commitInfo()
        }
    }

    private fun commitInfo(){


    }
}