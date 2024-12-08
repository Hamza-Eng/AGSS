package com.example.agss.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agss.Entity.User
import com.example.agss.MainActivity
import com.example.agss.R
import com.example.agss.Services.UserServices
import com.example.agss.SignUpActivity
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

    private val userServices = UserServices()

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var rememberMeCheckBox: CheckBox
    private lateinit var forgotPasswordTextView: TextView
    private lateinit var loginButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI components
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        rememberMeCheckBox = findViewById(R.id.remember_me)
        forgotPasswordTextView = findViewById(R.id.forgot_password)
        loginButton = findViewById(R.id.login)

        // Check if user is already logged in
        checkUserLogin()

        // Set up listeners
        setupListeners()
    }

    private fun checkUserLogin() {
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            val name = sharedPreferences.getString("name", null)
            Toast.makeText(this, "Welcome back, $name!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupListeners() {
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (validateInput(email, password)) {
                handleLogin(email, password)
            } else {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }

        forgotPasswordTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if(!email.isNotEmpty() || !email.contains("@") || !email.contains(".")  ){

            Toast.makeText(this, "Email format is Incorrect", Toast.LENGTH_SHORT).show()
            return false
        }
            else if (password.isEmpty() || password.length < 8 ) {
            Toast.makeText(this, "password format is Incorrect < 8 ", Toast.LENGTH_SHORT).show()
            return false

        }
            else{

            return true

        }

    }

    private fun handleLogin(email: String, password: String) {
        userServices.loginUser(email, password) { user ->
            if (user != null) {
                val isSaved = saveUserData(user)
                if (isSaved) {
                    Toast.makeText(this, "Login successful! Welcome back, ${user.name}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this, "Failed to save user data. Please try again.", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun saveUserData(user: User): Boolean {
        return try {
            val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("userId", user.id) // Save user ID
            editor.putString("name", user.name)
            editor.putString("email", user.email)
            editor.putString("phone", user.phone)
            editor.putBoolean("isLoggedIn", rememberMeCheckBox.isChecked)
            editor.apply()
            true
        } catch (e: Exception) {
            e.printStackTrace() // Log the exception for debugging
            false
        }
    }

}
