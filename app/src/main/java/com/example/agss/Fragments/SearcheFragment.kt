package com.example.agss.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agss.Adapters.ExplorerAdapter
import com.example.agss.Entity.Terain
import com.example.agss.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearcheFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearcheFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_searche, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemList =this.generateRandomTerainData()
        // Initialize RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ExplorerAdapter(itemList)
    }



    fun generateRandomTerainData(): List<Terain> {
        return listOf(
            Terain(
                name = "Sunny Fields",
                description = "A beautiful sunny terrain perfect for agriculture.",
                adresse = "123 Sunshine Lane, Agraria",
                size = "5x5",
                owner_name = "John Doe",
                owner_phone = "555-1234",
                images = R.drawable.pexels_pixabay,
                price = 1500
            ),
            Terain(
                name = "Lakeside Retreat",
                description = "A tranquil lakeside property ideal for a holiday getaway.",
                adresse = "456 Lakeside Drive, Serenity",
                size = "6x6",
                owner_name = "Jane Smith",
                owner_phone = "555-5678",
                images = R.drawable.pexels_tomfisk,
                price = 3000
            ),
            Terain(
                name = "Mountain Escape",
                description = "A scenic terrain nestled in the mountains.",
                adresse = "789 Alpine Road, Highland",
                size = "11x11",
                owner_name = "Mike Johnson",
                owner_phone = "555-9101",
                images = R.drawable.pexels_grizzlybear,
                price = 5000
            ),
            Terain(
                name = "Green Valley",
                description = "A lush green valley suitable for farming or eco-resort.",
                adresse = "101 Greenway Blvd, Eden",
                size = "5x5",
                owner_name = "Sarah Green",
                owner_phone = "555-1122",
                images = R.drawable.pexels_mike_468229_1171084,
                price = 2500
            ),
            Terain(
                name = "Urban Plot",
                description = "A conveniently located plot in the heart of the city.",
                adresse = "202 City Center Ave, Metropolis",
                size = "5x5",
                owner_name = "Alice Brown",
                owner_phone = "555-3344",
                images = R.drawable.pexels_grizzlybear,
                price = 10000
            )
        )
    }

}