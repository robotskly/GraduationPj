package com.example.graduationpj.module.usercenter.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationpj.support.base.page.BaseTitleFragment

class SettingFragment:BaseTitleFragment() {

    companion object{
        fun newInstance():SettingFragment{
            return SettingFragment()
        }
    }
    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        TODO("Not yet implemented")
    }
}