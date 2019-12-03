package com.example.foodformula.ApiConnection.Models

import com.google.gson.annotations.SerializedName

data class ExtendedIngredients(
    @SerializedName("id")
    var id: Int,
    @SerializedName("aisle")
    var aisle: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("consitency")
    var consitency: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("original")
    var original: String? = null,
    @SerializedName("originalString")
    var originalString: String? = null,
    @SerializedName("originalName")
    var originalName: String? = null,
    @SerializedName("amount")
    var amount: Double,
    @SerializedName("unit")
    var unit: String? = null,
    @SerializedName("meta")
    var meta: List<String>? = null,
    @SerializedName("metaInformation")
    var metaInformation: List<String>? = null,
    @SerializedName("measures")
    var measures: Measures? = null

)
