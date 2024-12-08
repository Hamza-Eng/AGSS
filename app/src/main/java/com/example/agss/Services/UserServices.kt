package com.example.agss.Services

import com.example.agss.Entity.User
import com.google.firebase.database.*

class UserServices {

    // Function to add a user to Firebase Realtime Database
    fun addUser(user: User,callback: (String?)-> Unit){
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("users")

        // Pushing a new user to the database with a unique ID
        val userId = myRef.push().key ?: return callback(null)

        // Create a new User instance with the ID
        val userWithId = User(
            id = userId,
            name = user.name,
            phone = user.phone,
            email = user.email,
            password = user.password
        )

//        var isSuccess = true
//        myRef.child(userId).setValue(userWithId).addOnCompleteListener { task ->
//            isSuccess = task.isSuccessful
//        }.addOnFailureListener {
//            isSuccess = false
//        }
//
//        return isSuccess
        myRef.child(userId).setValue(userWithId).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(userId) // Return the userId if the user is added successfully
            } else {
                callback(null) // Return null if there is an error
            }
        }.addOnFailureListener {
            callback(null) // Handle failure by returning null
        }
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


    // Function to load user data by ID
    fun loadUserData(userId: String, callback: (User?) -> Unit) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val usersRef: DatabaseReference = database.getReference("users")

        usersRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                callback(user) // Return the user data or null if not found
            }

            override fun onCancelled(error: DatabaseError) {
                callback(null) // Handle errors gracefully
            }
        })
    }

    fun updateUser(userId: String, updatedUser: User, callback: (Boolean) -> Unit) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val usersRef: DatabaseReference = database.getReference("users")
        usersRef.child(userId).setValue(updatedUser)
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    fun deleteUser(userId: String, callback: (Boolean) -> Unit) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val usersRef: DatabaseReference = database.getReference("users")
        usersRef.child(userId).removeValue()
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
            .addOnFailureListener {
                callback(false)
            }
    }



}
