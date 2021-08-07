package com.graduation.softskillsmeter.ui.home.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.softskillsmeter.models.Interview
import com.graduation.softskillsmeter.models.requests.FeedbackRequest
import com.graduation.softskillsmeter.models.requests.InterviewIdRequest
import com.graduation.softskillsmeter.models.responses.FeedbackResponse
import com.graduation.softskillsmeter.models.responses.InterviewIdResponse
import com.graduation.softskillsmeter.networking.RetrofitClient
import com.graduation.softskillsmeter.networking.RetrofitService
import com.graduation.softskillsmeter.ui.home.states.RequestState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedbackViewModel : ViewModel() {
    private val _feedback: MutableLiveData<Interview> = MutableLiveData()
    private val _requestState: MutableLiveData<RequestState> = MutableLiveData(RequestState.NOT_STARTED)

    val requestState: MutableLiveData<RequestState>
        get() = _requestState
    val feedback: MutableLiveData<Interview>
        get() = _feedback

    fun getInterviewId(requestBody: InterviewIdRequest) {
        _requestState.value = RequestState.LOADING

        val service = RetrofitClient.getRetrofit().create(RetrofitService::class.java)
        service.getInterviewId(requestBody).enqueue(object :
            Callback<InterviewIdResponse> {
            override fun onResponse(call: Call<InterviewIdResponse>, response: Response<InterviewIdResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("response", it.interview_id)
                        getFeedback(it.interview_id)
                    }
                }

                Log.d("response", "Response: $response")
            }

            override fun onFailure(call: Call<InterviewIdResponse>, t: Throwable) {
                _requestState.value = RequestState.FAIL
                Log.e("response", "Error ${t.message}")
            }
        })
    }

    private fun getFeedback(interviewId: String) {
        _requestState.value = RequestState.LOADING

        val service = RetrofitClient.getRetrofit().create(RetrofitService::class.java)
        service.getInterviewFeedback(FeedbackRequest(interviewId)).enqueue(object :
            Callback<FeedbackResponse> {
            override fun onResponse(call: Call<FeedbackResponse>, response: Response<FeedbackResponse>) {
                if (response.isSuccessful) {
                    _feedback.value = response.body()?.get(0)
                    _requestState.value = RequestState.SUCCESS
                }

                Log.d("response", "Response: $response")
            }

            override fun onFailure(call: Call<FeedbackResponse>, t: Throwable) {
                _requestState.value = RequestState.FAIL
                Log.e("response", "Error ${t.message}")
            }
        })
    }
}