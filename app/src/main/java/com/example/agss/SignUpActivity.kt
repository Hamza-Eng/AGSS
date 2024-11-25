package com.example.agss

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agss.Entity.User
import com.example.agss.Services.UserServices
import com.google.android.material.button.MaterialButton

class SignUpActivity : AppCompatActivity() {

    private val userServices = UserServices()

    // Declare UI elements
    private lateinit var fullNameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signupButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize UI elements
        fullNameEditText = findViewById(R.id.full_name)
        phoneEditText = findViewById(R.id.phone)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        signupButton = findViewById(R.id.signup)

        setupListeners()
    }

    private fun setupListeners() {
        signupButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (fullName.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                handleSignup(fullName, phone, email, password)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleSignup(name: String, phone: String, email: String, password: String) {
        // Create a new User object
        val newUser = User(name, phone, email, password)

        // Call UserServices to handle signup logic
        val success = userServices.addUser(newUser)

        if (success==true) {
            Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Close LoginActivity
        } else {
            Toast.makeText(this, "Signup failed. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }
}