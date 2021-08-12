package com.graduation.softskillsmeter.ui.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduation.softskillsmeter.HomeActivity
import com.graduation.softskillsmeter.R
import com.graduation.softskillsmeter.databinding.FragmentHomeBinding
import com.graduation.softskillsmeter.models.Interview
import com.graduation.softskillsmeter.ui.home.viewmodels.HomeViewModel
import com.graduation.softskillsmeter.ui.home.adapters.PreviousInterviewsAdapter
import com.graduation.softskillsmeter.ui.home.states.RequestState
import com.graduation.softskillsmeter.utils.SharedPreferenceUtils

class HomeFragment : Fragment(), PreviousInterviewsAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private var interviews: List<Interview>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        (activity as HomeActivity).showNavView(true)

        binding.btnStartInterview.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_instructionsFragment)
        }

        val username = SharedPreferenceUtils.getInstance(requireContext()).userName
        binding.tvGreeting.text = "Hi, $username!"
        val userId = SharedPreferenceUtils.getInstance(requireContext()).userId
        homeViewModel.getInterviews(userId)

        homeViewModel.previousInterviewsList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.emptyView.visibility = View.GONE
                interviews = it

                val adapter = PreviousInterviewsAdapter(it, this)
                binding.recyclerPreviousInterview.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
                binding.recyclerPreviousInterview.adapter = adapter
            } else {
                binding.emptyView.visibility = View.VISIBLE
            }
        }

        homeViewModel.requestState.observe(viewLifecycleOwner) {
            when (it) {
                RequestState.NOT_STARTED -> {
                }
                RequestState.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                RequestState.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                }
                RequestState.FAIL -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        return binding.root
    }

    override fun onItemClicked(position: Int) {
        if (!interviews.isNullOrEmpty()) {
            val interview: Interview = interviews!![position]
            val bundle = Bundle()
            bundle.putParcelable("interview", interview)

            findNavController().navigate(R.id.action_navigation_home_to_interviewDetailsFragment, bundle)
        }
    }
}