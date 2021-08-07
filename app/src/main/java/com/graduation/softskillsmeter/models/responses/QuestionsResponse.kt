package com.graduation.softskillsmeter.models.responses

import com.google.gson.annotations.SerializedName

data class QuestionsResponse(
    @SerializedName("1")
    val first: String,
    @SerializedName("2")
    val second: String,
    @SerializedName("3")
    val third: String,
    @SerializedName("4")
    val fourth: String
)