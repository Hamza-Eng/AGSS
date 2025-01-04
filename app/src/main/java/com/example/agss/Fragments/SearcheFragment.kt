package com.example.agss.Fragments

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
import com.example.agss.adapters.SearchResultAdapter
import com.example.agss.models.SearchResult

class SearcheFragment : Fragment() {
    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyStateView: View
    private lateinit var searchAdapter: SearchResultAdapter

    // Sample data
    private val allResults = listOf(
        SearchResult(
            "Bernabeu Stadium",
            "Madrid, Spain",
            "Football",
            "$200/hour",
            R.drawable.main_background
        ),
        SearchResult(
            "Camp Nou",
            "Barcelona, Spain",
            "Football",
            "$180/hour",
            R.drawable.main_background
        ),
        SearchResult(
            "Allianz Arena",
            "Munich, Germany",
            "Football",
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

        // Setup RecyclerView
        searchAdapter = SearchResultAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = searchAdapter
            setHasFixedSize(true) // Improves performance
        }

        // Setup search listener
        searchEditText.addTextChangedListener { text ->
            filterResults(text?.toString() ?: "")
        }

        // Show initial results
        searchAdapter.updateResults(allResults)
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