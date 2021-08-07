package com.graduation.softskillsmeter.networking;

import com.graduation.softskillsmeter.models.requests.FeedbackRequest;
import com.graduation.softskillsmeter.models.responses.FeedbackResponse;
import com.graduation.softskillsmeter.models.responses.InterviewIdResponse;
import com.graduation.softskillsmeter.models.responses.InterviewsResponse;
import com.graduation.softskillsmeter.models.responses.QuestionsResponse;
import com.graduation.softskillsmeter.models.requests.InterviewIdRequest;
import com.graduation.softskillsmeter.models.requests.InterviewsRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST("questions")
    Call<QuestionsResponse> getQuestions();

    @POST("interviews")
    Call<InterviewsResponse> getInterviews(@Body InterviewsRequest request);

    @POST("feedbacks")
    Call<InterviewIdResponse> getInterviewId(@Body InterviewIdRequest request);

    @POST("interview")
    Call<FeedbackResponse> getInterviewFeedback(@Body FeedbackRequest request);
}