package com.khalil.foodkonnect.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.khalil.foodkonnect.R
import com.khalil.foodkonnect.databinding.ActivityOnboardingBinding
import com.khalil.foodkonnect.ui.roleselection.RoleSelectionActivity

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    private val pages = listOf(
        OnboardingPage(
            imageRes = R.mipmap.ic_launcher_foreground, // placeholder, swap with onboarding_discover.png
            title = "Discover Local Kitchens",
            description = "Explore home chefs and restaurants near you, all in one place."
        ),
        OnboardingPage(
            imageRes = R.mipmap.ic_launcher_foreground, // placeholder, swap with onboarding_local_kitchens.png
            title = "Support Local Cooks",
            description = "Order fresh, homemade meals directly from kitchens in your community."
        ),
        OnboardingPage(
            imageRes = R.mipmap.ic_launcher_foreground, // placeholder, swap with onboarding_delivery.png
            title = "Fast, Reliable Delivery",
            description = "Track your order in real time, from the kitchen to your door."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OnboardingPagerAdapter(pages)
        binding.viewPager.adapter = adapter

        // Connects the dots indicator to the ViewPager2's current page automatically.
        TabLayoutMediator(binding.dotsIndicator, binding.viewPager) { tab, _ ->
            // no text/icon needed — dots are styled purely via the selector drawable below
        }.attach()

        binding.tvSkip.setOnClickListener {
            goToRoleSelection()
        }

        binding.btnNext.setOnClickListener {
            val nextItem = binding.viewPager.currentItem + 1
            if (nextItem < pages.size) {
                binding.viewPager.currentItem = nextItem
            } else {
                goToRoleSelection()
            }
        }

        // Update button label to "Get Started" on the last page.
        binding.viewPager.registerOnPageChangeCallback(object :
            androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.btnNext.text = if (position == pages.size - 1) {
                    getString(R.string.get_started)
                } else {
                    getString(R.string.next)
                }
            }
        })
    }

    private fun goToRoleSelection() {
        startActivity(Intent(this, RoleSelectionActivity::class.java))
        finish()
    }
}