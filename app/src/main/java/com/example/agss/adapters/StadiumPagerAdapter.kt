package com.example.agss.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.agss.fragments.StadiumInfoFragment
//import com.example.agss.fragments.BookingFragment

class StadiumPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> StadiumInfoFragment()
//            1 -> BookingFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
} 