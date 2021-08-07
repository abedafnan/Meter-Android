package com.graduation.softskillsmeter.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.graduation.softskillsmeter.HomeActivity
import com.graduation.softskillsmeter.R
import com.graduation.softskillsmeter.databinding.FragmentFeedbackBinding
import com.graduation.softskillsmeter.models.Interview
import com.graduation.softskillsmeter.models.requests.InterviewIdRequest
import com.graduation.softskillsmeter.ui.home.states.RequestState
import com.graduation.softskillsmeter.ui.home.viewmodels.FeedbackViewModel

class FeedbackFragment : Fragment() {

    private lateinit var binding: FragmentFeedbackBinding
    private lateinit var feedbackViewModel: FeedbackViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        feedbackViewModel = ViewModelProvider(this).get(FeedbackViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feedback, container, false)

        (activity as HomeActivity).showNavView(true)

        // TODO: replace with real data
        val qids = ArrayList<Int>()
        qids.add(1)
        qids.add(2)
        qids.add(3)
        qids.add(4)
        val answers = ArrayList<String>()
        answers.add("answer")
        answers.add("answer")
        answers.add("answer")
        answers.add("answer")
        val request = InterviewIdRequest("7lV4Dh1Ulk2vbYQwU1nL", qids, answers, "6-8-2021")

        feedbackViewModel.getInterviewId(request)

        feedbackViewModel.feedback.observe(viewLifecycleOwner) {
            if (it != null) {
                updateView(it)
            }
        }

        feedbackViewModel.requestState.observe(viewLifecycleOwner) {
            when(it) {
                RequestState.NOT_STARTED -> {
                }
                RequestState.LOADING ->  {
                    binding.progressbar.visibility = View.VISIBLE
                }
                RequestState.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                }
                RequestState.FAIL -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

    private fun updateView(interview: Interview) {
        val formattedScore = String.format("%.1f", interview.score)
        binding.tvFinalScore.text = formattedScore + "/10"

        binding.tvShare.visibility = View.VISIBLE

        updateCommunication(interview.feedback[0])
        updateGrowthMindset(interview.feedback[1])
        updateProblemSolving(interview.feedback[2])
        updateTeamWork(interview.feedback[3])
    }

    private fun updateCommunication(feedback: String) {
        binding.communication.root.visibility = View.VISIBLE
        binding.communication.tvSkillName.text = "Communication"
        binding.communication.tvFeedback.text = feedback
        binding.communication.tvViewQuestion.visibility = View.GONE
    }

    private fun updateGrowthMindset(feedback: String) {
        binding.growthMindset.root.visibility = View.VISIBLE
        binding.communication.tvSkillName.text = "Growth Mindset"
        binding.growthMindset.tvFeedback.text = feedback
        binding.growthMindset.tvViewQuestion.visibility = View.GONE
    }

    private fun updateProblemSolving(feedback: String) {
        binding.problemSolving.root.visibility = View.VISIBLE
        binding.communication.tvSkillName.text = "Problem Solving"
        binding.problemSolving.tvFeedback.text = feedback
        binding.problemSolving.tvViewQuestion.visibility = View.GONE
    }

    private fun updateTeamWork(feedback: String) {
        binding.teamwork.root.visibility = View.VISIBLE
        binding.communication.tvSkillName.text = "Teamwork"
        binding.teamwork.tvFeedback.text = feedback
        binding.teamwork.tvViewQuestion.visibility = View.GONE
    }
}