package com.khalil.foodkonnect.domain.repository

import com.khalil.foodkonnect.domain.model.User

// This interface defines WHAT auth can do — no Firebase, no Firestore mentioned here.
// The domain layer should never know or care that Firebase is the implementation.
// This is what makes the code testable: in a unit test, you can fake this interface
// without ever touching a real Firebase instance.
interface AuthRepository {

    suspend fun signUp(
        email: String,
        password: String,
        name: String,
        role: com.khalil.foodkonnect.domain.model.Role,
        businessType: com.khalil.foodkonnect.domain.model.BusinessType?
    ): Result<User>

    suspend fun login(email: String, password: String): Result<User>

    suspend fun getCurrentUser(): User?

    fun logout()
}