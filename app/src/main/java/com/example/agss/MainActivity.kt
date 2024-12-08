package com.example.agss

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.agss.Fragments.HomeFragment
import com.example.agss.Fragments.SearcheFragment
import com.example.agss.Fragments.CalendarFragment
import com.example.agss.Fragments.ProfileFragment
import com.example.agss.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())
        binding.bottomNavigationView.background = null

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.search -> replaceFragment(SearcheFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.calendar -> replaceFragment(CalendarFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
