package com.jyun_yi.kotlin_demo.ui.fragments.search

import com.jyun_yi.kotlin_demo.models.ParkingItem
import com.jyun_yi.kotlin_demo.ui.BaseViewModel

class SearchViewModel: BaseViewModel() {
    var parkingItems: Map<String, ParkingItem>? = null
}