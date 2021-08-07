package com.graduation.softskillsmeter.ui.home.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.softskillsmeter.models.responses.QuestionsResponse
import com.graduation.softskillsmeter.networking.RetrofitClient
import com.graduation.softskillsmeter.networking.RetrofitService
import com.graduation.softskillsmeter.ui.home.states.RequestState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionViewModel : ViewModel() {
    private val _question: MutableLiveData<String> = MutableLiveData()
    private val _requestState: MutableLiveData<RequestState> = MutableLiveData(RequestState.NOT_STARTED)

    val question: MutableLiveData<String>
        get() = _question
    val requestState: MutableLiveData<RequestState>
        get() = _requestState

    var recordingStarted = false
    var questionNo = 1

    init {
        getQuestion()
    }

    private fun getQuestion() {
        _requestState.value = RequestState.LOADING

        val service = RetrofitClient.getRetrofit().create(RetrofitService::class.java)
        service.questions.enqueue(object :
            Callback<QuestionsResponse> {
            override fun onResponse(call: Call<QuestionsResponse>, response: Response<QuestionsResponse>) {
                if (response.isSuccessful) {
                    checkQuestionNo(questionNo, response.body())
                }

                Log.d("response", "Response: $response")
            }

            override fun onFailure(call: Call<QuestionsResponse>, t: Throwable) {
                _requestState.value = RequestState.FAIL
                Log.e("response", "Error ${t.message}")
            }
        })
    }

    private fun checkQuestionNo(no: Int, body: QuestionsResponse?) {
        when(no) {
            1 -> {
                _question.value = body?.first
            }
            2-> {
                _question.value = body?.second
            }
            3 -> {
                _question.value = body?.third
            }
            4-> {
                _question.value = body?.fourth
            }
        }

        _requestState.value = RequestState.SUCCESS
    }
}