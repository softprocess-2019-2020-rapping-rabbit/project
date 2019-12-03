package com.example.foodformula.ApiConnection.Models

import com.google.gson.annotations.SerializedName

data class AnalyzedInstructions(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("steps")
    var steps: List<Steps>? = null
)

