package com.jyun_yi.kotlin_demo.ui.fragments.member

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import com.jyun_yi.kotlin_demo.R
import com.jyun_yi.kotlin_demo.databinding.FragmentMemberBinding
import com.jyun_yi.kotlin_demo.ui.BaseFragment
import com.jyun_yi.kotlin_demo.ui.activitys.main.MainActivity

class MemberFragment : BaseFragment<FragmentMemberBinding, MemberViewModel>() {
    override val layoutId = R.layout.fragment_member

    override fun initView() {
        if( arguments != null){
            val bundle = requireArguments()
            viewModel.updateCanPop( bundle.getBoolean("canPop",false) )
            binding.memberAppBar.btnBack.setOnClickListener {
                passData(MainActivity.BACK_PRESS, null, 0)
            }
            viewModel.updateNum( bundle.getInt("num", 0) )
        }
        binding.memberBtnPop.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("canPop", false)
            bundle.putInt("num", 0)
            passData(MainActivity.ROUTE_CHANGE, bundle, R.id.route_c_member)
        }
        binding.memberBtnPop2.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("canPop", true)
            bundle.putInt("num", viewModel.num.value!! + 1 )
            passData(MainActivity.ROUTE_CHANGE, bundle, R.id.route_c_member)
        }
        binding.memberBtnDialog.setOnClickListener {
            showMsg("測試訊息")
        }
        binding.memberBtnLoading.setOnClickListener {
            showLoading()
            Handler(Looper.getMainLooper()).postDelayed({
                hideLoading()
            }, 2000) //millis
        }
    }

    override fun initViewModel(): MemberViewModel {
        val viewModel by viewModels<MemberViewModel>()
        binding.viewModel = viewModel
        return viewModel
    }

    override fun initData() {

    }

    override fun receiveData(action: String, data: Any?) {

    }
}