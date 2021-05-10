package com.example.graduationpj.module.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationpj.R
import com.example.graduationpj.support.base.page.BaseTitleFragment

class ServiceHomeFragment: BaseTitleFragment() {
    companion object{
        fun newInstance():ServiceHomeFragment{
            return ServiceHomeFragment()
        }
    }
    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_service,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //this.mTitleView.title = context?.getString(R.string.smart_service)?:""
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