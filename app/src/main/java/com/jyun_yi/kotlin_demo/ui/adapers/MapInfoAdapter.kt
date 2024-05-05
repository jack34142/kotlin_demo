package com.jyun_yi.kotlin_demo.ui.adapers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.jyun_yi.kotlin_demo.databinding.ItemMapBinding
import com.jyun_yi.kotlin_demo.models.ParkingItem

class MapInfoAdapter(context: Context): GoogleMap.InfoWindowAdapter {
    val binding = ItemMapBinding.inflate(LayoutInflater.from(context))

    var items: Map<String, ParkingItem>? = null

    override fun getInfoContents(marker: Marker): View? {
        val id = marker.title!!
        if(items != null){
            val item = items!![id]!!
            binding.parkingLot = item.parkingLot
            binding.parkingSpace = item.parkingSpace
        }
        binding.executePendingBindings()
        return binding.root
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }

}
