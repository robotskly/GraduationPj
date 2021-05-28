package com.example.graduationpj.module.usercenter.noterecord

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.example.graduationpj.R
import com.example.graduationpj.module.logindemo.net.APIService
import com.example.graduationpj.module.usercenter.noterecord.model.NoteMessage
import com.example.graduationpj.module.usercenter.noterecord.model.NoteModel
import com.example.graduationpj.module.usercenter.noterecord.view.NoteAdapter
import com.example.graduationpj.support.base.page.BaseTitleFragment
import com.example.graduationpj.support.login.LoginManager
import com.example.graduationpj.support.network.ConfigConst
import com.example.graduationpj.support.network.RetrofitManager
import com.example.graduationpj.support.utils.ConvertUtil
import kotlinx.android.synthetic.main.fragment_history_route.datePickerTv
import kotlinx.android.synthetic.main.fragment_note_record.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class NoteRecordFragment :BaseTitleFragment(){

    private lateinit var pvTime: TimePickerView
    private var noteList:MutableList<NoteModel> ?= mutableListOf()
    private var dateSelect: Date? = Date(System.currentTimeMillis())
        set(value) {
            field = value
            datePickerTv.text = ConvertUtil.date2StringYMD(dateSelect!!)
            Toast.makeText(context, ConvertUtil.date2StringYMD(value!!), Toast.LENGTH_SHORT).show()
            requestRecordByDate {
                if(it){
                    historyNoteRv.layoutManager = LinearLayoutManager(context)

                    historyNoteRv.adapter = NoteAdapter(noteList!!)
                }else{
                    Toast.makeText(context,"网络错误",Toast.LENGTH_SHORT).show()
                }
            }
        }
    private var titleContent:String ?=null

    companion object{
        fun newInstance():NoteRecordFragment{
            return NoteRecordFragment()
        }
    }

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_note_record,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initAction()
    }

    fun initData(){

    }

    fun initView(){
        datePickerTv.text = ConvertUtil.date2StringYMD(dateSelect!!)
        initTimePicker()
        requestRecordByDate {
            if(it){
                historyNoteRv.layoutManager = LinearLayoutManager(context)
                historyNoteRv.adapter = NoteAdapter(noteList!!)
            }else{
                Toast.makeText(context,"网络错误",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun initAction(){
        datePickerTv.setOnClickListener {
            pvTime.show()
        }
        selectTv.setOnClickListener {
            titleContent = titleSelectEt.text.toString()
            requestRecordByTitle {
                if(it){
                    historyNoteRv.layoutManager = LinearLayoutManager(context)
                    historyNoteRv.adapter = NoteAdapter(noteList!!)
                }else{
                    Toast.makeText(context,"网络错误",Toast.LENGTH_SHORT).show()
                }
            }
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

    private fun requestRecordByDate(callBack:(Boolean)->Unit){
        val noteTask = RetrofitManager.getService(ConfigConst.REQUEST_BASE_URL,APIService.GetNoteListByDate::class.java)
        val noteTaskCallback = noteTask.toGetNoteList(
            LoginManager.newInstance().user?.iduser?:0,
            ConvertUtil.date2StringYMD(dateSelect?:Date(System.currentTimeMillis()))
        )

        noteTaskCallback.enqueue(object : Callback<NoteMessage>{
            override fun onFailure(call: Call<NoteMessage>?, t: Throwable?) {
                Toast.makeText(context,"网络错误",Toast.LENGTH_SHORT).show()
                callBack.invoke(false)
            }
            override fun onResponse(call: Call<NoteMessage>?, response: Response<NoteMessage>?) {
                noteList?.clear()
                noteList = response?.body()?.data
                callBack.invoke(true)
            }
        })
    }
    private fun requestRecordByTitle(callBack:(Boolean)->Unit){
        val noteTask = RetrofitManager.getService(ConfigConst.REQUEST_BASE_URL,APIService.GetNoteListByTitle::class.java)
        val noteTaskCallback = noteTask.toGetNoteList(
            LoginManager.newInstance().user?.iduser?:0,
            ConvertUtil.date2StringYMD(dateSelect?:Date(System.currentTimeMillis())),
            titleContent?:""
        )

        noteTaskCallback.enqueue(object : Callback<NoteMessage>{
            override fun onFailure(call: Call<NoteMessage>?, t: Throwable?) {
                Toast.makeText(context,"网络错误",Toast.LENGTH_SHORT).show()
                callBack.invoke(false)
            }
            override fun onResponse(call: Call<NoteMessage>?, response: Response<NoteMessage>?) {
                noteList?.clear()
                noteList = response?.body()?.data

                callBack.invoke(true)
            }
        })
    }

}