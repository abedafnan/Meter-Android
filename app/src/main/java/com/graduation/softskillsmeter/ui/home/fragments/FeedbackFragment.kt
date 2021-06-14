package com.graduation.softskillsmeter.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduation.softskillsmeter.HomeActivity
import com.graduation.softskillsmeter.R
import com.graduation.softskillsmeter.databinding.FragmentFeedbackBinding
import com.graduation.softskillsmeter.ui.home.adapters.FeedbackAdapter
import com.graduation.softskillsmeter.ui.home.viewmodels.FeedbackViewModel

class FeedbackFragment : Fragment() {

    private lateinit var binding: FragmentFeedbackBinding
    private lateinit var feedbackViewModel: FeedbackViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        feedbackViewModel =
            ViewModelProvider(this).get(FeedbackViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feedback, container, false)

        (activity as HomeActivity).showNavView(true)

        feedbackViewModel.feedbackList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val adapter = FeedbackAdapter(it, false, null)
                binding.recyclerFeedback.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.recyclerFeedback.adapter = adapter
            }
        }

        return binding.root
    }
}