package com.graduation.softskillsmeter.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern

object ValidationUtils {

    private fun isEmailValid(email: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        // TODO: Check if email already exists in DB
    }

    private fun passwordsDoMatch(pass1: String, pass2: String): Boolean {
        return pass1 == pass2
    }

    private fun isValidPassword(password: String): Boolean {
        return !TextUtils.isEmpty(password) && password.length >= 6
    }

    private fun isValidName(first: String, last: String): Boolean {
        val p: Pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val m: Matcher = p.matcher("I am a string")
        val containsSpecialChars: Boolean = m.find()

        return first.isNotEmpty()
                && last.isNotEmpty()
                && !containsSpecialChars
    }

    fun validateSignIn(context: Context, email: String, password: String): Boolean {
        if (!isEmailValid(email)) {
            Toast.makeText(context, "Enter a valid email", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isValidPassword(password)) {
            Toast.makeText(context, "Enter a valid password", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    fun validateSignUp(context: Context, email: String, password: String,
                       firstName: String, lastName: String, confirmPassword: String) : Boolean {
        if (!isValidName(firstName, lastName)) {
            Toast.makeText(context, "Enter valid first and last names", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isEmailValid(email)) {
            Toast.makeText(context, "Enter a valid email", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isValidPassword(password)) {
            Toast.makeText(context, "Enter a valid password", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!passwordsDoMatch(password, confirmPassword)) {
            Toast.makeText(context, "Passwords don't match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}