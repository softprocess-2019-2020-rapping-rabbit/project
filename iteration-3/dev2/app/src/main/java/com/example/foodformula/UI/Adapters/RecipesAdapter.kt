package com.example.foodformula.UI.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodformula.ApiConnection.Models.Recipe
import com.example.foodformula.R


class RecipesAdapter(private val context: Context, var array: List<Recipe?>) :
    RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {


    interface ItemClickListener {
        fun onRecyclerItemClick(view: View, position: Int)
    }

    var listener: ItemClickListener? = null


    override fun getItemCount(): Int = array.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recipe_item,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = array[position]!!.title

    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(val v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        val tvName = v.findViewById(R.id.recipe) as TextView


        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            listener?.onRecyclerItemClick(
                v,
                adapterPosition
            )
        }
    }


}