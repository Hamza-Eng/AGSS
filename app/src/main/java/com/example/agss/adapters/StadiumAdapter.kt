package com.example.agss.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agss.R
import com.example.agss.models.Stadium

class StadiumAdapter(
    private val stadiums: List<Stadium>,
    private val onStadiumClick: (Stadium) -> Unit
) : RecyclerView.Adapter<StadiumAdapter.StadiumViewHolder>() {

    inner class StadiumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onStadiumClick(stadiums[position])
                }
            }
        }
        val image: ImageView = view.findViewById(R.id.stadiumImage)
        val name: TextView = view.findViewById(R.id.stadiumName)
        val location: TextView = view.findViewById(R.id.stadiumLocation)
        val price: TextView = view.findViewById(R.id.stadiumPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StadiumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stadium, parent, false)
        return StadiumViewHolder(view)
    }

    override fun onBindViewHolder(holder: StadiumViewHolder, position: Int) {
        val stadium = stadiums[position]
        holder.image.setImageResource(stadium.imageResId)
        holder.name.text = stadium.name
        holder.location.text = stadium.location
        holder.price.text = stadium.price
    }

    override fun getItemCount() = stadiums.size
} 