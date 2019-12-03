package com.example.foodformula.ApiConnection

import com.example.foodformula.ApiConnection.Models.Recipe
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiFunctions {
    @GET("recipes/random")
    suspend fun getRandomRecipe(
    ): ResponseWrapperRecipes<Recipe>

    @GET("recipes/findByIngredients")
    suspend fun getRecipeByIngridient(@Query("ingredients") page: String): List<Recipe>

    @GET("recipes/{id}/information")
    suspend fun getRecipeById(@Path("id") id: Int): Recipe

}