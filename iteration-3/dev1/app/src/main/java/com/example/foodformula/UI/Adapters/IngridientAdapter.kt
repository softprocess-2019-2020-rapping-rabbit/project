package com.example.foodformula.UI.Adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodformula.ApiConnection.Models.ExtendedIngredients
import com.example.foodformula.R


class IngridientAdapter(var array: List<ExtendedIngredients?>) :
    RecyclerView.Adapter<IngridientAdapter.ViewHolder>() {


    override fun getItemCount(): Int = array.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.ingridient_item,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = array[position]!!.name
        holder.tvVegeterian.text = array[position]!!.amount.toString()
        val uri =
            Uri.parse("https://spoonacular.com/cdn/ingredients_100x100/" + array[position]!!.image)
        holder.ivImage.setImageURI(uri)

    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvName = v.findViewById(R.id.name) as TextView
        val tvVegeterian = v.findViewById(R.id.vegeterian) as TextView
        val ivImage = v.findViewById(R.id.image) as ImageView
    }


}