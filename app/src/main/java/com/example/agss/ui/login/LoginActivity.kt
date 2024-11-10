package com.example.agss.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agss.MainActivity
import com.example.agss.R
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

    // UI elements
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var rememberMeCheckBox: CheckBox
//    private lateinit var forgotPasswordTextView: TextView
    private lateinit var loginButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI components
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
//        rememberMeCheckBox = findViewById(R.id.remember_me)
//        forgotPasswordTextView = findViewById(R.id.forgot_password)
        loginButton = findViewById(R.id.login)

        // Set up listeners
        setupListeners()
    }

    private fun setupListeners() {
        // Login button click listener
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (validateInput(email, password)) {
                handleLogin(email, password)
            } else {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Forgot password click listener
        /*forgotPasswordTextView.setOnClickListener {
            // Navigate to ForgotPasswordActivity or handle password recovery
            Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
        }*/
    }

    private fun validateInput(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun handleLogin(email: String, password: String) {
        // Example authentication check (replace with real auth logic)
        if (email == "test@example.com" && password == "password") {
            // Navigate to MainActivity on successful login
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Close LoginActivity
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }
}
