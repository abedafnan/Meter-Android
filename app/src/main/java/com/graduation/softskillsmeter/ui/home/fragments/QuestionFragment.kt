package com.graduation.softskillsmeter.ui.home.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.graduation.softskillsmeter.R
import com.graduation.softskillsmeter.databinding.FragmentQuestionBinding
import com.graduation.softskillsmeter.ui.home.viewmodels.QuestionViewModel

class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestionBinding
    private lateinit var viewModel: QuestionViewModel
    private var questionNo: Int? = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(QuestionViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)

        if (arguments != null) {
            questionNo = arguments?.getInt("q_no")
        }

        fillQuestionProgress()
        setHeaderTitle()

        binding.btnRecord.setOnClickListener {
            if (!viewModel.recordingStarted) {
                changeViews()
                //TODO: Start Recording
                viewModel.recordingStarted = true
            } else {
                if (questionNo?.let { it1 -> (it1 == 4) } == true) {
                    findNavController().navigate(R.id.action_questionFragment_to_feedbackFragment)

                    return@setOnClickListener
                }

                questionNo?.let {
                    val bundle = Bundle()
                    bundle.putInt("q_no", it + 1)
                    findNavController().navigate(R.id.action_questionFragment_to_submitBottomSheet, bundle)
                }
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        this.requireView().isFocusableInTouchMode = true
        this.requireView().requestFocus()
        this.requireView().setOnKeyListener { _, keyCode, _ ->
            keyCode == KeyEvent.KEYCODE_BACK
        }
    }

    private fun changeViews() {
        binding.btnRecord.text = getString(R.string.submit)
        binding.tvRecordedAfter.text = getString(R.string.remaining_time)
        binding.tvOr.visibility = View.GONE
    }

    private fun setHeaderTitle() {
        when(questionNo) {
            2 -> binding.tvHeaderTitle.text = "Question 2"
            3 -> binding.tvHeaderTitle.text = "Question 3"
            4 -> binding.tvHeaderTitle.text = "Question 4"
        }
    }

    private fun fillQuestionProgress() {
        when(questionNo) {
            2 -> {
                ViewCompat.setBackgroundTintList(
                    binding.fill1,
                    ColorStateList.valueOf(resources.getColor(R.color.colorPrimary)))
                ViewCompat.setBackgroundTintList(
                    binding.fill2,
                    ColorStateList.valueOf(resources.getColor(R.color.colorPrimary)))
            }
            3 -> {
                ViewCompat.setBackgroundTintList(
                    binding.fill1,
                    ColorStateList.valueOf(resources.getColor(R.color.colorPrimary)))
                ViewCompat.setBackgroundTintList(
                    binding.fill2,
                    ColorStateList.valueOf(resources.getColor(R.color.colorPrimary)))
                ViewCompat.setBackgroundTintList(
                    binding.fill3,
                    ColorStateList.valueOf(resources.getColor(R.color.colorPrimary)))
            }
            4 -> {
                ViewCompat.setBackgroundTintList(
                    binding.fill1,
                    ColorStateList.valueOf(resources.getColor(R.color.colorPrimary)))
                ViewCompat.setBackgroundTintList(
                    binding.fill2,
                    ColorStateList.valueOf(resources.getColor(R.color.colorPrimary)))
                ViewCompat.setBackgroundTintList(
                    binding.fill3,
                    ColorStateList.valueOf(resources.getColor(R.color.colorPrimary)))
                ViewCompat.setBackgroundTintList(
                    binding.fill4,
                    ColorStateList.valueOf(resources.getColor(R.color.colorPrimary)))
            }
        }
    }
}