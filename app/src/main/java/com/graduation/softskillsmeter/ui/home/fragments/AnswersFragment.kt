package com.graduation.softskillsmeter.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.graduation.softskillsmeter.HomeActivity
import com.graduation.softskillsmeter.R
import com.graduation.softskillsmeter.databinding.FragmentAnswersBinding

class AnswersFragment : Fragment(){

    private lateinit var binding: FragmentAnswersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_answers, container, false)

        (activity as HomeActivity).showNavView(false)

        val question = arguments?.getString("question")
        val answer = arguments?.getString("answer")
        val skill = arguments?.getString("skill")

        //TODO: Set skill score

        binding.tvHeaderTitle.text = "$skill Answers"
        binding.tvQuestion.text = question

        if(answer.isNullOrEmpty()) {
            binding.tvFeedback.text = "No Answer.."
        } else {
            binding.tvFeedback.text = answer
        }

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }
}