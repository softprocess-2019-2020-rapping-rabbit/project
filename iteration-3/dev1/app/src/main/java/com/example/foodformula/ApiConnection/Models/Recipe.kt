package com.example.foodformula.ApiConnection.Models

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("vegetarian")
    var vegetarian: Boolean = false,
    @SerializedName("vegan")
    var vegan: Boolean = false,
    @SerializedName("glutenFree")
    var glutenFree: Boolean = false,
    @SerializedName("dairyFree")
    var dairyFree: Boolean = false,
    @SerializedName("veryHealthy")
    var veryHealthy: Boolean = false,
    @SerializedName("cheap")
    var cheap: Boolean = false,
    @SerializedName("veryPopular")
    var veryPopular: Boolean = false,
    @SerializedName("sustainable")
    var sustainable: Boolean = false,
    @SerializedName("weightWatcherSmartPoints")
    var weightWatcherSmartPoints: Int = 0,
    @SerializedName("gaps")
    var gaps: String? = null,
    @SerializedName("lowFodmap")
    var lowFodmap: Boolean = false,
    @SerializedName("ketogenic")
    var ketogenic: Boolean = false,
    @SerializedName("whole30")
    var whole30: Boolean = false,
    @SerializedName("sourceUrl")
    var sourceUrl: String? = null,
    @SerializedName("spoonacularSourceUrl")
    var spoonacularSourceUrl: String? = null,
    @SerializedName("aggregateLikes")
    var aggregateLikes: Int = 0,
    @SerializedName("spoonacularScore")
    var spoonacularScore: Int = 0,
    @SerializedName("healthScore")
    var healthScore: Int = 0,
    @SerializedName("creditsText")
    var creditsText: String? = null,
    @SerializedName("sourceName")
    var sourceName: String? = null,
    @SerializedName("pricePerServing")
    var pricePerServing: Double = 0.0,
    @SerializedName("extendedIngredients")
    var extendedIngredients: List<ExtendedIngredients>? = null,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("readyInMinutes")
    var readyInMinutes: Int = 0,
    @SerializedName("servings")
    var servings: Int = 0,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("imageType")
    var imageType: String? = null,
    @SerializedName("cuisines")
    var cuisines: List<String?>? = null,
    @SerializedName("dishTypes")
    var dishTypes: List<String?>? = null,
    @SerializedName("diets")
    var diets: List<String?>? = null,
    @SerializedName("occasions")
    var occasions: List<String?>? = null,
    @SerializedName("instructions")
    var instructions: String? = null,
    @SerializedName("analyzedInstructions")
    var analyzedInstructions: List<AnalyzedInstructions>? = null
)
