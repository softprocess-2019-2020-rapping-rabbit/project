package com.example.foodformula.ApiConnection.Models

import com.google.gson.annotations.SerializedName

data class US(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("unitShort")
    val unitShort: String,
    @SerializedName("unitLong")
    var unitLong: String
)