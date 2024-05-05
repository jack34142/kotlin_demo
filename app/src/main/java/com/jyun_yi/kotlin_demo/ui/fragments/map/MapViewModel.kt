package com.jyun_yi.kotlin_demo.ui.fragments.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jyun_yi.kotlin_demo.models.ParkingItem
import com.jyun_yi.kotlin_demo.http.GovApi
import com.jyun_yi.kotlin_demo.http.MyCallback
import com.jyun_yi.kotlin_demo.models.ParkingLocation
import com.jyun_yi.kotlin_demo.models.ParkingLot
import com.jyun_yi.kotlin_demo.models.ParkingResponse
import com.jyun_yi.kotlin_demo.models.ParkingSpace
import com.jyun_yi.kotlin_demo.ui.BaseViewModel

class MapViewModel: BaseViewModel() {
    private val _searching = MutableLiveData(false)
    val searching: LiveData<Boolean> = _searching

    private val parkingLots = mutableMapOf<String, ParkingLot>()
    private val parkingSpaces = mutableMapOf<String, ParkingSpace>()
    val parkingLocations = mutableListOf<ParkingLocation>()

    fun updateSearching(){
        _searching.value = !_searching.value!!
    }
    // ------------------------------
    fun clearData(){
        parkingLots.clear()
        parkingSpaces.clear()
        parkingLocations.clear()
    }

    fun getParkingLot(onComplete: (parkingLot: Map<String, ParkingLot>?) -> Unit){
        GovApi.getInstance().getParkingLot(object: MyCallback<ParkingResponse<ParkingLot>>() {
            override fun onSuccess(data: ParkingResponse<ParkingLot>){
                parkingLocations.clear()
                data.data.park.forEach { item ->
                    val id = item.id
                    parkingLots[id] = item
                    item.entranceCoord.entrancecoordInfo?.forEach {
                        val address = it.address.ifEmpty { item.address }
                        val lat = it.lat.toDouble()
                        val lng = it.lng.toDouble()
                        if( lat != 0.0 && lng != 0.0 ){
                            parkingLocations.add(ParkingLocation(id, lat, lng, item.name,item.area + address ))
                        }
                    }
                }
                onComplete(parkingLots)
            }
            override fun onFailed(e: String) {
                onApiError(e)
                onComplete(null)
            }
        })
    }

    fun getParkingSpace(onComplete: (parkingLot: Map<String, ParkingSpace>?) -> Unit){
        GovApi.getInstance().getParkingSpace(object: MyCallback<ParkingResponse<ParkingSpace>>() {
            override fun onSuccess(data: ParkingResponse<ParkingSpace>){
                data.data.park.forEach{
                    parkingSpaces[it.id] = it
                }
                onComplete(parkingSpaces)
            }
            override fun onFailed(e: String) {
                onApiError(e)
                onComplete(null)
            }
        })
    }

    fun getParkingItems(): Map<String, ParkingItem>? {
        if(parkingLots.isEmpty() || parkingSpaces.isEmpty()){
            return null
        }
        val data = mutableMapOf<String, ParkingItem>()
        parkingLocations.forEach {
            val id = it.id
            val parkingLot = this.parkingLots[id]!!
            val parkingSpace = this.parkingSpaces[id] ?: ParkingSpace(id,-9,-9,-9, null)
            data[id] = ParkingItem(parkingLot, parkingSpace)
        }
        return data;
    }
}