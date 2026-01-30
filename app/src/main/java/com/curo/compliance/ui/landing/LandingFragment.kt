package com.curo.compliance.ui.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.curo.compliance.R
import com.curo.compliance.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {

    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup ViewPager
        val adapter = OnboardingAdapter(this)
        binding.viewPager.adapter = adapter

        // Skip button
        binding.tvSkip.setOnClickListener {
            findNavController().navigate(R.id.action_landing_to_login)
        }
    }

    fun onNextClicked(currentPage: Int) {
        if (currentPage < 2) {
            binding.viewPager.currentItem = currentPage + 1
        } else {
            // Last page, go to login
            findNavController().navigate(R.id.action_landing_to_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
