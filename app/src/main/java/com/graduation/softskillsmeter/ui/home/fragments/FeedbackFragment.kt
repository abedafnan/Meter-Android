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
import com.graduation.softskillsmeter.models.Feedback
import com.graduation.softskillsmeter.models.Interview
import com.graduation.softskillsmeter.models.requests.InterviewIdRequest
import com.graduation.softskillsmeter.ui.home.states.RequestState
import com.graduation.softskillsmeter.ui.home.viewmodels.FeedbackViewModel
import com.graduation.softskillsmeter.utils.SharedPreferenceUtils
import com.graduation.softskillsmeter.utils.Utils
import com.graduation.softskillsmeter.utils.Utils.formatSkillScore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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

        // Set question IDs
        val qids = ArrayList<Int>()
        qids.add(1)
        qids.add(2)
        qids.add(3)
        qids.add(4)

        // Set answers
        val sp = SharedPreferenceUtils.getInstance(requireContext())
        val userId = sp.userId
        val answers = sp.answers

        // Set date and time
        val dateTime = setCurrentDateTime()

        val request = InterviewIdRequest(userId, qids, answers, dateTime)

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

    private fun setCurrentDateTime() : String {
        val c: Calendar = Calendar.getInstance()
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm")

        return df.format(c.time)
    }

    private fun updateView(interview: Interview) {
        val formattedScore = String.format("%.1f", (interview.score * 10))
        binding.tvFinalScore.text = "$formattedScore/10"

        binding.tvShare.visibility = View.VISIBLE

        interview.feedback?.let {
            updateCommunication(it[0])
            updateGrowthMindset(it[1])
            updateProblemSolving(it[2])
            updateTeamWork(it[3])
        }
    }

    private fun updateCommunication(feedback: Feedback) {
        binding.communication.root.visibility = View.VISIBLE
        binding.communication.tvSkillName.text = "Communication"
        binding.communication.tvFeedback.text = feedback.feedback
        binding.communication.tvSkillScore.text = formatSkillScore(feedback.skill_score)
        binding.communication.tvViewQuestion.visibility = View.GONE
    }

    private fun updateGrowthMindset(feedback: Feedback) {
        binding.growthMindset.root.visibility = View.VISIBLE
        binding.growthMindset.tvSkillName.text = "Growth Mindset"
        binding.growthMindset.tvFeedback.text = feedback.feedback
        binding.growthMindset.tvSkillScore.text = formatSkillScore(feedback.skill_score)
        binding.growthMindset.tvViewQuestion.visibility = View.GONE
    }

    private fun updateProblemSolving(feedback: Feedback) {
        binding.problemSolving.root.visibility = View.VISIBLE
        binding.problemSolving.tvSkillName.text = "Problem Solving"
        binding.problemSolving.tvFeedback.text = feedback.feedback
        binding.problemSolving.tvSkillScore.text = formatSkillScore(feedback.skill_score)
        binding.problemSolving.tvViewQuestion.visibility = View.GONE
    }

    private fun updateTeamWork(feedback: Feedback) {
        binding.teamwork.root.visibility = View.VISIBLE
        binding.teamwork.tvSkillName.text = "Teamwork"
        binding.teamwork.tvFeedback.text = feedback.feedback
        binding.teamwork.tvSkillScore.text = formatSkillScore(feedback.skill_score)
        binding.teamwork.tvViewQuestion.visibility = View.GONE
    }
}