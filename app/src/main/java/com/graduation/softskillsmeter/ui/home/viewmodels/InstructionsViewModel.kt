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
        list.add(Instruction(1, "This is instruction text"))
        list.add(Instruction(2, "This is instruction text"))
        list.add(Instruction(3, "This is instruction text"))
        list.add(Instruction(4, "This is instruction text"))
        list.add(Instruction(5, "This is instruction text"))
        list.add(Instruction(6, "This is instruction text"))
        list.add(Instruction(7, "This is instruction text"))
        list.add(Instruction(8, "This is instruction text"))
        list.add(Instruction(9, "This is instruction text"))

        _instructionsList.value = list
    }
}