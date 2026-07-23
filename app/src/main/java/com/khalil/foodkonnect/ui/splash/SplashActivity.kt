package com.khalil.foodkonnect.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.khalil.foodkonnect.R
import com.khalil.foodkonnect.ui.onboarding.OnboardingActivity

// Splash screen: shown briefly on app launch, then navigates onward.
// Using a plain Activity + Handler delay (not the Android 12+ system splash
// screen API) so we keep full control over the dark theme styling here.
class SplashActivity : AppCompatActivity() {

    private val splashDelayMillis = 1800L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            navigateNext()
        }, splashDelayMillis)
    }

    private fun navigateNext() {
        // TODO: once AuthRepository.getCurrentUser() is wired to a ViewModel here,
        // check session first — if logged in, skip straight to the correct home
        // screen based on role. For now, always go to Onboarding.
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
        finish()
    }
}