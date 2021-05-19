package com.example.graduationpj.module.logindemo.base

/**
 * Created by Roman on 2021/1/18
 */
interface BaseView<T> {
    fun setPresenter(presenter: T)
}