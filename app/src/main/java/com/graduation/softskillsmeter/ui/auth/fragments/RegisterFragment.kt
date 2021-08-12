package com.graduation.softskillsmeter.ui.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.graduation.softskillsmeter.databinding.FragmentRegisterBinding
import com.graduation.softskillsmeter.ui.auth.viewmodels.RegisterViewModel
import com.graduation.softskillsmeter.utils.ValidationUtils

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val confirmPass = binding.etConfirmPass.text.toString()

            if (ValidationUtils.validateSignUp(requireContext(), email, password, firstName, lastName, confirmPass)) {
                viewModel.onRegisterTapped(email, password, firstName, lastName)
            }
        }

        binding.tvHeaderTitle.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getRegisterFinished().observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }

        return binding.root
    }
}