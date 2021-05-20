package com.example.graduationpj.module.logindemo.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationpj.R
import com.example.graduationpj.module.logindemo.bean.Accounts
import com.example.graduationpj.support.base.page.BaseTitleFragment
import kotlinx.android.synthetic.main.activity_register.*

class RegisterFragment : BaseTitleFragment(), RegisterContract.View {
    private var registerPresenter: RegisterContract.Presenter? = null

    companion object{
        fun startActivity(ctx: Context){
            val i = Intent(ctx, RegisterFragment::class.java)
            ctx.startActivity(i)
        }
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       return inflater.inflate(R.layout.activity_register,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initViews()
        initAction()
    }


    fun initData() {
        RegisterPresenter(this)
    }

    fun initViews() {

    }
    fun initAction(){
        btn_register.setOnClickListener {
            goRegister()
        }
    }

    private fun goRegister() {
        if (checkRegister()){
            registerPresenter!!.register(getUserName(),getPwd())
        }
    }

    private fun checkRegister(): Boolean{
        if (TextUtils.isEmpty(getUserName())){
            input_name.requestFocus()
            input_name.error = getString(R.string.username_cant_null)
            return false
        }
        if (TextUtils.isEmpty(getPwd())){
            input_pwd.requestFocus()
            input_pwd.error = getString(R.string.password_cant_null)
            return false
        }
        if (getPwd() != getConfirmPwd()){
            input_confirm_pwd.requestFocus()
            input_confirm_pwd.error = getString(R.string.pwd_not_confirm)
            return false
        }
        if (TextUtils.isEmpty(getEmail())){
            input_email.requestFocus()
            input_email.error = getString(R.string.email_cant_null)
            return false
        }
        return true
    }

    override fun getUserName(): String {
        return input_name.text.toString()
    }

    override fun getPwd(): String {
        return input_pwd.text.toString()
    }

    override fun getConfirmPwd(): String {
        return input_confirm_pwd.text.toString()
    }

    override fun getEmail(): String {
        return input_email.text.toString()
    }

    override fun registerSuccess(userAccount: Accounts) {
        //toast("注册成功!${userAccount.result.username}${userAccount.result.password}")
        //Todo
    }

    override fun registerFail(msg: String) {

    }

    override fun setPresenter(presenter: RegisterContract.Presenter) {
        registerPresenter = presenter
    }
}