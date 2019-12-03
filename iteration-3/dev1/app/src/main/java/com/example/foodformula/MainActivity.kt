package com.example.foodformula

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.foodformula.UI.RecipeFragment
import com.example.foodformula.UI.SearchFragment
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        addFragment(RecipeFragment.newInstance(), RecipeFragment.RECIPE_FRAGMENT_KEY)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_search -> openSearchFragment()
        else -> super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val currentFragment =
            supportFragmentManager.findFragmentByTag(SearchFragment.RECIPE_KEY) as SearchFragment?
        if (currentFragment != null && currentFragment.isVisible) {
            replaceFragment(
                RecipeFragment.newInstance(),
                RecipeFragment.RECIPE_FRAGMENT_KEY,
                Bundle()
            )
        } else finish()
    }

    private fun openSearchFragment(): Boolean =
        replaceFragment(SearchFragment.newInstance(), SearchFragment.RECIPE_KEY, Bundle())

    private fun addFragment(f: Fragment, addToBackStack: String?) {
        val bundle = Bundle()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_holder, f, addToBackStack)
        f.arguments = bundle
        transaction.commit()
    }

    fun replaceFragment(f: Fragment, addToBackStack: String?, bundle: Bundle?): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_holder, f, addToBackStack)
        f.arguments = bundle
        transaction.commit()
        return true
    }


}
