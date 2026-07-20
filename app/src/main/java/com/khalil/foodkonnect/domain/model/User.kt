package com.khalil.foodkonnect.domain.model

data class User(
    val uid: String,
    val email: String,
    val name: String,
    val role: Role,
    val businessType: BusinessType?, // null if role is BUYER
    val phone: String?
)