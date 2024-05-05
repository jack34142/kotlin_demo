package com.jyun_yi.kotlin_demo.http

import com.jyun_yi.kotlin_demo.models.ParkingResponse
import com.jyun_yi.kotlin_demo.models.ParkingLot
import com.jyun_yi.kotlin_demo.models.ParkingSpace

class GovApi private constructor():
        BaseHttp<GovService>("https://tcgbusfs.blob.core.windows.net", GovService::class.java) {
    companion object {
        @Volatile
        private var instance: GovApi? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: GovApi().also { instance = it }
        }
    }

    fun getParkingLot(myCallback: MyCallback<ParkingResponse<ParkingLot>>){
        service.getParkingLot().enqueue(myCallback)
    }

    fun getParkingSpace(myCallback: MyCallback<ParkingResponse<ParkingSpace>>){
        service.getParkingSpace().enqueue(myCallback)
    }
}