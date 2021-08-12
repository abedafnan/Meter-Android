package com.graduation.softskillsmeter.ui.auth.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.graduation.softskillsmeter.HomeActivity
import com.graduation.softskillsmeter.R
import com.graduation.softskillsmeter.databinding.FragmentLoginBinding
import com.graduation.softskillsmeter.ui.auth.viewmodels.LoginViewModel
import com.graduation.softskillsmeter.ui.auth.viewmodels.RegisterViewModel
import com.graduation.softskillsmeter.utils.AESEncyption.encrypt
import com.graduation.softskillsmeter.utils.SharedPreferenceUtils
import com.graduation.softskillsmeter.utils.ValidationUtils

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.getUserId().observe(viewLifecycleOwner) {
            SharedPreferenceUtils.getInstance(requireContext()).userId = it
        }

        viewModel.getUsername().observe(viewLifecycleOwner) {
            SharedPreferenceUtils.getInstance(requireContext()).userName = it
        }

        viewModel.getLoginFinished().observe(viewLifecycleOwner) {
            if (it) {
                startActivity(Intent(requireActivity(), HomeActivity::class.java))
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Wrong email or password!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()

            if (ValidationUtils.validateSignIn(requireContext(), email, password)) {
                viewModel.onLoginTapped(email, password)
            }
        }

        binding.tvHeaderTitle.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }
}