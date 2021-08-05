package com.graduation.softskillsmeter.networking;

import com.graduation.softskillsmeter.models.FeedbackResponse;
import com.graduation.softskillsmeter.models.QuestionsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST("questions")
    Call<QuestionsResponse> getQuestions();

    @POST("interviews")
    Call<QuestionsResponse> getInterviews(); //TODO

    @FormUrlEncoded
    @POST("feedbacks")
    Call<QuestionsResponse> getInterviewId(@Field("user_id") String userId,
                                           @Field("questions_ids") List<Integer> questionIdsList,
                                           @Field("answers") List<String> answersList,
                                           @Field("date_time") String dateTime); //TODO

    @FormUrlEncoded
    @POST("interview")
    Call<FeedbackResponse> getInterviewFeedback(@Field("interview_id") String interviewId);
}