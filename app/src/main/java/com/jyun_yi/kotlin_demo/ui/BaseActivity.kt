package com.jyun_yi.kotlin_demo.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.jyun_yi.kotlin_demo.ui.components.dialogs.MsgDialog

abstract class BaseActivity<T: ViewDataBinding, V: BaseViewModel> : AppCompatActivity(), IView, IPass{
    protected lateinit var binding: T
    protected lateinit var viewModel: V

    abstract fun initViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = initViewModel()
        viewModel.init(this)
        initView()
        binding.lifecycleOwner = this
        initData()
    }

    override fun onApiError(error: String){
        showToast(error)
    }

    fun showToast(msg: String) {
        val toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show()
    }

    override fun showMsg(msg: String){
        val dialog = MsgDialog(this, msg)
        dialog.show()
    }
}