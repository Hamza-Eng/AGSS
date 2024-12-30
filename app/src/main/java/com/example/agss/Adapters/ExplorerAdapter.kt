package com.example.agss.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agss.Entity.Terain
import com.example.agss.R

class ExplorerAdapter(private var terains: List<Terain>) : RecyclerView.Adapter<ExplorerAdapter.TerainViewHolder>() {

    class TerainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = itemView.findViewById(R.id.name)
        val adresse: TextView = itemView.findViewById(R.id.adresse)
        val size: TextView = itemView.findViewById(R.id.size)
        val price: TextView = itemView.findViewById(R.id.price)
        val image: ImageView = itemView.findViewById(R.id.card_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TerainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardhome, parent, false)
        return TerainViewHolder(view)
    }

    override fun onBindViewHolder(holder: TerainViewHolder, position: Int) {
        val terain = terains[position]
        holder.name.text = terain.name
        holder.size.text = terain.size
        holder.adresse.text = terain.adresse
        holder.price.text = terain.price.toString()
        holder.image.setImageResource(terain.images as Int)
    }

    override fun getItemCount(): Int = terains.size

    // Method to update the dataset
    fun updateList(newList: List<Terain>) {
        terains = newList
        notifyDataSetChanged()
    }
}