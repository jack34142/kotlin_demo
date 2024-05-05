package com.jyun_yi.kotlin_demo.ui

import androidx.lifecycle.ViewModel

interface IBinding<V: ViewModel> {
    var viewModel: V
}

interface IView {
    val layoutId: Int
    fun initView()
    fun initData()
    fun showMsg(msg: String)
    fun showLoading()
    fun hideLoading()
    fun onApiError(error: String)
}

interface IReceive {
    fun receiveData(action: String, data: Any?)
}

interface IPass {
    fun passData(action: String, data: Any?, tabIndex: Int)
}