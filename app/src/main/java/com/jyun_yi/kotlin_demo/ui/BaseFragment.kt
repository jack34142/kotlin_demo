package com.jyun_yi.kotlin_demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.jyun_yi.kotlin_demo.ui.components.dialogs.MsgDialog

abstract class BaseFragment<T : ViewDataBinding, V: BaseViewModel> : Fragment(), IView, IReceive, IPass {
    protected lateinit var binding: T
    protected lateinit var viewModel: V

    abstract fun initViewModel(): V

    private var isInit = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewModel = initViewModel()
        viewModel.init(this)
        initView()
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(!isInit){
            initData()
            isInit = true
        }
    }

    override fun showMsg(msg: String){
        val dialog = MsgDialog(requireContext(), msg)
        dialog.show()
    }

    fun showToast(msg: String) {
        val toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
        toast.show()
    }

    // use after onStart
    override fun showLoading(){
        (requireActivity() as IView).showLoading()
    }

    // use after onStart
    override fun hideLoading(){
        (requireActivity() as IView).hideLoading()
    }

    override fun onApiError(error: String){
        showToast(error)
    }

    override fun passData(action: String, data: Any?, tabIndex: Int) {
        (requireActivity() as IPass).passData(action, data, tabIndex)
    }
}