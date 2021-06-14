package com.graduation.softskillsmeter.ui.home.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.softskillsmeter.models.Feedback

class FeedbackViewModel : ViewModel() {
    private val _feedbackList: MutableLiveData<ArrayList<Feedback>> = MutableLiveData()

    val feedbackList: MutableLiveData<ArrayList<Feedback>>
        get() = _feedbackList

    init {
        // Placeholder
        val list = ArrayList<Feedback>()
        list.add(Feedback(1, 9.0, "Teamwork", "This is feedback. This is feedback. This is feedback. "))
        list.add(Feedback(2, 6.3, "Communication", "This is feedback. This is feedback. This is feedback. "))
        list.add(Feedback(3, 5.5, "Problem Solving", "This is feedback. This is feedback. This is feedback. "))
        list.add(Feedback(4, 9.5, "Leadership", "This is feedback. This is feedback. This is feedback. "))

        _feedbackList.value = list
    }
}