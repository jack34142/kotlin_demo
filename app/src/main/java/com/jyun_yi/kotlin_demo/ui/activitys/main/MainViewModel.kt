package com.jyun_yi.kotlin_demo.ui.activitys.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jyun_yi.kotlin_demo.ui.BaseViewModel

class MainViewModel: BaseViewModel() {
    private val _selectedTab = MutableLiveData(0)
    private val _loadingCount = MutableLiveData(0)

    val selectedTab: LiveData<Int> = _selectedTab
    val loadingCount: LiveData<Int> = _loadingCount

    fun tabChange(index: Int): Boolean {
        return if(index >= 0 && _selectedTab.value != index){
            _selectedTab.value = index
            true
        }else{
            false
        }
    }

    fun showLoading() {
        _loadingCount.value = _loadingCount.value!! + 1
    }

    fun hideLoading() {
        if(_loadingCount.value!! > 0){
            _loadingCount.value = _loadingCount.value!! - 1
        }
    }
}