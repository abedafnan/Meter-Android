package com.graduation.softskillsmeter.ui.home.fragments

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.RecognizerIntent
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.graduation.softskillsmeter.R
import com.graduation.softskillsmeter.databinding.FragmentQuestionBinding
import com.graduation.softskillsmeter.ui.home.states.RequestState
import com.graduation.softskillsmeter.ui.home.viewmodels.QuestionViewModel
import com.graduation.softskillsmeter.utils.SharedPreferenceUtils

class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestionBinding
    private lateinit var viewModel: QuestionViewModel
    private lateinit var recordCountDownTimer: CountDownTimer
    private lateinit var questionCountDownTimer: CountDownTimer
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var remainingTime: Long = 0
    private lateinit var answer: String
    private var submitted: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)

        if (arguments != null) {
            viewModel.questionNo = arguments?.getInt("q_no")!!
        }

        fillQuestionProgress()
        setHeaderTitle()

        binding.btnRecord.setOnClickListener {
            recordCountDownTimer.cancel()

            if (!viewModel.recordingStarted) {
                startRecording()
            } else {
                viewModel.questionNo.let {
                    val bundle = Bundle()
                    bundle.putInt("q_no", it + 1)
                    bundle.putLong("remaining", remainingTime)
                    submitted = true
                    findNavController().navigate(R.id.action_questionFragment_to_submitBottomSheet, bundle)
                }
            }
        }

        viewModel.requestState.observe(viewLifecycleOwner) {
            when(it) {
                RequestState.NOT_STARTED -> {
                }
                RequestState.LOADING ->  {
                    binding.tvQuestion.text = ""
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

        viewModel.question.observe(viewLifecycleOwner) {
            binding.tvQuestion.text = it
            startCountDown()
        }

        submitted = false
        answer = ""
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data: Intent? = result.data
                data?.let {
                    val result = it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    if (result != null) {
                        for (item in result) {
                            setAnswer(item)
                        }
                    }
                }
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (submitted) {
//            startRecordingIntent()
        }

        this.requireView().isFocusableInTouchMode = true
        this.requireView().requestFocus()
        this.requireView().setOnKeyListener { _, keyCode, _ ->
            keyCode == KeyEvent.KEYCODE_BACK
        }
    }

    override fun onPause() {
        super.onPause()

        // FIXME: Continue if user doesn't submit
        if (viewModel.questionNo == 4) {
            questionCountDownTimer.cancel()
            Log.d("testing", "timer cancelled")
        }
    }

    private fun startCountDown() {
        recordCountDownTimer = object : CountDownTimer(15000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.tvCountDown.text = ("${millisUntilFinished / 1000}")
            }

            override fun onFinish() {
                startRecording()
            }
        }.start()
    }

    private fun startRecording() {
        changeViews()

        questionCountDownTimer = object : CountDownTimer((3 * 60 * 1000), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                remainingTime = millisUntilFinished

                var seconds = (millisUntilFinished / 1000)
                val minutes = seconds / 60
                seconds %= 60

                binding.tvCountDown.text = (String.format("%d:%02d", minutes, seconds))
            }

            override fun onFinish() {
                viewModel.questionNo.let {
                    val bundle = Bundle()
                    bundle.putInt("q_no", it + 1)
                    findNavController().navigate(R.id.action_questionFragment_self, bundle)
                }
            }
        }.start()

        startRecordingIntent()
        viewModel.recordingStarted = true
    }

    private fun startRecordingIntent() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        try {
            resultLauncher.launch(intent)
        } catch (a: ActivityNotFoundException) {
            Log.e("testing", "Intent activity not found")
        }
    }

    private fun changeViews() {
        binding.btnRecord.text = getString(R.string.submit)
        binding.tvRecordedAfter.text = getString(R.string.remaining_time)
        binding.tvOr.visibility = View.GONE
        binding.layoutRecording.visibility = View.VISIBLE
    }

    private fun setHeaderTitle() {
        when(viewModel.questionNo) {
            2 -> binding.tvHeaderTitle.text = "Question 2"
            3 -> binding.tvHeaderTitle.text = "Question 3"
            4 -> binding.tvHeaderTitle.text = "Question 4"
        }
    }

    private fun setAnswer(answer: String) {
        this.answer += answer
        val sp = SharedPreferenceUtils.getInstance(requireContext())
        when(viewModel.questionNo) {
            1 -> sp.setAnswer1(answer)
            2 -> sp.setAnswer2(answer)
            3 -> sp.setAnswer3(answer)
            4 -> sp.setAnswer4(answer)
        }
    }

    private fun fillQuestionProgress() {
        when(viewModel.questionNo) {
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