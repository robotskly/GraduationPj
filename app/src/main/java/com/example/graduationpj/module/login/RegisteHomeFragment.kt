package com.example.graduationpj.module.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationpj.R
import com.example.graduationpj.support.base.page.BaseTitleFragment
import kotlinx.android.synthetic.main.fragment_login.*

class RegisterHomeFragment :BaseTitleFragment(){
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


}