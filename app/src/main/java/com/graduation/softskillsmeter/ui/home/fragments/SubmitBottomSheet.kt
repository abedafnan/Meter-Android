package com.graduation.softskillsmeter.ui.home.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.graduation.softskillsmeter.R
import kotlinx.android.synthetic.main.bottom_sheet_submit.*

class SubmitBottomSheet: BottomSheetDialogFragment() {
    private lateinit var remainingCountDownTimer: CountDownTimer
    private var questionNo: Int? = 1
    private var remainingTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_submit, container, false)

        if (arguments != null) {
            questionNo = arguments?.getInt("q_no")
            remainingTime = arguments?.getLong("remaining")!!
        }

        remainingCountDownTimer = object : CountDownTimer(remainingTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var seconds = (millisUntilFinished / 1000)
                val minutes = seconds / 60
                seconds %= 60

                val timeFormatted = (String.format("%d:%02d", minutes, seconds))
                tv_time_left.text = resources.getString(R.string.time_left, timeFormatted)
            }

            override fun onFinish() {
                goToNextQuestion()
            }
        }.start()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        btn_resume.setOnClickListener {
            this.dismiss()
        }

        btn_submit.setOnClickListener {
            if (questionNo?.let { it1 -> (it1 == 5) } == true) {
                findNavController().navigate(R.id.action_submitBottomSheet_to_feedbackFragment)

                return@setOnClickListener
            }

            goToNextQuestion()
        }
    }

    private fun goToNextQuestion() {
        questionNo?.let {
            val bundle = Bundle()
            bundle.putInt("q_no", it)
            findNavController().navigate(R.id.action_submitBottomSheet_to_questionFragment, bundle)
        }
    }

    override fun onPause() {
        super.onPause()
        remainingCountDownTimer.cancel()
    }
}