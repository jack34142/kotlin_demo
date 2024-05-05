package com.jyun_yi.kotlin_demo.ui.fragments.member

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jyun_yi.kotlin_demo.ui.BaseViewModel

class MemberViewModel: BaseViewModel() {
    private val _canPop = MutableLiveData(false)
    private val _num = MutableLiveData(0)
    val canPop: LiveData<Boolean> = _canPop
    val num: LiveData<Int> = _num

    fun updateCanPop(canPop: Boolean){
        _canPop.value = canPop
    }

    fun updateNum(num: Int){
        _num.value = num
    }
}