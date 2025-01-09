package com.example.agss.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agss.R
import com.example.agss.adapters.ReservationAdapter
import com.example.agss.models.Reservation
import android.content.Context
import com.google.android.material.button.MaterialButton
import androidx.navigation.fragment.findNavController

class CalendarFragment : Fragment() {
    
    private lateinit var reservationsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupReservationsList(view)
    }

    private fun setupReservationsList(view: View) {
        reservationsRecyclerView = view.findViewById(R.id.reservationsRecyclerView)
        reservationsRecyclerView.layoutManager = LinearLayoutManager(context)

        // Get reservations from SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("Reservations", Context.MODE_PRIVATE)
        val reservationsSet = sharedPreferences.getStringSet("reservations", setOf()) ?: setOf()

        // Convert reservations to list
        val reservations = reservationsSet.mapNotNull { reservationJson ->
            try {
                val lines = reservationJson.split(",")
                Reservation(
                    stadiumName = lines.find { it.contains("stadiumName") }?.split(":")?.get(1)?.trim()?.removeSurrounding("\"") ?: "",
                    date = lines.find { it.contains("date") }?.split(":")?.get(1)?.trim()?.removeSurrounding("\"") ?: "",
                    time = lines.find { it.contains("time") }?.split(":")?.get(1)?.trim()?.removeSurrounding("\"") ?: "",
                    price = lines.find { it.contains("price") }?.split(":")?.get(1)?.trim()?.removeSurrounding("\"") ?: "",
                    userName = "" // Add if you have user info
                )
            } catch (e: Exception) {
                null
            }
        }

        // Show/hide empty state
        view.findViewById<View>(R.id.emptyStateLayout).visibility = 
            if (reservations.isEmpty()) View.VISIBLE else View.GONE

        // Set adapter
        reservationsRecyclerView.adapter = ReservationAdapter(reservations)

        // Setup book now button
        view.findViewById<MaterialButton>(R.id.bookNowButton).setOnClickListener {
            // Navigate to booking screen
            //findNavController().navigate(R.id.action_calendarFragment_to_bookingFragment)
        }
    }
}