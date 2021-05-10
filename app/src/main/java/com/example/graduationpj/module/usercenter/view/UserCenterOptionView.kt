package com.example.graduationpj.module.usercenter.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.graduationpj.R
import kotlinx.android.synthetic.main.view_user_center_option.view.*

class UserCenterOptionView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_user_center_option, this)
        val attrArray = context.obtainStyledAttributes(attributeSet, R.styleable.UserCenterOptionView)
        optionIv.setImageDrawable(attrArray.getDrawable(R.styleable.UserCenterOptionView_optionIcon))
        optionTv.text = attrArray.getText(R.styleable.UserCenterOptionView_optionText)
        attrArray.recycle()
    }
}