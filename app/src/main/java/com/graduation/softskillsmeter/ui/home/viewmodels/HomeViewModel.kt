package com.graduation.softskillsmeter.ui.home.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.softskillsmeter.app.AppController
import com.graduation.softskillsmeter.models.Interview
import com.graduation.softskillsmeter.models.InterviewsResponse
import com.graduation.softskillsmeter.models.requests.InterviewsRequest
import com.graduation.softskillsmeter.networking.RetrofitClient
import com.graduation.softskillsmeter.networking.RetrofitService
import com.graduation.softskillsmeter.ui.home.states.RequestState
import com.graduation.softskillsmeter.utils.SharedPreferenceUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _requestState: MutableLiveData<RequestState> = MutableLiveData(RequestState.NOT_STARTED)
    private val _previousInterviewsList: MutableLiveData<ArrayList<Interview>> = MutableLiveData()

    val requestState: MutableLiveData<RequestState>
        get() = _requestState
    val previousInterviewsList: MutableLiveData<ArrayList<Interview>>
        get() = _previousInterviewsList

    fun getInterviews(userId: String) {
        _requestState.value = RequestState.LOADING

        val service = RetrofitClient.getRetrofit().create(RetrofitService::class.java)
        service.getInterviews(InterviewsRequest(userId)).enqueue(object :
            Callback<InterviewsResponse> {
            override fun onResponse(call: Call<InterviewsResponse>, response: Response<InterviewsResponse>) {
                if (response.isSuccessful) {
                    _previousInterviewsList.value = response.body()?.interviews as ArrayList<Interview>
                    _requestState.value = RequestState.SUCCESS
                }

                Log.d("response", "Response: $response")
            }

            override fun onFailure(call: Call<InterviewsResponse>, t: Throwable) {
                _requestState.value = RequestState.FAIL
                Log.e("response", "Error ${t.message}")
            }
        })
    }
}