package com.example.graduationpj.support.base.page.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.graduationpj.R
import com.example.graduationpj.support.utils.ConvertUtil.Companion.dp2px
import kotlinx.android.synthetic.main.view_base_title.view.*

class BaseTitleView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attributeSet, defStyle) {

    init {
        View.inflate(context, R.layout.view_base_title, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, dp2px(context,50f).toInt())
    }

    var backgroundRes: Int = R.color.white
        set(value) {
            field = value
            titleCl.background = getDrawable(context,value)
        }

    var titleColor: Int = R.color.color_333333
        set(value) {
            field = value
            navMidTv.setTextColor(resources.getColorStateList(value))
        }

    var title: CharSequence
        get() {
            return navMidTv.text.toString()
        }
        set(value) {
            navMidTv.text = value
        }

    var rightText: String
        get() {
            return navRightTv.text.toString()
        }
        set(value) {
            navRightTv.text = value
        }

    var leftText: String
        get() {
            return navBackTv.text.toString()
        }
        set(value) {
            setLeftDrawableResId(0)
            navBackTv.text = value
        }

    fun hideLeftBtn() {
        navBackTv.visibility = View.GONE
    }

    fun setRightDrawableResId(@DrawableRes resId: Int) {
        navRightTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, resId, 0)
    }

    fun setLeftDrawableResId(@DrawableRes resId: Int) {
        navBackTv.setCompoundDrawablesRelativeWithIntrinsicBounds(resId, 0, 0, 0)
    }

    fun setLeftClickListener(listener: () -> Unit) {
        navBackTv.setOnClickListener { listener() }
    }

    fun setRightClickListener(listener: () -> Unit) {
        navRightTv.setOnClickListener { listener() }
    }

    fun isRightBtnEnable(isEnable: Boolean) {
        navRightTv.isEnabled = isEnable
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    fun setRightTextColor(@ColorRes colorRes: Int) {
        navRightTv.setTextColor(resources.getColorStateList(colorRes))
    }

    fun setRightTextSize(float: Float) {
        navRightTv.textSize = float
    }
}