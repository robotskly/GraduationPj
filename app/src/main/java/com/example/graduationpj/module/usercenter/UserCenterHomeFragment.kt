package com.example.graduationpj.module.usercenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationpj.R
import com.example.graduationpj.module.usercenter.basicinfo.BasicInfoFragment
import com.example.graduationpj.module.usercenter.noterecord.NoteRecordFragment
import com.example.graduationpj.module.usercenter.routehistory.HistoryRouteFragment
import com.example.graduationpj.support.base.page.BaseTitleFragment
import kotlinx.android.synthetic.main.fragment_home_user_center.*

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
        traceTodayTv.text = "198.2"
        historyValueTv.text = "0"
        userNameTv.text = "1234"
    }
    private fun initAction(){
        historyTraceCl.setOnClickListener {
            start(HistoryRouteFragment.newInstance())
        }
        userInfoCl.setOnClickListener {
            start(BasicInfoFragment.newInstance())
        }
        vehicleInfoCl.setOnClickListener {

        }
        settingCl.setOnClickListener {

        }
        noteListCl.setOnClickListener {
            start(NoteRecordFragment.newInstance())
        }
        quitAccountTv.setOnClickListener {

        }
    }




}