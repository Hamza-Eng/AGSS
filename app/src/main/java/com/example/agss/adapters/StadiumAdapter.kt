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
    private val onStadiumClicked: (Stadium) -> Unit
) : RecyclerView.Adapter<StadiumAdapter.StadiumViewHolder>() {

    inner class StadiumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.stadiumName)
        private val locationTextView: TextView = itemView.findViewById(R.id.stadiumLocation)
        private val priceTextView: TextView = itemView.findViewById(R.id.stadiumPrice)
        private val imageView: ImageView = itemView.findViewById(R.id.stadiumImage)

        fun bind(stadium: Stadium) {
            nameTextView.text = stadium.name
            locationTextView.text = stadium.location
            priceTextView.text = stadium.price
            imageView.setImageResource(stadium.imageResId)

            itemView.setOnClickListener {
                onStadiumClicked(stadium)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StadiumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stadium, parent, false)
        return StadiumViewHolder(view)
    }

    override fun onBindViewHolder(holder: StadiumViewHolder, position: Int) {
        holder.bind(stadiums[position])
    }

    override fun getItemCount() = stadiums.size
} 