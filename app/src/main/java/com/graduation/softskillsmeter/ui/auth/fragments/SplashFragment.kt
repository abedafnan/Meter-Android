package com.graduation.softskillsmeter.ui.auth.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.graduation.softskillsmeter.R


class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        return view
    }

    override fun onResume() {
        super.onResume()

        Handler().postDelayed(this::navigateToLogin, 5000)
    }

    private fun navigateToLogin() {

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                requireActivity(), arrayOf(
                    Manifest.permission.RECORD_AUDIO
                ),
                REQUEST_AUDIO
            )
        } else {
            Log.i("testing", "PERMISSION GRANTED")
            findNavController().navigate(R.id.loginFragment)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_AUDIO) {
            if (permissions[0] == Manifest.permission.RECORD_AUDIO && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                findNavController().navigate(R.id.loginFragment)
            }
        }
    }

    companion object {
        const val REQUEST_AUDIO = 100
    }
}