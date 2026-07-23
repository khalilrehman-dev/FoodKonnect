package com.khalil.foodkonnect.data.model

import java.security.Timestamp

data class UserDto(
    val uid: String = "",
    val email: String = "",
    val name: String = "",
    val role: String = "",
    val businessType: String? = null,
    val phone: String? = null,
    val createdAt: Timestamp? = null
) {
    constructor() : this("", "", "", "", null, null, null)
}