package com.example.agss.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agss.R
import com.example.agss.models.SearchResult

class SearchAdapter(
    private var searchResults: List<SearchResult>,
    private val onItemClick: (SearchResult) -> Unit  // Add click handler
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.stadiumImage)
        val name: TextView = view.findViewById(R.id.stadiumName)
        val location: TextView = view.findViewById(R.id.stadiumLocation)
        val price: TextView = view.findViewById(R.id.stadiumPrice)

        init {
            // Set click listener for the entire item
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(searchResults[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stadium, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val result = searchResults[position]
        holder.image.setImageResource(result.imageResId)
        holder.name.text = result.name
        holder.location.text = result.location
        holder.price.text = result.price
    }

    override fun getItemCount() = searchResults.size

    fun updateResults(newResults: List<SearchResult>) {
        searchResults = newResults
        notifyDataSetChanged()
    }
} 