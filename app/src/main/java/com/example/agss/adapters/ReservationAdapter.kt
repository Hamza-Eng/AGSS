package com.example.agss.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agss.R
import com.example.agss.models.Reservation
import com.google.android.material.card.MaterialCardView

class ReservationAdapter(
    private val reservations: List<Reservation>
) : RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {

    class ReservationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView = view.findViewById(R.id.reservationCard)
        val stadiumName: TextView = view.findViewById(R.id.stadiumNameText)
        val dateText: TextView = view.findViewById(R.id.dateText)
        val timeText: TextView = view.findViewById(R.id.timeText)
        val priceText: TextView = view.findViewById(R.id.priceText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reservation, parent, false)
        return ReservationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val reservation = reservations[position]
        
        holder.stadiumName.text = reservation.stadiumName
        holder.dateText.text = reservation.date
        holder.timeText.text = reservation.time
        holder.priceText.text = reservation.price
    }

    override fun getItemCount() = reservations.size
} 