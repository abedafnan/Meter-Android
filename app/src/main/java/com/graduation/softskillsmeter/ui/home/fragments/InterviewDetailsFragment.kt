package com.graduation.softskillsmeter.ui.home.fragments

import android.os.Bundle
import android.util.Log
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
import com.graduation.softskillsmeter.models.Feedback
import com.graduation.softskillsmeter.models.Interview
import com.graduation.softskillsmeter.ui.home.viewmodels.FeedbackViewModel
import com.graduation.softskillsmeter.utils.Utils.formatSkillScore

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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_interview_details, container, false)

        (activity as HomeActivity).showNavView(false)

        val interview = arguments?.getParcelable<Interview>("interview")
        if (interview != null) {
            updateView(interview)
        }

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    private fun updateView(interview: Interview) {
        val formattedScore = String.format("%.1f", (interview.score * 10))
        binding.tvFinalScore.text = "$formattedScore/10"

        interview.feedback?.let {
            if (interview.user_answers != null && interview.interview_questions != null) {
                updateCommunication(
                    it[0],
                    interview.user_answers[0],
                    interview.interview_questions[0]
                )
                updateGrowthMindset(
                    it[1],
                    interview.user_answers[1],
                    interview.interview_questions[1]
                )
                updateProblemSolving(
                    it[2],
                    interview.user_answers[2],
                    interview.interview_questions[2]
                )
                updateTeamWork(it[3], interview.user_answers[3], interview.interview_questions[3])
            }
        }
    }

    private fun updateCommunication(feedback: Feedback, answer: String, question: String) {
        val skill = "Communication"
        binding.communication.root.visibility = View.VISIBLE
        binding.communication.tvSkillName.text = skill
        binding.communication.tvFeedback.text = feedback.feedback
        binding.communication.tvSkillScore.text = formatSkillScore(feedback.skill_score)
        binding.communication.tvViewQuestion.setOnClickListener {
            goToDetails(question, answer, skill)
        }
    }

    private fun updateGrowthMindset(feedback: Feedback, answer: String, question: String) {
        val skill = "Growth Mindset"
        binding.growthMindset.root.visibility = View.VISIBLE
        binding.growthMindset.tvSkillName.text = skill
        binding.growthMindset.tvFeedback.text = feedback.feedback
        binding.growthMindset.tvSkillScore.text = formatSkillScore(feedback.skill_score)
        binding.growthMindset.tvViewQuestion.setOnClickListener {
            goToDetails(question, answer, skill)
        }
    }

    private fun updateProblemSolving(feedback: Feedback, answer: String, question: String) {
        val skill = "Problem Solving"
        binding.problemSolving.root.visibility = View.VISIBLE
        binding.problemSolving.tvSkillName.text = skill
        binding.problemSolving.tvFeedback.text = feedback.feedback
        binding.problemSolving.tvSkillScore.text = formatSkillScore(feedback.skill_score)
        binding.problemSolving.tvViewQuestion.setOnClickListener {
            goToDetails(question, answer, skill)
        }
    }

    private fun updateTeamWork(feedback: Feedback, answer: String, question: String) {
        val skill = "Teamwork"
        binding.teamwork.root.visibility = View.VISIBLE
        binding.teamwork.tvSkillName.text = skill
        binding.teamwork.tvFeedback.text = feedback.feedback
        binding.teamwork.tvSkillScore.text = formatSkillScore(feedback.skill_score)
        binding.teamwork.tvViewQuestion.setOnClickListener {
            goToDetails(question, answer, skill)
        }
    }

    private fun goToDetails(question: String, answer: String, skill: String) {
        val bundle = Bundle()
        bundle.putString("question", question)
        bundle.putString("answer", answer)
        bundle.putString("skill", skill)
        findNavController().navigate(R.id.action_interviewDetailsFragment_to_answersFragment, bundle)
    }
}