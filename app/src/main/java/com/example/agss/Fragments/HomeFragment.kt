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
        setupStadiumsRecyclerView(view)
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

    private fun setupStadiumsRecyclerView(view: View) {
        stadiumsRecyclerView = view.findViewById(R.id.stadiumsRecyclerView)
        stadiumsRecyclerView.layoutManager = 
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // Sample stadiums data
        val stadiums = listOf(
            Stadium(
                "Bernabeu Stadium",
                "Madrid, Spain",
                "$200/hour",
                R.drawable.main_background
            ),
            Stadium(
                "Camp Nou",
                "Barcelona, Spain",
                "$180/hour",
                R.drawable.main_background
            ),
            Stadium(
                "Allianz Arena",
                "Munich, Germany",
                "$150/hour",
                R.drawable.main_background
            )
        )

        stadiumsRecyclerView.adapter = StadiumAdapter(stadiums) { stadium ->
            // Navigate to TerrainAccueil
            val intent = Intent(context, TerrainAccueil::class.java).apply {
//                putExtra("stadium_id", stadium.)
                putExtra("stadium_name", stadium.name)
                putExtra("stadium_location", stadium.location)
                putExtra("stadium_price", stadium.price)
                putExtra("stadium_image", stadium.imageResId)
            }
            startActivity(intent)
        }
    }
}