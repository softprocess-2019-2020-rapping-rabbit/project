package com.example.foodformula.ApiConnection

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseWrapperRecipes<T> : Serializable {
    @SerializedName("recipes")
    val data: List<T>? = null
    @SerializedName("error")
    val error: Error? = null

}