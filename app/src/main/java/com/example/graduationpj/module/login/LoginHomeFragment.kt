package com.example.graduationpj.module.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationpj.R
import com.example.graduationpj.support.base.page.BaseTitleFragment

class LoginHomeFragment : BaseTitleFragment(){
    companion object{
        fun newInsatnce():LoginHomeFragment{
            return LoginHomeFragment()
        }
    }
    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login,container,false)
    }




}