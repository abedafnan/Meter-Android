package com.graduation.softskillsmeter.ui.auth.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.graduation.softskillsmeter.utils.AESEncyption

class RegisterViewModel: ViewModel() {
    private var db: FirebaseFirestore = Firebase.firestore
    private val _registerFinished: MutableLiveData<Boolean> = MutableLiveData()

    fun getRegisterFinished(): MutableLiveData<Boolean> = _registerFinished

    fun onRegisterTapped(email: String, password: String, first: String, last: String) {
        val encryptedPassword = AESEncyption.encrypt(password)

        // Create a new user with a first, middle, and last name
        val user = hashMapOf(
            "first_name" to first,
            "last_name" to last,
            "email" to email,
            "password" to encryptedPassword
        )

        // Add a new document with a generated ID
        db.collection("user")
            .add(user)
            .addOnSuccessListener { documentReference ->
                _registerFinished.value = true
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    companion object {
        const val TAG = "Testing"
    }
}