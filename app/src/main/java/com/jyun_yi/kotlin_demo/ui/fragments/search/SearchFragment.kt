package com.jyun_yi.kotlin_demo.ui.fragments.search

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.jyun_yi.kotlin_demo.models.ParkingItem
import com.jyun_yi.kotlin_demo.R
import com.jyun_yi.kotlin_demo.databinding.FragmentSearchBinding
import com.jyun_yi.kotlin_demo.ui.BaseFragment
import com.jyun_yi.kotlin_demo.ui.adapers.ParkingInfoAdapter

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutId = R.layout.fragment_search

    private val parkingLotAdapter = ParkingInfoAdapter()

    companion object {
        private const val TAG = "SearchFragment"
        const val UPDATE_PARKING_ITEMS = TAG + "UpdateParkingItems"
        const val SHOW_INFO = TAG + "ShowInfo"
    }

    override fun initViewModel(): SearchViewModel {
        val viewModel by viewModels<SearchViewModel>()
        binding.viewModel = viewModel
        return viewModel
    }

    override fun initView() {
        binding.searchList.adapter = parkingLotAdapter
        binding.saerchEt.addTextChangedListener {
            val text = it.toString()
            if(viewModel.parkingItems != null){
                val items = mutableMapOf<String, ParkingItem>()
                viewModel.parkingItems!!.keys.forEach { id ->
                    val item = viewModel.parkingItems!![id]!!
                    if ( (item.parkingLot.name + item.parkingLot.area + item.parkingLot.address).contains(text) ){
                        items[id] = item
                    }
                }
                parkingLotAdapter.submitList(items)
            }
        }
    }

    override fun initData() {

    }

    override fun receiveData(action: String, data: Any?) {
        when(action){
            UPDATE_PARKING_ITEMS -> {
                val items = data as Map<String, ParkingItem>
                viewModel.parkingItems = items
                parkingLotAdapter.submitList(items)
            }
            SHOW_INFO -> {
                val id = data as String
//                binding.searchList.scrollToPosition( parkingLotAdapter.getPositionById(id) )
                if( viewModel.parkingItems != null){
                    val name = viewModel.parkingItems!![id]!!.parkingLot.name
                    binding.saerchEt.setText(name)
                }
            }
        }
    }
}