package com.graduation.softskillsmeter.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.graduation.softskillsmeter.HomeActivity
import com.graduation.softskillsmeter.R
import com.graduation.softskillsmeter.databinding.FragmentInterviewDetailsBinding
import com.graduation.softskillsmeter.ui.home.viewmodels.FeedbackViewModel

class InterviewDetailsFragment : Fragment() {

    private lateinit var binding: FragmentInterviewDetailsBinding
    private lateinit var feedbackViewModel: FeedbackViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        feedbackViewModel =
            ViewModelProvider(this).get(FeedbackViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_interview_details, container, false)

        (activity as HomeActivity).showNavView(false)

        feedbackViewModel.feedback.observe(viewLifecycleOwner) {
//            if (it.isNotEmpty()) {
//                val adapter = FeedbackAdapter(it, true, this)
//                binding.recyclerFeedback.layoutManager =
//                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//                binding.recyclerFeedback.adapter = adapter
//            }
        }

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

//    override fun onItemClicked() {
//        findNavController().navigate(R.id.action_interviewDetailsFragment_to_answersFragment)
//    }
}