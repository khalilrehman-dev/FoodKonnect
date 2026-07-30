package com.khalil.foodkonnect.ui.onboarding

import androidx.annotation.DrawableRes

// Represents one slide in the onboarding carousel.
data class OnboardingPage(
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String
)
