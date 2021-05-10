package com.example.graduationpj.module.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.graduationpj.R
import android.view.ViewGroup
import com.example.graduationpj.support.base.page.BaseTitleFragment

class NoteHomeFragment :BaseTitleFragment(){
    companion object{
        fun newInstance():NoteHomeFragment{
            return NoteHomeFragment()
        }
    }
    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_note,container,false)
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