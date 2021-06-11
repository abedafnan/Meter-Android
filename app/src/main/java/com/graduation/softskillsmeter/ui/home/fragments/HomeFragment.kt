package com.graduation.softskillsmeter.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduation.softskillsmeter.HomeActivity
import com.graduation.softskillsmeter.R
import com.graduation.softskillsmeter.databinding.FragmentHomeBinding
import com.graduation.softskillsmeter.ui.home.viewmodels.HomeViewModel
import com.graduation.softskillsmeter.ui.home.adapters.PreviousInterviewsAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeFragment : Fragment(), PreviousInterviewsAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

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

        homeViewModel.previousInterviewsList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val adapter = PreviousInterviewsAdapter(it, this)
                binding.recyclerPreviousInterview.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.recyclerPreviousInterview.adapter = adapter
            }
        }

        return binding.root
    }

    override fun onItemClicked() {
        findNavController().navigate(R.id.action_navigation_home_to_interviewDetailsFragment)
    }
}