package com.curo.compliance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.navigation.fragment.NavHostFragment
import android.graphics.Color
import com.curo.compliance.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable edge-to-edge display
        androidx.core.view.WindowCompat.setDecorFitsSystemWindows(window, false)
        window.navigationBarColor = Color.TRANSPARENT

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navViewCustom = binding.navViewCustom
        
        // Handle Edge-to-Edge Insets for Nav Bar
        val originalBottomMargin = (12 * resources.displayMetrics.density).toInt()
        ViewCompat.setOnApplyWindowInsetsListener(navViewCustom) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<android.view.ViewGroup.MarginLayoutParams> {
                bottomMargin = originalBottomMargin + insets.bottom
            }
            windowInsets
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        
        // Setup Custom Nav Click Listeners
        binding.navHome.setOnClickListener {
            if (navController.currentDestination?.id != R.id.navigation_home) {
                navController.navigate(R.id.navigation_home)
            }
        }

        binding.navHire.setOnClickListener {
            if (navController.currentDestination?.id != R.id.navigation_caregiver) {
                navController.navigate(R.id.navigation_caregiver)
            }
        }

        binding.navMedicine.setOnClickListener {
            if (navController.currentDestination?.id != R.id.navigation_medicine) {
                navController.navigate(R.id.navigation_medicine)
            }
        }
        
        binding.navProfile.setOnClickListener {
            if (navController.currentDestination?.id != R.id.navigation_profile) {
                navController.navigate(R.id.navigation_profile)
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Reset all icons to unselected state
            resetNavIcons()

            when (destination.id) {
                R.id.navigation_splash,
                R.id.navigation_landing,
                R.id.navigation_login,
                R.id.navigation_otp,
                R.id.navigation_userDetails,
                R.id.navigation_caregiver_details,
                R.id.navigation_appointment,
                R.id.navigation_sop_selection -> {
                    navViewCustom.visibility = android.view.View.GONE
                    supportActionBar?.hide()
                }
                R.id.navigation_home -> {
                    navViewCustom.visibility = android.view.View.VISIBLE
                    supportActionBar?.hide()
                    binding.navHome.setBackgroundResource(R.drawable.bg_nav_item_selected)
                }
                R.id.navigation_caregiver -> {
                    navViewCustom.visibility = android.view.View.VISIBLE
                    supportActionBar?.hide()
                    binding.navHire.setBackgroundResource(R.drawable.bg_nav_item_selected)
                }
                R.id.navigation_medicine -> {
                    navViewCustom.visibility = android.view.View.VISIBLE
                    supportActionBar?.hide()
                    binding.navMedicine.setBackgroundResource(R.drawable.bg_nav_item_selected)
                }
                R.id.navigation_profile -> {
                    navViewCustom.visibility = android.view.View.VISIBLE
                    supportActionBar?.hide()
                    binding.navProfile.setBackgroundResource(R.drawable.bg_nav_item_selected)
                }
                else -> {
                    navViewCustom.visibility = android.view.View.VISIBLE
                    supportActionBar?.show()
                }
            }
        }
    }

    private fun resetNavIcons() {
        binding.navHome.setBackgroundResource(R.drawable.bg_nav_item_unselected)
        binding.navHire.setBackgroundResource(R.drawable.bg_nav_item_unselected)
        binding.navMedicine.setBackgroundResource(R.drawable.bg_nav_item_unselected)
        binding.navProfile.setBackgroundResource(R.drawable.bg_nav_item_unselected)
    }
}
