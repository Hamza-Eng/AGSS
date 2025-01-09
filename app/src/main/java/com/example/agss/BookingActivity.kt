package com.example.agss

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.agss.adapters.TimeSlotAdapter
import com.example.agss.databinding.ActivityBookingBinding
import com.example.agss.models.TimeSlot
import com.example.agss.models.Reservation
import java.text.SimpleDateFormat
import java.util.*

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private lateinit var timeSlotAdapter: TimeSlotAdapter
    private var selectedDate: String? = null
    private var selectedTimeSlot: TimeSlot? = null
    private var stadiumName: String = ""
    private var stadiumPrice: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get stadium details from intent
        stadiumName = intent.getStringExtra("stadium_name") ?: ""
        stadiumPrice = intent.getStringExtra("stadium_price") ?: ""

        setupCalendarView()
        setupTimeSlots()
        setupBookButton()
    }

    private fun setupCalendarView() {
        binding.calendarView.minDate = System.currentTimeMillis()
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            selectedDate = dateFormat.format(calendar.time)
            
            // You could fetch availability for the selected date here
            // For now, we'll just show a toast
            Toast.makeText(this, "Selected date: $selectedDate", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupTimeSlots() {
        // Generate time slots from 8:00 to 22:00
        val timeSlots = generateTimeSlots()
        
        timeSlotAdapter = TimeSlotAdapter(timeSlots) { timeSlot ->
            selectedTimeSlot = timeSlot
            Toast.makeText(this, "Selected time: ${timeSlot.time}", Toast.LENGTH_SHORT).show()
        }

        binding.timeSlotRecyclerView.apply {
            layoutManager = GridLayoutManager(this@BookingActivity, 4)
            adapter = timeSlotAdapter
        }
    }

    private fun generateTimeSlots(): List<TimeSlot> {
        val slots = mutableListOf<TimeSlot>()
        val startHour = 8
        val endHour = 22

        for (hour in startHour until endHour) {
            slots.add(TimeSlot(String.format("%02d:00", hour)))
        }
        return slots
    }

    private fun setupBookButton() {
        binding.bookButton.setOnClickListener {
            if (selectedDate == null || selectedTimeSlot == null) {
                Toast.makeText(this, "Please select both date and time", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
            val userName = sharedPreferences.getString("name", "") ?: ""

            val reservation = Reservation(
                stadiumName = stadiumName,
                date = selectedDate!!,
                time = selectedTimeSlot!!.time,
                price = stadiumPrice,
                userName = userName
            )

            saveReservation(reservation)
            Toast.makeText(this, "Booking confirmed!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun saveReservation(reservation: Reservation) {
        val sharedPreferences = getSharedPreferences("Reservations", MODE_PRIVATE)
        val reservationsSet = sharedPreferences.getStringSet("reservations", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        
        // Convert reservation to JSON string
        val reservationJson = """
            {
                "id": "${reservation.id}",
                "stadiumName": "${reservation.stadiumName}",
                "date": "${reservation.date}",
                "time": "${reservation.time}",
                "price": "${reservation.price}",
                "userName": "${reservation.userName}"
            }
        """.trimIndent()
        
        reservationsSet.add(reservationJson)
        
        sharedPreferences.edit().putStringSet("reservations", reservationsSet).apply()
    }
} 