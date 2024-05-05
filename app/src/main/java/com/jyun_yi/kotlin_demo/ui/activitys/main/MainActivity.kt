package com.jyun_yi.kotlin_demo.ui.activitys.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.jyun_yi.kotlin_demo.R
import com.jyun_yi.kotlin_demo.databinding.ActivityMainBinding
import com.jyun_yi.kotlin_demo.ui.BaseActivity
import com.jyun_yi.kotlin_demo.ui.IReceive

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutId = R.layout.activity_main

    companion object {
        private const val TAG = "MainActivity"
        const val TAB_CHANGE = TAG + "TabChange"
        const val ROUTE_CHANGE = TAG + "RouteChange"
        const val BACK_PRESS = TAG + "BackPress"
    }

    private var lastBackPress: Long = 0
    private val tabs = listOf(R.id.tab_a, R.id.tab_b, R.id.tab_c)
    private val containers = listOf(R.id.main_A_container, R.id.main_B_container, R.id.main_C_container)
    private val navs = listOf(R.id.nav_a, R.id.nav_b, R.id.nav_c)

    override fun initView() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() = onBackPress()
        })
        binding.mainBottomNavigation.setOnItemSelectedListener {
            onTabClick( tabs.indexOf(it.itemId) )
        }
    }

    override fun initViewModel(): MainViewModel {
        val viewModel by viewModels<MainViewModel>()
        binding.viewModel = viewModel
        return viewModel
    }

    override fun initData() {

    }

    override fun showLoading() {
        viewModel.showLoading()
    }

    override fun hideLoading() {
        viewModel.hideLoading()
    }

    override fun passData(action: String, data: Any?, tabIndex: Int) {
        when(action){
            TAB_CHANGE -> onTabClick(tabIndex)
            BACK_PRESS -> onBackPress()
            ROUTE_CHANGE -> {  //要在正確的 tab 使用
                val bundle: Bundle
                var canPop = true
                if(data != null){
                    bundle = data as Bundle
                    if( bundle.containsKey("canPop") ){
                        canPop = bundle.getBoolean("canPop")
                    }
                }else{
                    bundle = Bundle()
                }
                bundle.putBoolean("canPop", canPop)
                val options = NavOptions.Builder()
                if(!canPop){
                    options.setPopUpTo(navs[viewModel.selectedTab.value!!], true)
                }
                getNavController().navigate(tabIndex, bundle, options.build())
            }
            else -> {
                val navHostFragment = supportFragmentManager.findFragmentById( containers[tabIndex] ) as NavHostFragment
                val fragment = navHostFragment.childFragmentManager.fragments[0] as IReceive
                fragment.receiveData(action, data)
            }
        }
    }

    private fun onBackPress(){
        if ( viewModel.loadingCount.value!! > 0){
            return
        }
        if( !getNavController().navigateUp() ){
            if(System.currentTimeMillis() - lastBackPress > 1500){
                showToast(resources.getString(R.string.press2exit))
                lastBackPress = System.currentTimeMillis()
            }else{
                finish()
            }
        }
    }

    private fun onTabClick(index: Int): Boolean {
        val isChange = viewModel.tabChange(index)
        if (isChange){
            binding.mainBottomNavigation.menu.get(index).setChecked(true)
        }
        return isChange
    }

    private fun getNavController(): NavController{
        return findNavController( containers[viewModel.selectedTab.value!!] )
    }
}