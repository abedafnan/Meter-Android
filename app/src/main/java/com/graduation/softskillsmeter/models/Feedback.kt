package com.graduation.softskillsmeter.models

data class Feedback(
    val id: Int,
    val score: Double,
    val skillName: String,
    val feedback: String,
)