package com.graduation.softskillsmeter.ui.home.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.softskillsmeter.models.Instruction

class InstructionsViewModel : ViewModel() {
    private val _instructionsList: MutableLiveData<ArrayList<Instruction>> = MutableLiveData()

    val instructionsList: MutableLiveData<ArrayList<Instruction>>
        get() = _instructionsList

    init {
        // Placeholder
        val list = ArrayList<Instruction>()
        list.add(Instruction(1, "The display of questions is sequential, the user cannot return to the question again."))
        list.add(Instruction(2, "It is not possible to re-record again."))
        list.add(Instruction(3, "There is a set time for each question"))
        list.add(Instruction(4, "The answers are in English language only."))
        list.add(Instruction(5, "Make sure your microphone is working properly"))
        list.add(Instruction(6, "Make sure you're in a quite environment and your voice is clear."))
        list.add(Instruction(7, "Please do not use outside assistance."))

        _instructionsList.value = list
    }
}