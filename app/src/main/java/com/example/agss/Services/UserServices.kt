package com.example.agss.Services

import com.example.agss.Entity.User
import com.google.firebase.database.*

class UserServices {

    // Function to add a user to Firebase Realtime Database
    fun addUser(user: User): Boolean {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("users")

        // Pushing a new user to the database with a unique ID
        val userId = myRef.push().key ?: return false

        var isSuccess = true
        myRef.child(userId).setValue(user).addOnCompleteListener { task ->
            isSuccess = task.isSuccessful
        }.addOnFailureListener {
            isSuccess = false
        }

        return isSuccess
    }


    // Function to log in a user by email and password
    fun loginUser(email: String, password: String, callback: (User?) -> Unit) {
        val database = FirebaseDatabase.getInstance()
        val usersRef = database.getReference("users")

        // Querying the database by email
        usersRef.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var userFound: User? = null

                    if (snapshot.exists()) {
                        for (userSnapshot in snapshot.children) {
                            val user = userSnapshot.getValue(User::class.java)
                            // Check if the password matches
                            if (user != null && user.password == password) {
                                userFound = user
                                break // Exit loop as we found the user
                            }
                        }
                    }

                    // Invoke callback with the result
                    callback(userFound) // null if no matching user
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(null) // Handle database errors gracefully
                }
            })
    }





}
