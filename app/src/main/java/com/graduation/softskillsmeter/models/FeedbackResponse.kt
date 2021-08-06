package com.graduation.softskillsmeter.models

import com.google.gson.annotations.SerializedName

data class FeedbackResponse(
    @SerializedName("0")
    val interview: Interview
)