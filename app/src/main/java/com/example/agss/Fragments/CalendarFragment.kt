package com.example.agss.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.agss.R
import android.util.Log
import android.content.Context
import android.graphics.Typeface
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import androidx.core.content.ContextCompat
import com.example.agss.databinding.FragmentCalendarBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarFragment : Fragment() {
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayReservations()
    }

    private fun displayReservations() {
        val sharedPreferences = requireContext().getSharedPreferences("Reservations", Context.MODE_PRIVATE)
        val reservationsSet = sharedPreferences.getStringSet("reservations", setOf()) ?: setOf()

        val reservationsLayout = binding.reservationsLayout
        reservationsLayout.removeAllViews()

        for (reservationJson in reservationsSet) {
            try {
                // Extract values using simple string manipulation
                val lines = reservationJson.split(",")
                val stadiumName = lines.find { it.contains("stadiumName") }?.split(":")?.get(1)?.trim()?.removeSurrounding("\"") ?: ""
                val date = lines.find { it.contains("date") }?.split(":")?.get(1)?.trim()?.removeSurrounding("\"") ?: ""
                val time = lines.find { it.contains("time") }?.split(":")?.get(1)?.trim()?.removeSurrounding("\"") ?: ""
                val price = lines.find { it.contains("price") }?.split(":")?.get(1)?.trim()?.removeSurrounding("\"") ?: ""

                // Create a card view for each reservation
                val cardView = MaterialCardView(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(16, 8, 16, 8)
                    }
                    radius = resources.getDimension(R.dimen.card_corner_radius)
                    elevation = resources.getDimension(R.dimen.card_elevation)
                }

                val contentLayout = LinearLayout(requireContext()).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(16, 16, 16, 16)
                }

                // Add reservation details
                contentLayout.addView(TextView(requireContext()).apply {
                    text = "Stadium: $stadiumName"
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                    setTypeface(null, Typeface.BOLD)
                })

                contentLayout.addView(TextView(requireContext()).apply {
                    text = "Date: $date"
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                })

                contentLayout.addView(TextView(requireContext()).apply {
                    text = "Time: $time"
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                })

                contentLayout.addView(TextView(requireContext()).apply {
                    text = "Price: $price"
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                    setTextColor(ContextCompat.getColor(context, R.color.my_primary))
                })

                cardView.addView(contentLayout)
                reservationsLayout.addView(cardView)
            } catch (e: Exception) {
                Log.e("CalendarFragment", "Error parsing reservation: $e")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}