package com.khalil.foodkonnect.ui.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khalil.foodkonnect.databinding.ActivitySignUpBinding
import com.khalil.foodkonnect.ui.common.MapViewUtils
import org.osmdroid.views.MapView

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private var isBusinessRole = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRoleToggle()
        setupMap()

        binding.btnCreateAccount.setOnClickListener {
            // TODO: validate fields, then call AuthRepository.signUp via ViewModel
        }
    }

    private fun setupRoleToggle() {
        binding.btnRoleBuyer.setOnClickListener {
            isBusinessRole = false
            binding.groupBusinessFields.visibility = android.view.View.GONE
        }

        binding.btnRoleBusiness.setOnClickListener {
            isBusinessRole = true
            binding.groupBusinessFields.visibility = android.view.View.VISIBLE
        }
    }

    private fun setupMap() {
        // The <include> gives us access to the included layout's views via
        // binding.locationPicker, since it's a merge include with an id.
        val mapView = binding.locationPicker.root.findViewById<MapView>(
            com.khalil.foodkonnect.R.id.mapView
        )
        MapViewUtils.setupMapView(mapView)
    }

    override fun onResume() {
        super.onResume()
        // osmdroid requires explicit lifecycle calls to manage tile
        // downloading/caching correctly.
        binding.locationPicker.root.findViewById<MapView>(
            com.khalil.foodkonnect.R.id.mapView
        )?.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.locationPicker.root.findViewById<MapView>(
            com.khalil.foodkonnect.R.id.mapView
        )?.onPause()
    }
}