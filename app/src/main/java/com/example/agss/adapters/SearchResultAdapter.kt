package com.example.agss.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agss.R
import com.example.agss.models.SearchResult

class SearchResultAdapter(private var results: List<SearchResult> = emptyList()) :
    RecyclerView.Adapter<SearchResultAdapter.SearchViewHolder>() {

    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.stadiumImage)
        val name: TextView = view.findViewById(R.id.stadiumName)
        val location: TextView = view.findViewById(R.id.stadiumLocation)
        val price: TextView = view.findViewById(R.id.stadiumPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stadium, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val result = results[position]
        holder.image.setImageResource(result.imageResId)
        holder.name.text = result.name
        holder.location.text = result.location
        holder.price.text = result.price
    }

    override fun getItemCount() = results.size

    fun updateResults(newResults: List<SearchResult>) {
        results = newResults
        notifyDataSetChanged()
    }
} 