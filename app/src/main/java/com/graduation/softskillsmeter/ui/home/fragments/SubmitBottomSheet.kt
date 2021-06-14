package com.graduation.softskillsmeter.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.graduation.softskillsmeter.R
import kotlinx.android.synthetic.main.bottom_sheet_submit.*

class SubmitBottomSheet: BottomSheetDialogFragment() {
    private var questionNo: Int? = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_submit, container, false)

        if (arguments != null) {
            questionNo = arguments?.getInt("q_no")
        }

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

            questionNo?.let {
                val bundle = Bundle()
                bundle.putInt("q_no", it)
                findNavController().navigate(R.id.action_submitBottomSheet_to_questionFragment, bundle)
            }
        }
    }
}