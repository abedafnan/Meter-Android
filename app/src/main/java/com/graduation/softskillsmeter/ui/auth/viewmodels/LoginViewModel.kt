package com.graduation.softskillsmeter.ui.auth.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.graduation.softskillsmeter.utils.AESEncyption.encrypt


class LoginViewModel: ViewModel() {
    private var db: FirebaseFirestore = Firebase.firestore
    private val _loginFinished: MutableLiveData<Boolean> = MutableLiveData()

    fun getLoginFinished(): MutableLiveData<Boolean> = _loginFinished

    init {
        db = Firebase.firestore
    }

    fun onLoginTapped(email: String, password: String) {
        val encryptedPassword = encrypt(password)

        val rootRef = FirebaseFirestore.getInstance()
        val usersRef = rootRef.collection("user")
        val queryUsersByEmail: Query = usersRef.whereEqualTo("email", email)
        queryUsersByEmail.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    if (document.exists()) {
                        Log.d(TAG, "Email exists")

                        if (encryptedPassword != null) {
                            lookForPassword(encryptedPassword)
                        }

                    } else {
                        Log.d(TAG, "Email doesn't exist")
                        _loginFinished.value = false
                        return@addOnCompleteListener
                    }
                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.exception)
            }
        }
    }

    private fun lookForPassword(pass: String) {
        val rootRef = FirebaseFirestore.getInstance()
        val usersRef = rootRef.collection("user")
        val queryUsersByPass: Query = usersRef.whereEqualTo("password", pass)
        queryUsersByPass.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    if (document.exists()) {
                        Log.d(TAG, "Password exists")
                        _loginFinished.value = true
                    } else {
                        Log.d(TAG, "Password doesn't exist")
                        _loginFinished.value = false
                    }
                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.exception)
            }
        }
    }

    companion object {
        const val TAG = "Testing"
    }
}