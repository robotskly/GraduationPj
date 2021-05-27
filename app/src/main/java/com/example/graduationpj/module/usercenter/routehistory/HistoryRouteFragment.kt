package com.example.graduationpj.module.usercenter.routehistory

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.example.graduationpj.R
import com.example.graduationpj.module.logindemo.net.APIService
import com.example.graduationpj.module.usercenter.routehistory.model.RouteMessage
import com.example.graduationpj.module.usercenter.routehistory.model.RouteModel
import com.example.graduationpj.module.usercenter.routehistory.view.RouteRecordAdapter
import com.example.graduationpj.support.base.page.BaseTitleFragment
import com.example.graduationpj.support.login.LoginManager
import com.example.graduationpj.support.network.BaseCallBack
import com.example.graduationpj.support.network.ConfigConst
import com.example.graduationpj.support.network.RetrofitManager
import com.example.graduationpj.support.utils.ConvertUtil
import kotlinx.android.synthetic.main.fragment_history_route.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class HistoryRouteFragment : BaseTitleFragment() {
    private lateinit var pvTime: TimePickerView
    private var dateSelect: Date? = Date(System.currentTimeMillis())
        set(value) {
            field = value
            datePickerTv.text = getTime(value!!)
            Toast.makeText(context, getTime(value), Toast.LENGTH_SHORT).show()
            requestRecord {
                if(it){
                    historyTraceRv.layoutManager = LinearLayoutManager(context)
                    historyTraceRv.adapter = RouteRecordAdapter(routeList)
                }else{
                    Toast.makeText(context,"网络错误",Toast.LENGTH_SHORT).show()
                }
            }
        }
    private var routeList:MutableList<RouteModel>? = mutableListOf()


    companion object {
        fun newInstance(): HistoryRouteFragment {
            return HistoryRouteFragment()
        }
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_history_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initAction()
    }

    fun initData() {

    }

    fun initView() {
        datePickerTv.text = ConvertUtil.date2StringYMD(dateSelect!!)
        initTimePicker()
        requestRecord{
            if(it){
                historyTraceRv.layoutManager = LinearLayoutManager(context)
                historyTraceRv.adapter = RouteRecordAdapter(routeList)
            }else{
                Toast.makeText(context,"网络错误",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun initAction() {
        datePickerTv.setOnClickListener {
            pvTime.show()
        }
    }

    private fun initTimePicker() {//Dialog 模式下，在底部弹出
        pvTime = TimePickerBuilder(context, object : OnTimeSelectListener {
            override fun onTimeSelect(date: Date?, v: View?) {
                dateSelect = date
            }
        }).setTimeSelectChangeListener {
            fun onTimeSelectChanged(date: Date) {
                dateSelect = date
            }
        }.setType(booleanArrayOf(true, true, true, false, false, false))
            .isDialog(true)
            .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
            .build()

        val mDialog: Dialog = pvTime.dialog
        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            Gravity.BOTTOM
        )
        params.leftMargin = 0;
        params.rightMargin = 0;
        pvTime.dialogContainerLayout.layoutParams = params
        val dialogWindow = mDialog.window
        if (dialogWindow != null) {
            dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
            dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime(date: Date): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return simpleDateFormat.format(date)
    }

    private fun requestRecord(callBack: (Boolean) -> Unit) {
        val routeTask = RetrofitManager.getService(ConfigConst.REQUEST_BASE_URL,APIService.GetRouteList::class.java)
        val routeTaskCall  = routeTask.toGetRoute(LoginManager.user?.iduser?:1,ConvertUtil.date2StringYMDHMS(dateSelect?:Date(System.currentTimeMillis())))

        routeTaskCall.enqueue(object: Callback<RouteMessage>{
            override fun onFailure(call: Call<RouteMessage>?, t: Throwable?) {
                callBack.invoke(false)
            }
            override fun onResponse(call: Call<RouteMessage>?, response: Response<RouteMessage>?) {
                if(response?.body()?.data!=null){
                    noTraceTv.isVisible = false
                    routeList?.clear()
                    routeList = response.body()?.data
                }else{
                    noTraceTv.isVisible = true
                }
                callBack.invoke(true)
            }
        })
    }
}

