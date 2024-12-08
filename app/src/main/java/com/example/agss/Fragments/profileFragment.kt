package com.example.agss.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.agss.Entity.User
import com.example.agss.R
import com.example.agss.Services.UserServices
import com.example.agss.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    private val userServices = UserServices()

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_, container, false)

        // Initialize views
        nameEditText = view.findViewById(R.id.name)
        emailEditText = view.findViewById(R.id.email)
        phoneEditText = view.findViewById(R.id.phone)
        passwordEditText = view.findViewById(R.id.password)
        updateButton = view.findViewById(R.id.update_button)
        deleteButton = view.findViewById(R.id.delete_button)
        logoutButton = view.findViewById(R.id.logout_button)

        // Load user data
        loadUserProfile()

        // Set button listeners
        setupButtonListeners()

        return view
    }

    private fun loadUserProfile() {
        // Retrieve user ID from SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", null)

        if (userId != null) {
            userServices.loadUserData(userId) { user ->
                if (user != null) {
                    // Populate UI fields with user data
                    nameEditText.setText(user.name)
                    emailEditText.setText(user.email)
                    phoneEditText.setText(user.phone)
                    passwordEditText.setText(user.password)
                } else {
                    Toast.makeText(requireContext(), "Failed to load user data", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(requireContext(), "User ID not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupButtonListeners() {
        updateButton.setOnClickListener {
            updateUserProfile()
        }

        deleteButton.setOnClickListener {
            deleteUserProfile()
        }

        logoutButton.setOnClickListener {
            logoutUser()
        }
    }

    private fun updateUserProfile() {
        // Retrieve user ID from SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", null)

        if (userId != null) {
            val updatedUser = User(
                id = userId,
                name = nameEditText.text.toString(),
                email = emailEditText.text.toString(),
                phone = phoneEditText.text.toString(),
                password = passwordEditText.text.toString()
            )

            userServices.updateUser(userId, updatedUser) { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to update profile", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(requireContext(), "User ID not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteUserProfile() {
        // Retrieve user ID from SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", null)

        if (userId != null) {
            userServices.deleteUser(userId) { isSuccess ->
                if (isSuccess) {
                    Toast.makeText(requireContext(), "Profile deleted successfully", Toast.LENGTH_SHORT).show()
                    logoutUser() // Log out the user after deleting the profile
                } else {
                    Toast.makeText(requireContext(), "Failed to delete profile", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(requireContext(), "User ID not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun logoutUser() {
        // Clear SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        // Redirect to LoginActivity
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
