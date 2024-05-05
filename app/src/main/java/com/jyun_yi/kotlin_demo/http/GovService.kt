package com.jyun_yi.kotlin_demo.http

import com.jyun_yi.kotlin_demo.models.ParkingResponse
import com.jyun_yi.kotlin_demo.models.ParkingLot
import com.jyun_yi.kotlin_demo.models.ParkingSpace
import retrofit2.Call
import retrofit2.http.GET

interface GovService {
    @GET("/blobtcmsv/TCMSV_alldesc.json")
    fun getParkingLot(): Call<ParkingResponse<ParkingLot>>

    @GET("/blobtcmsv/TCMSV_allavailable.json")
    fun getParkingSpace(): Call<ParkingResponse<ParkingSpace>>
}