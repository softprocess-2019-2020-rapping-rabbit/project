package com.example.foodformula.ApiConnection.Models

import com.google.gson.annotations.SerializedName

data class Equipment(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("image")
    var image: String? = null

)