package com.example.foodformula.UI

import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodformula.ApiConnection.Models.Recipe
import com.example.foodformula.ApiConnection.Status
import com.example.foodformula.R
import com.example.foodformula.UI.Adapters.IngridientAdapter
import com.example.foodformula.ViewModels.ActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.recipe_layout.*

class RecipeFragment : Fragment() {

    companion object {
        fun newInstance(): RecipeFragment = RecipeFragment()
        const val RECIPE_FRAGMENT_KEY = "RECIPE_FRAGMENT_KEY"
    }

    private lateinit var activityViewModel: ActivityViewModel
    var progressDialog: ProgressDialog? = null
    var ingridientAdapter: IngridientAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        observeGetPosts()
        if (arguments != null) {
            if (arguments!!.containsKey(SearchFragment.RECIPE_KEY)) activityViewModel.getRecipeById(
                arguments!!.getInt(SearchFragment.RECIPE_KEY)
            )
            else activityViewModel.getRandomRecipe()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.recipe_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonOneClickListener()
        ingridientAdapter = IngridientAdapter(arrayListOf())
        val llm3 = LinearLayoutManager(context)
        llm3.orientation = LinearLayoutManager.VERTICAL
        extented_ingridients.layoutManager = llm3
        extented_ingridients.isNestedScrollingEnabled = false
    }

    private fun observeGetPosts() = activityViewModel.simpleLiveData.observe(this, Observer {
        when (it.status) {
            Status.LOADING -> viewOneLoading()
            Status.SUCCESS -> viewOneSuccess(it.data)
            Status.ERROR -> viewOneError(it.error)
        }
    })


    private fun buttonOneClickListener() = random.setOnClickListener {
        activityViewModel.getRandomRecipe()
    }

    private fun viewOneLoading() {
        progressDialog = ProgressDialog(context)
        progressDialog!!.setTitle("Loading")
        progressDialog!!.setMessage("Wait while loading...")
        progressDialog!!.setCancelable(false) // disable dismiss by tapping outside of the dialog

        progressDialog!!.show()

    }

    private fun viewOneSuccess(data: Recipe?) = if (data != null) {
        progressDialog!!.dismiss()
        setupView(data)
    } else Snackbar.make(view!!, "Not Found", Snackbar.LENGTH_SHORT).show()

    private fun viewOneError(error: Error?) = if (error != null) {
        progressDialog?.dismiss()
        Snackbar.make(view!!, error?.localizedMessage.toString(), Snackbar.LENGTH_SHORT).show()
    } else Snackbar.make(view!!, error?.localizedMessage.toString(), Snackbar.LENGTH_SHORT).show()

    private fun setupView(recipe: Recipe) {
        ingridientAdapter = recipe.extendedIngredients?.let {
            IngridientAdapter(it)
        }
        recipe.run {
            name.text = "$title"
            info.text = "$aggregateLikes"
            ready_in_minutes.text = "Time for preparing in minutes: $readyInMinutes"
            if (extendedIngredients!!.isNotEmpty()) {
                extented_ingridients.adapter = ingridientAdapter
            }

            val uri = Uri.parse(image)
            image_recipe.setImageURI(uri)
            instruction_text.text = instructions
        }

    }
}