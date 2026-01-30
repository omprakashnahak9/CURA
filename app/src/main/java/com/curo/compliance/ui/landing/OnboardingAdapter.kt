package com.curo.compliance.ui.landing

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingPageFragment.newInstance(1)
            1 -> OnboardingPageFragment.newInstance(2)
            2 -> OnboardingPageFragment.newInstance(3)
            else -> OnboardingPageFragment.newInstance(1)
        }
    }
}
