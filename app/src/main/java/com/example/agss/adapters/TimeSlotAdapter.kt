package com.example.agss.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.agss.R
import com.example.agss.models.TimeSlot

class TimeSlotAdapter(
    private var timeSlots: List<TimeSlot>,
    private val onTimeSlotSelected: (TimeSlot) -> Unit
) : RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {

    inner class TimeSlotViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val timeText: TextView = view.findViewById(R.id.timeText)
        val cardView: CardView = view.findViewById(R.id.timeSlotCard)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val timeSlot = timeSlots[position]
                    if (timeSlot.isAvailable) {
                        timeSlot.isSelected = !timeSlot.isSelected
                        onTimeSlotSelected(timeSlot)
                        notifyItemChanged(position)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_time_slot, parent, false)
        return TimeSlotViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {
        val timeSlot = timeSlots[position]
        holder.timeText.text = timeSlot.time

        // Update card appearance based on state
        when {
            !timeSlot.isAvailable -> {
                holder.cardView.setCardBackgroundColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.unavailable_slot)
                )
                holder.timeText.setTextColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.white)
                )
            }
            timeSlot.isSelected -> {
                holder.cardView.setCardBackgroundColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.selected_slot)
                )
                holder.timeText.setTextColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.white)
                )
            }
            else -> {
                holder.cardView.setCardBackgroundColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.white)
                )
                holder.timeText.setTextColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.black)
                )
            }
        }
    }

    override fun getItemCount() = timeSlots.size

    fun updateTimeSlots(newTimeSlots: List<TimeSlot>) {
        timeSlots = newTimeSlots
        notifyDataSetChanged()
    }
} 