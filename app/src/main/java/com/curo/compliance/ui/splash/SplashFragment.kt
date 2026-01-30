package com.curo.compliance.ui.splash

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.curo.compliance.R
import com.curo.compliance.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up video from raw folder
        val videoUri = Uri.parse("android.resource://${requireActivity().packageName}/${R.raw.splash_video}")
        binding.splashVideo.setVideoURI(videoUri)

        // Start playing the video
        binding.splashVideo.start()

        // Navigate after 3 seconds (matching video length) to avoid timing issues
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            if (isAdded) {
                binding.splashVideo.stopPlayback()
                checkUserLoginStatus()
            }
        }, 3000)
    }

    private fun checkUserLoginStatus() {
        val sharedPreferences = requireActivity().getSharedPreferences("CuraPrefs", android.content.Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // User is logged in, navigate to home
            findNavController().navigate(R.id.action_splash_to_home)
        } else {
            // New user, navigate to landing
            findNavController().navigate(R.id.action_splash_to_landing)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
