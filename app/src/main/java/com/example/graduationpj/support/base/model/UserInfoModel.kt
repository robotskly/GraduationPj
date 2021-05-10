package com.example.graduationpj.support.base.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserInfoModel(
    @SerializedName("") val userId: String? = null,
    @SerializedName("") val userName: String? = null,
    @SerializedName("") val userAge: Int? = null,
    @SerializedName("") val userSex: SexEnum? = null,
    @SerializedName("") val userHomeAddress: AddressModel? = null,
    @SerializedName("") val userCompanyAddress: AddressModel? = null,
    @SerializedName("") val vehicleInfo: VehicleInfoModel? = null,
    @SerializedName("") val userTel: String? = null,
    @SerializedName("") val userPass: String? = null,
) : BaseModel() {}

enum class SexEnum(val sex: Int) {
    @SerializedName("1")
    FeMale(1),

    @SerializedName("2")
    Male(2)
}

data class AddressModel(
    @SerializedName("") val province: String? = null,
    @SerializedName("") val provinceCode: String? = null,
    @SerializedName("") val city: String? = null,
    @SerializedName("") val cityCode: String? = null,
    @SerializedName("") val street: String? = null,
    @SerializedName("") val location: String? = null,
) : BaseModel()

data class VehicleInfoModel(
    @SerializedName("") val vehicleId: String? = null,
    @SerializedName("") val vehicleName: String? = null,
    @SerializedName("") val vehicleCode: String? = null,
    @SerializedName("") val vehicleBrand: String? = null,
    @SerializedName("") val vehicleValue: Int? = null,
    @SerializedName("") val vehicleDate: Date? = null,
) : BaseModel()