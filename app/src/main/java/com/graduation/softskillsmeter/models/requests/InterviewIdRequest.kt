package com.graduation.softskillsmeter.models.requests

data class InterviewIdRequest(
    var user_id: String,
    var questions_ids: List<Int>,
    var answers: List<String>,
    var date_time: String
)