package com.graduation.softskillsmeter.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Interview(
    @SerializedName("date-time")
    val dateTime: String,
    val feedback: List<Feedback>,
    val interview_questions: List<String>,
    val score: Double,
    val test: String,
    val user_answers: List<String>
) : Parcelable
