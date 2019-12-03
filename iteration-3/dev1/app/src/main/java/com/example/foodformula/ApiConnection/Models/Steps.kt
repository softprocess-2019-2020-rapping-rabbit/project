package com.example.foodformula.ApiConnection.Models

import com.google.gson.annotations.SerializedName

data class Steps(
    @SerializedName("number")
    var number: Int = 0,
    @SerializedName("step")
    var step: String? = null,
    @SerializedName("ingredients")
    var ingredients: List<Ingredient>? = null,
    @SerializedName("equipment")
    var equipment: List<Equipment>? = null
)