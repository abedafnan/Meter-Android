package com.graduation.softskillsmeter.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Feedback(
    val feedback: String,
    val question_id: Int,
    val skill_id: Int,
    val skill_name: String,
    val skill_score: Double
) : Parcelable