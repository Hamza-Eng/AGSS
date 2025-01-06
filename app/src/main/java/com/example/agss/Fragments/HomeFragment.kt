package com.example.agss.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agss.R
import com.example.agss.adapters.CategoryAdapter
import com.example.agss.adapters.StadiumAdapter
import com.example.agss.models.Category
import com.example.agss.models.Stadium
import android.content.Intent
import com.example.agss.TerrainAccueil

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var stadiumsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupCategoriesRecyclerView(view)
        setupStadiumsList(view)
    }

    private fun setupCategoriesRecyclerView(view: View) {
        categoriesRecyclerView = view.findViewById(R.id.categoriesRecyclerView)
        categoriesRecyclerView.layoutManager = GridLayoutManager(context, 2)

        // Sample categories data
        val categories = listOf(
            Category("Football", R.drawable.football),
            Category("Basketball", R.drawable.basketball),
            Category("Tennis", R.drawable.tennis),
            Category("Volleyball", R.drawable.volleyball)
        )

        categoriesRecyclerView.adapter = CategoryAdapter(categories)
    }

    private fun setupStadiumsList(view: View) {
        val stadiumsRecyclerView = view.findViewById<RecyclerView>(R.id.stadiumsRecyclerView)
        stadiumsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // Create list of stadiums with real data
        val stadiums = listOf(

            Stadium(
                id = "1",
                name = "Camp Nou",
                location = "Barcelona, Spain",
                price = "$90/hour",
                imageResId = R.drawable.pexels_pixabay,
                description = "Europe's largest stadium, home to FC Barcelona",
                latitude = 41.3809,
                longitude = 2.1228
            ),
            Stadium(
                id = "2",
                name = "Bernabeu Stadium",
                location = "Madrid, Spain",
                price = "$100/hour",
                imageResId = R.drawable.pexels_grizzlybear,
                description = "Home of Real Madrid, this iconic stadium offers world-class facilities",
                latitude = 40.4530,
                longitude = -3.6883
            ),
            Stadium(
                id = "3",
                name = "Bernabeu Stadium",
                location = "Madrid, Spain",
                price = "$100/hour",
                imageResId = R.drawable.pexels_mike_468229_1171084,
                description = "Home of Real Madrid, this iconic stadium offers world-class facilities",
                latitude = 40.4530,
                longitude = -3.6883
            ),

        )

        stadiumsRecyclerView.adapter = StadiumAdapter(stadiums) { stadium ->
            val intent = Intent(context, TerrainAccueil::class.java).apply {
                putExtra("stadium_id", stadium.id)
                putExtra("stadium_name", stadium.name)
                putExtra("stadium_location", stadium.location)
                putExtra("stadium_price", stadium.price)
                putExtra("stadium_image", stadium.imageResId)
                putExtra("stadium_description", stadium.description)
                putExtra("stadium_latitude", stadium.latitude)
                putExtra("stadium_longitude", stadium.longitude)
            }
            startActivity(intent)
        }
    }
}