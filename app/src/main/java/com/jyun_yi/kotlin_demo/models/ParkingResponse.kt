package com.jyun_yi.kotlin_demo.models

import com.google.gson.annotations.SerializedName

data class ParkingResponse<T>(

    @field:SerializedName("data")
    val data: ParkingData<T>
)

data class ParkingData<T>(

    @field:SerializedName("UPDATETIME")
    val uPDATETIME: String,

    @field:SerializedName("park")
    val park: List<T>
)