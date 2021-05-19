package com.example.graduationpj

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.graduationpj.module.HomeFragment
import com.example.graduationpj.module.login.LoginHomeFragment
import com.example.graduationpj.module.logindemo.login.LoginFragment
import me.yokeyword.fragmentation.SupportActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity:SupportActivity(){
    //动态申请权限
    //先定义一String数组存所有要添加的权限名称
    //动态权限
    private val permissions:ArrayList<String> = arrayListOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.CHANGE_NETWORK_STATE,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.WRITE_SETTINGS,
    )
    private val permissionNeed:Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadRootFragment(R.id.containerRoot, LoginFragment.newInstance())
        //loadRootFragment(R.id.containerRoot,HomeFragment.newInstance())
        askPermission()
        //println(Test.sHA1(this))
        //start(HomeFragment.newInstance())
    }
    private fun askPermission(){
        permissions.forEachIndexed { index, value ->
            if(ContextCompat.checkSelfPermission(this, value)!=PackageManager.PERMISSION_GRANTED){
                permissionNeed.plus(permissions[index])
            }
        }
        if(permissionNeed.isNullOrEmpty())
            return
        ActivityCompat.requestPermissions(this, permissionNeed,1)
    }



}