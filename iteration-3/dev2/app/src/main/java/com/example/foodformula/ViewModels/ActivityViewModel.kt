package com.example.foodformula.ViewModels

import androidx.lifecycle.MutableLiveData
import com.example.foodformula.ApiConnection.Event
import com.example.foodformula.ApiConnection.Models.Recipe

class ActivityViewModel : BaseViewModel() {

    val simpleLiveData = MutableLiveData<Event<Recipe>>()

    fun getRandomRecipe() = requestWithLiveData(simpleLiveData) {
        api.getRandomRecipe()
    }

    fun getRecipeByIngridient(ingredient: String, callback: (data: Event<List<Recipe?>>?) -> Unit) =
        requestWithCallback({
            api.getRecipeByIngridient(
                ingredient
            )
        }) {
            callback(it)
        }

    fun getRecipeById(id: Int) = requestWithLiveDataWithoutWraper(simpleLiveData) {
        api.getRecipeById(id)
    }
}