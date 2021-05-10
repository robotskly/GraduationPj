package com.example.graduationpj.module.communication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.graduationpj.R
import android.view.ViewGroup
import com.example.graduationpj.support.base.page.BaseTitleFragment

class CommunicationHomeFragment :BaseTitleFragment(){
    companion object{
        fun newInstance():CommunicationHomeFragment{
            return CommunicationHomeFragment()
        }
    }
    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_communicate,container,false)
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