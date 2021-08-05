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
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

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
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.getLoginFinished().observe(viewLifecycleOwner) {
            if (it) {
                startActivity(Intent(requireActivity(), HomeActivity::class.java))
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Wrong email or password!", Toast.LENGTH_SHORT).show()
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