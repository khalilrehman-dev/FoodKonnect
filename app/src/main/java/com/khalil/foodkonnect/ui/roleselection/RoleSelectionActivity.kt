package com.khalil.foodkonnect.ui.roleselection

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khalil.foodkonnect.R
import com.khalil.foodkonnect.databinding.ActivityRoleSelectionBinding

class RoleSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoleSelectionBinding

    // Tracks which role the user has tapped — null means nothing selected yet.
    private var selectedRole: SelectedRole? = null

    private enum class SelectedRole { BUYER, BUSINESS }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoleSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardBuyer.setOnClickListener {
            selectRole(SelectedRole.BUYER)
        }

        binding.cardBusiness.setOnClickListener {
            selectRole(SelectedRole.BUSINESS)
        }

        binding.btnContinue.setOnClickListener {
            goToSignUp()
        }
    }

    private fun selectRole(role: SelectedRole) {
        selectedRole = role

        // Highlight selected card with amber stroke, reset the other to default.
        val amber = getColor(R.color.accent_amber)
        val defaultStroke = getColor(R.color.divider)

        binding.cardBuyer.strokeColor = if (role == SelectedRole.BUYER) amber else defaultStroke
        binding.cardBusiness.strokeColor = if (role == SelectedRole.BUSINESS) amber else defaultStroke

        binding.btnContinue.isEnabled = true
    }

    private fun goToSignUp() {
        val role = selectedRole ?: return
        val intent = Intent(this, com.khalil.foodkonnect.ui.signup.SignUpActivity::class.java)
        // TODO: pass role as an intent extra once SignUpActivity reads it to preset the toggle
        startActivity(intent)
    }
}