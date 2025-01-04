package com.example.agss.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agss.R
import com.example.agss.TerrainAccueil
import com.example.agss.adapters.SearchAdapter
import com.example.agss.models.SearchResult

class SearcheFragment : Fragment() {
    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyStateView: View
    private lateinit var searchAdapter: SearchAdapter

    // Sample data
    private val allResults = listOf(
        SearchResult(
            "Bernabeu Stadium",
            "Madrid, Spain",
            "$200/hour",
            R.drawable.main_background
        ),
        SearchResult(
            "Camp Nou",
            "Barcelona, Spain",
            "$180/hour",
            R.drawable.main_background
        ),
        SearchResult(
            "Allianz Arena",
            "Munich, Germany",
            "$150/hour",
            R.drawable.main_background
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_searche, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        searchEditText = view.findViewById(R.id.searchEditText)
        recyclerView = view.findViewById(R.id.searchRecyclerView)
        emptyStateView = view.findViewById(R.id.emptyStateView)

        setupRecyclerView()

        // Setup search listener
        searchEditText.addTextChangedListener { text ->
            filterResults(text?.toString() ?: "")
        }

        // Show initial results
        searchAdapter.updateResults(allResults)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        
        // Initialize adapter with click listener
        searchAdapter = SearchAdapter(allResults) { searchResult ->
            // Handle click - navigate to TerrainAccueil
            val intent = Intent(context, TerrainAccueil::class.java).apply {
                putExtra("stadium_name", searchResult.name)
                putExtra("stadium_location", searchResult.location)
                putExtra("stadium_price", searchResult.price)
                putExtra("stadium_image", searchResult.imageResId)
            }
            startActivity(intent)
        }
        
        recyclerView.adapter = searchAdapter
    }

    private fun filterResults(query: String) {
        val filteredResults = if (query.isEmpty()) {
            allResults
        } else {
            allResults.filter { result ->
                result.name.contains(query, ignoreCase = true) ||
                result.location.contains(query, ignoreCase = true)
            }
        }

        searchAdapter.updateResults(filteredResults)
        updateEmptyState(filteredResults.isEmpty())
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        emptyStateView.visibility = if (isEmpty) View.VISIBLE else View.GONE
        recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }
}