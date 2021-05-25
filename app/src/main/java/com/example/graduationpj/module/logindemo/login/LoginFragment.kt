package com.example.graduationpj.module.logindemo.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationpj.R
import com.example.graduationpj.module.HomeFragment
import com.example.graduationpj.module.logindemo.bean.User
import com.example.graduationpj.module.logindemo.changepassword.RegisterHomeFragment

import com.example.graduationpj.support.utils.SPUtil
import com.example.graduationpj.support.base.page.BaseTitleFragment
import com.example.graduationpj.support.login.LoginManager
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Roman on 2021/1/22
 */
class LoginFragment: BaseTitleFragment(), LoginContract.View {

    var loginPresenter: LoginContract.Presenter? = null

    companion object{
        fun newInstance():LoginFragment{
           return LoginFragment()
        }
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.activity_login,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initViews()
    }

    fun initData() {
        LoginPresenter(this)
    }

    fun initViews() {
        login.setOnClickListener {
            userToLogin()
        }
        register.setOnClickListener {
            start(RegisterHomeFragment.newInstance())
        }
    }

    private fun userToLogin() {
        if (checkUserInfo()){
            loginPresenter?.goLogin(getUserName(),getPwd())
        }
    }

    private fun checkUserInfo(): Boolean {
        if (TextUtils.isEmpty(getUserName().toString())){
            username.requestFocus()
            username.error = getString(R.string.username_cant_null)
            return false
        }
        if (TextUtils.isEmpty(getPwd())){
            password.requestFocus()
            password.error = getString(R.string.password_cant_null)
            return false
        }
        return true
    }


    override fun getUserName(): Int {
        return username.text.toString().toInt()
    }

    override fun getPwd(): String {
        return password.text.toString()
    }
    override fun loginSuccess(user:User) {
        SPUtil.saveLogin(true)
        start(HomeFragment.newInstance())
    }
    override fun loginFail(msg: String) {

    }
    override fun setPresenter(presenter: LoginContract.Presenter) {
        loginPresenter = presenter
    }
}