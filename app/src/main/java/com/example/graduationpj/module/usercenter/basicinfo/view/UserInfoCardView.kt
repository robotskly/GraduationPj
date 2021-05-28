package com.example.graduationpj.module.usercenter.basicinfo.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.graduationpj.R
import kotlinx.android.synthetic.main.fragment_home_user_center.*
import kotlinx.android.synthetic.main.view_user_center_option.view.*
import kotlinx.android.synthetic.main.view_user_info_card.view.*

class UserInfoCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defAttr: Int = 0
) : LinearLayout(context, attributeSet, defAttr) {

    init {
        View.inflate(context, R.layout.view_user_info_card, this)
        val attrArray =
            context.obtainStyledAttributes(attributeSet, R.styleable.UserInfoCardView)
        titleTv.text = attrArray.getText(R.styleable.UserInfoCardView_userInfoTitle)
        attrArray.recycle()
    }
    fun updateView(content:String){
        contentTv.text = content
    }


}