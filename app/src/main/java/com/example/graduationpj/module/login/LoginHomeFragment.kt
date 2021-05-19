package com.example.graduationpj.module.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationpj.R
import com.example.graduationpj.module.login.task.LoginLoader
import com.example.graduationpj.support.base.page.BaseTitleFragment
import com.example.graduationpj.support.network.Fault
import com.example.graduationpj.support.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_login.*


class LoginHomeFragment : BaseTitleFragment() {

    var loginLoader:LoginLoader?=null

    var loginName:Int?=null
    var loginPassword:String?=null

    companion object {
        fun newInsatnce(): LoginHomeFragment {
            return LoginHomeFragment()
        }
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initAction()
    }
    fun initData(){
        loginLoader = LoginLoader()
    }
    fun initView(){

    }
    fun initAction(){

        loginBtTv.setOnClickListener {
            loginName = loginNameEt.text.toString().toInt()
            loginPassword = passwordEt.text.toString()
            requestLogin(loginName?:0,loginPassword?:"")
        }

        registerTv.setOnClickListener {
            start(RegisterHomeFragment.newInstance())
        }

        changePasswordTv.setOnClickListener {
            start(ChangePasswordFragment.newInstance())
        }
    }

    /**
     * private void getMovieList(){
    mMovieLoader.getMovie(0,10).subscribe(new Action1<List<Movie>>() {
    @Override
    public void call(List<Movie> movies) {
    mMovieAdapter.setMovies(movies);
    mMovieAdapter.notifyDataSetChanged();
    }
    }, new Action1<Throwable>() {
    @Override
    public void call(Throwable throwable) {
    Log.e("TAG","error message:"+throwable.getMessage());
    if(throwable instanceof Fault){
    Fault fault = (Fault) throwable;
    if(fault.getErrorCode() == 404){
    //错误处理
    }else if(fault.getErrorCode() == 500){
    //错误处理
    }else if(fault.getErrorCode() == 501){
    //错误处理
    }
    }
    }
    });

    }
     */

    fun requestLogin(iduser:Int,password:String){
        loginLoader?.login(iduser,password)?.subscribe(
            {
                //成功处理
                if(it != null){
                    ToastUtil.show(context,"登录成功")
                }else{
                    ToastUtil.show(context,"账号密码错误")
                }
            },
            {
                //失败处理
                it as Fault
                when(it.errorCode){
                    500->{
                        ToastUtil.show(context,"500")
                    }
                    501->{
                        ToastUtil.show(context,"501")
                    }
                    404->{
                        ToastUtil.show(context,"404")
                    }
                }
            }
        )
    }


}