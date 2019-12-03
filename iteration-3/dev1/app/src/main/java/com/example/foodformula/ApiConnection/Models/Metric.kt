package com.example.foodformula.ApiConnection.Models

import com.google.gson.annotations.SerializedName

data class Metric(
    @SerializedName("amount")
    var amount: Double,
    @SerializedName("unitShort")
    var unitShort: String?,
    @SerializedName("unitLong")
    var unitLong: String?

)
