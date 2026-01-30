package com.curo.compliance.ui.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.curo.compliance.databinding.OnboardingPage1Binding
import com.curo.compliance.databinding.OnboardingPage2Binding
import com.curo.compliance.databinding.OnboardingPage3Binding

class OnboardingPageFragment : Fragment() {

    private var pageNumber: Int = 1

    companion object {
        private const val ARG_PAGE = "page_number"

        fun newInstance(page: Int): OnboardingPageFragment {
            val fragment = OnboardingPageFragment()
            val args = Bundle()
            args.putInt(ARG_PAGE, page)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = arguments?.getInt(ARG_PAGE) ?: 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return when (pageNumber) {
            1 -> {
                val binding = OnboardingPage1Binding.inflate(inflater, container, false)
                setupClickListener(binding.btnNext)
                binding.root
            }
            2 -> {
                val binding = OnboardingPage2Binding.inflate(inflater, container, false)
                setupClickListener(binding.btnNext)
                binding.root
            }
            3 -> {
                val binding = OnboardingPage3Binding.inflate(inflater, container, false)
                setupClickListener(binding.btnNext)
                binding.root
            }
            else -> {
                val binding = OnboardingPage1Binding.inflate(inflater, container, false)
                setupClickListener(binding.btnNext)
                binding.root
            }
        }
    }

    private fun setupClickListener(button: com.google.android.material.floatingactionbutton.FloatingActionButton) {
        button.setOnClickListener {
            (parentFragment as? LandingFragment)?.onNextClicked(pageNumber - 1)
        }
    }
}
