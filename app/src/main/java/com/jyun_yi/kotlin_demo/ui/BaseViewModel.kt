package com.jyun_yi.kotlin_demo.ui

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    lateinit var onApiError: (String) -> Unit

    fun init(context: IView){
        onApiError = {
            context.onApiError(it)
        }
    }
}