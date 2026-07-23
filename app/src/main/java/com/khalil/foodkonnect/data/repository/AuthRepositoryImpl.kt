package com.khalil.foodkonnect.data.repository

import com.khalil.foodkonnect.data.mapper.toDomain
import com.khalil.foodkonnect.data.mapper.toDto
import com.khalil.foodkonnect.data.model.UserDto
import com.khalil.foodkonnect.data.remote.FirebaseAuthSource
import com.khalil.foodkonnect.data.remote.UserFirestoreSource
import com.khalil.foodkonnect.domain.model.BusinessType
import com.khalil.foodkonnect.domain.model.Role
import com.khalil.foodkonnect.domain.model.User
import com.khalil.foodkonnect.domain.repository.AuthRepository
import javax.inject.Inject

// The real implementation of AuthRepository — this is the ONLY class in the app
// that knows both "Firebase" and "domain model" at the same time. Everything else
// either speaks Firebase (data/remote) or speaks domain (everywhere else).
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuthSource: FirebaseAuthSource,
    private val userFirestoreSource: UserFirestoreSource
) : AuthRepository {

    override suspend fun signUp(
        email: String,
        password: String,
        name: String,
        role: Role,
        businessType: BusinessType?
    ): Result<User> {
        return try {
            val uid = firebaseAuthSource.signUp(email, password)

            val dto = UserDto(
                uid = uid,
                email = email,
                name = name,
                role = role.name,
                businessType = businessType?.name,
                phone = null,
                createdAt = com.google.firebase.Timestamp.now()
            )
            userFirestoreSource.saveUser(dto)

            Result.success(dto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val uid = firebaseAuthSource.login(email, password)
            val dto = userFirestoreSource.getUser(uid)
                ?: throw IllegalStateException("User document not found for uid: $uid")
            Result.success(dto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCurrentUser(): User? {
        val uid = firebaseAuthSource.getCurrentUserId() ?: return null
        val dto = userFirestoreSource.getUser(uid) ?: return null
        return dto.toDomain()
    }

    override fun logout() {
        firebaseAuthSource.logout()
    }
}