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


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SplashFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}