package com.graduation.softskillsmeter.models

import com.google.gson.annotations.SerializedName

data class Interview(
    @SerializedName("date-time")
    val dateTime: String,
    val feedback: List<String>,
    val interview_questions: List<String>,
    val score: Double,
    val test: String,
    val user_answers: List<String>
)