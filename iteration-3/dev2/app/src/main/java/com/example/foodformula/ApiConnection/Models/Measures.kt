package com.example.foodformula.ApiConnection.Models

import com.google.gson.annotations.SerializedName

data class Measures(
    @SerializedName("us")
    var us: US? = null,
    @SerializedName("metric")
    var metric: Metric? = null

)