package com.example.foodformula.UI

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodformula.ApiConnection.Models.Recipe
import com.example.foodformula.ApiConnection.Status
import com.example.foodformula.MainActivity
import com.example.foodformula.R
import com.example.foodformula.UI.Adapters.RecipesAdapter
import com.example.foodformula.ViewModels.ActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.search_layout.*

open class SearchFragment : Fragment() {

    private lateinit var activityViewModel: ActivityViewModel
    var progressDialog: ProgressDialog? = null
    var adapter: RecipesAdapter? = null

    companion object {

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }

        const val RECIPE_KEY = "RECIPE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_layout, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonTwoClickListener()
        adapter = RecipesAdapter(
            context!!,
            arrayListOf()
        )
        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        recycler_view.layoutManager = llm
    }

    private fun buttonTwoClickListener() {
        search_text.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                v.text?.let { it1 ->
                    activityViewModel.getRecipeByIngridient(ingredient = it1.toString()) {
                        when (it!!.status) {
                            Status.LOADING -> viewTwoLoading()
                            Status.SUCCESS -> viewTwoSuccess(it.data)
                            Status.ERROR -> viewTwoError(it.error)
                        }
                    }
                }
                hideKeyboard()
                true
            } else false


        }
    }

    private fun viewTwoError(error: Error?) {
        progressDialog!!.dismiss()
        Snackbar.make(view!!, error?.localizedMessage.toString(), Snackbar.LENGTH_SHORT).show()

    }

    private fun viewTwoSuccess(data: List<Recipe?>?) {
        progressDialog!!.dismiss()
        if (data.isNullOrEmpty()) {
            Snackbar.make(view!!, "Not found", Snackbar.LENGTH_SHORT).show()
        } else {
            val llm = LinearLayoutManager(context)
            llm.orientation = LinearLayoutManager.VERTICAL
            recycler_view.layoutManager = llm
            adapter = data.let {
                RecipesAdapter(
                    context!!,
                    it
                )
            }
            adapter!!.setOnItemClickListener(object : RecipesAdapter.ItemClickListener {
                override fun onRecyclerItemClick(view: View, position: Int) {
                    goToRecipeFragment(data[position]!!.id)
                }

            })
            recycler_view.adapter = data.let { adapter }
        }


    }

    private fun viewTwoLoading() {
        progressDialog = ProgressDialog(context)
        progressDialog!!.setTitle("Loading")
        progressDialog!!.setMessage("Wait while loading...")
        progressDialog!!.setCancelable(false) // disable dismiss by tapping outside of the dialog

        progressDialog!!.show()

    }

    protected fun hideKeyboard() {
        if (activity != null && activity!!.window != null
            && activity!!.window.currentFocus != null
        ) {
            val imm = activity!!
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(
                activity!!.window
                    .currentFocus!!.windowToken, 0
            )
        }
    }

    private fun goToRecipeFragment(id: Int) {
        val currentActivity = activity as MainActivity
        val bundle = Bundle()
        bundle.putInt(RECIPE_KEY, id)
        currentActivity.replaceFragment(RecipeFragment.newInstance(), null, bundle)
    }

}