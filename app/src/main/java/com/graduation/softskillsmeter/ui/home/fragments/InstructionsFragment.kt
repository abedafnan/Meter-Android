package com.graduation.softskillsmeter.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduation.softskillsmeter.HomeActivity
import com.graduation.softskillsmeter.R
import com.graduation.softskillsmeter.databinding.FragmentInterviewInstructionsBinding
import com.graduation.softskillsmeter.ui.home.adapters.InstructionsAdapter
import com.graduation.softskillsmeter.ui.home.viewmodels.InstructionsViewModel

class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInterviewInstructionsBinding
    private lateinit var instructionsViewModel: InstructionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        instructionsViewModel =
            ViewModelProvider(this).get(InstructionsViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_interview_instructions, container, false)

        (activity as HomeActivity).showNavView(false)

        instructionsViewModel.instructionsList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val adapter = InstructionsAdapter(it)
                binding.recyclerInstructions.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.recyclerInstructions.adapter = adapter
            }
        }

        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_instructionsFragment_to_questionFragment)
        }

        return binding.root
    }
}