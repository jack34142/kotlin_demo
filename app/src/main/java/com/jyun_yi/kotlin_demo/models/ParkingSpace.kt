package com.jyun_yi.kotlin_demo.models

import com.google.gson.annotations.SerializedName

data class ParkingSpace(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("availablecar")
	val availablecar: Int,  //小型車位剩餘

	@field:SerializedName("availablemotor")
	val availablemotor: Int, //機車位剩餘

	@field:SerializedName("availablebus")
	val availablebus: Int, //巴士車位剩餘

	@field:SerializedName("ChargeStation")
	val chargeStation: ChargeStation?  //充電樁狀態
)

data class ScoketStatusListItem(

	@field:SerializedName("spot_abrv")
	val spotAbrv: String,  //充電樁id

	@field:SerializedName("spot_status")
	val spotStatus: String  //充電樁狀態
)

data class ChargeStation(

	@field:SerializedName("scoketStatusList")
	val scoketStatusList: List<ScoketStatusListItem>
)
