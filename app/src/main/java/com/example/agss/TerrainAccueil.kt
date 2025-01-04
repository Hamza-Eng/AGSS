package com.example.agss

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.agss.adapters.StadiumPagerAdapter
import com.example.agss.databinding.ActivityTerrainAccueilBinding

class TerrainAccueil : AppCompatActivity() {
    private lateinit var binding: ActivityTerrainAccueilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTerrainAccueilBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            // Navigate to booking tab
            binding.viewPager.currentItem = 1
        }
    }
}