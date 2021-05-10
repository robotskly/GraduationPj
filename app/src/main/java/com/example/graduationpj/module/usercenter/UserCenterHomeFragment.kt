package com.example.graduationpj.module.usercenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationpj.R
import com.example.graduationpj.support.base.page.BaseTitleFragment

class UserCenterHomeFragment :BaseTitleFragment(){
    companion object{
        fun newInstance():UserCenterHomeFragment{
            return UserCenterHomeFragment()
        }
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_user_center,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initAction()
    }
    private fun initData(){

    }
    private fun initView(){

    }
    private fun initAction(){

    }




}