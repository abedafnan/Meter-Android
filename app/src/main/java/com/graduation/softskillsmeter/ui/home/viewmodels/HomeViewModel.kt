package com.graduation.softskillsmeter.ui.home.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.softskillsmeter.models.Interview

class HomeViewModel : ViewModel() {
    private val _previousInterviewsList: MutableLiveData<ArrayList<Interview>> = MutableLiveData()

    val previousInterviewsList: MutableLiveData<ArrayList<Interview>>
        get() = _previousInterviewsList

    init {
        // Placeholder
        val list = ArrayList<Interview>()
        list.add(Interview("June 1st 2021", 7.0))
        list.add(Interview("June 1st 2021", 7.0))
        list.add(Interview("June 1st 2021", 7.0))
        list.add(Interview("June 1st 2021", 7.0))
        list.add(Interview("June 1st 2021", 7.0))

        _previousInterviewsList.value = list
    }
}