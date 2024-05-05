package com.jyun_yi.kotlin_demo.models

import com.google.gson.annotations.SerializedName

data class ParkingLot(

	@field:SerializedName("area")
	val area: String,  //鄉鎮市區

	@field:SerializedName("summary")
	val summary: String,  //停車位概覽

	@field:SerializedName("tw97y")
	val tw97y: String,

	@field:SerializedName("address")
	val address: String,  //路段地址

	@field:SerializedName("payex")
	val payex: String,  //票價概覽

	@field:SerializedName("totalmotor")
	val totalmotor: Int,  //機車位

	@field:SerializedName("totalcar")
	val totalcar: Int,  //小型車位

	@field:SerializedName("totalbus")
	val totalbus: Int,  //巴士車位

	@field:SerializedName("type")
	val type: String,  // 1:動態停車場(指可取得目前剩餘車位數) 2:靜態停車場

	@field:SerializedName("serviceTime")
	val serviceTime: String,  //服務時間

	@field:SerializedName("tw97x")
	val tw97x: String,

	@field:SerializedName("EntranceCoord")
	val entranceCoord: EntranceCoord,  //入口座標

	@field:SerializedName("FareInfo")
	val fareInfo: FareInfo,  //售票資訊

	@field:SerializedName("name")
	val name: String,  //停車場名稱

	@field:SerializedName("totalbike")
	val totalbike: Int,  //腳踏車位

	@field:SerializedName("tel")
	val tel: String,  //市話

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("totallargemotor")
	val totallargemotor: String,  // 大型車位

	@field:SerializedName("Phone_Charge")
	val phoneCharge: String,  //手機充電座

	@field:SerializedName("AED_Equipment")
	val aEDEquipment: String,  // AED設備

	@field:SerializedName("Pregnancy_First")
	val pregnancyFirst: String,  // 孕婦、育有六歲以下兒童停車位

	@field:SerializedName("Handicap_First")
	val handicapFirst: String,  // 身心障礙停車位

	@field:SerializedName("Child_Pickup_Area")
	val childPickupArea: String, //小孩接送區

	@field:SerializedName("Taxi_OneHR_Free")
	val taxiOneHRFree: String,  //計程車停車免費1小時

	@field:SerializedName("type2")
	val type2: String,  //1:停管處經營 2:非停管處經營

	@field:SerializedName("CellSignal_Enhancement")
	val cellSignalEnhancement: String,  // ???

	@field:SerializedName("ChargingStation")
	val chargingStation: String,  //充電樁數量

	@field:SerializedName("Accessibility_Elevator")
	val accessibilityElevator: String  //無障礙電梯
)

data class EntranceCoord(

	@field:SerializedName("EntrancecoordInfo")
	val entrancecoordInfo: List<EntrancecoordInfoItem>?
)

data class EntrancecoordInfoItem(

	@field:SerializedName("Address")
	val address: String,  //路段地址

	@field:SerializedName("Ycod")
	val lng: String,  //經度

	@field:SerializedName("Xcod")
	val lat: String  //緯度
)

data class FareInfo(

	@field:SerializedName("Holiday")
	val holiday: List<FareItem>,  //假日

	@field:SerializedName("WorkingDay")
	val workingDay: List<FareItem>  //平日
)

data class FareItem(

	@field:SerializedName("Period")
	val period: String,  //時段

	@field:SerializedName("Fare")
	val fare: String  //票價
)
