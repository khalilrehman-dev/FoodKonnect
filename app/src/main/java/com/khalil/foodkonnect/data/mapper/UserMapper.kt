package com.khalil.foodkonnect.data.mapper

import com.khalil.foodkonnect.data.model.UserDto
import com.khalil.foodkonnect.domain.model.BusinessType
import com.khalil.foodkonnect.domain.model.Role
import com.khalil.foodkonnect.domain.model.User

// Converts the raw Firestore shape (UserDto) into the clean domain model (User).
// This is where "String" becomes "Role" — the boundary where messy data gets validated.
fun UserDto.toDomain(): User {
    return User(
        uid = uid,
        email = email,
        name = name,
        role = Role.valueOf(role),                 // throws if Firestore has bad data — acceptable for now
        businessType = businessType?.let { BusinessType.valueOf(it) },
        phone = phone
    )
}

// Converts the domain model back into Firestore's expected shape, e.g. when writing a new user.
fun User.toDto(): UserDto {
    return UserDto(
        uid = uid,
        email = email,
        name = name,
        role = role.name,                          // enum -> String for storage
        businessType = businessType?.name,
        phone = phone
    )
}