package com.example.agss

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.agss.adapters.StadiumPagerAdapter
import com.example.agss.databinding.ActivityTerrainAccueilBinding

import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker

class TerrainAccueil : AppCompatActivity() {
    private lateinit var binding: ActivityTerrainAccueilBinding
    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTerrainAccueilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize osmdroid configuration
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))

        // Get data from intent and update UI
        intent.extras?.let { bundle ->
            binding.title.text = bundle.getString("stadium_name", "")
            binding.location.text = bundle.getString("stadium_location", "")
            binding.price.text = bundle.getString("stadium_price", "")
            binding.sportsImage.setImageResource(bundle.getInt("stadium_image"))
        }

        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Setup ViewPager and TabLayout
        val pagerAdapter = StadiumPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Info"
                1 -> "Book"
                else -> ""
            }
        }.attach()

        // Handle booking button click
        binding.fabBook.setOnClickListener {
            // Create intent to start BookingActivity
            val intent = Intent(this, BookingActivity::class.java).apply {
                // Pass any necessary data to BookingActivity
                putExtra("stadium_name", binding.title.text)
                putExtra("stadium_location", binding.location.text)
                putExtra("stadium_price", binding.price.text)
            }
            startActivity(intent)
        }

        // Initialize map
        map = binding.mapView // Use binding to get mapView
        map.setMultiTouchControls(true)

        val mapController = map.controller
        mapController.setZoom(15.0)

        // Set the center point (example coordinates for Casablanca)
        val startPoint = GeoPoint(33.5731, -7.5898)
        mapController.setCenter(startPoint)

        // Add a marker
        val marker = Marker(map)
        marker.position = startPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = "Bernabeu Stadium"
        map.overlays.add(marker)
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }
}