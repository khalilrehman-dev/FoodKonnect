package com.khalil.foodkonnect.data.remote

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

// Thin wrapper around FirebaseAuth's raw SDK calls.
// Purpose: isolate Firebase's API so AuthRepositoryImpl doesn't call FirebaseAuth directly.
// If we ever swap auth providers, only this class changes.
class FirebaseAuthSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    suspend fun signUp(email: String, password: String): String {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return result.user?.uid ?: throw IllegalStateException("Sign up succeeded but UID is null")
    }

    suspend fun login(email: String, password: String): String {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user?.uid ?: throw IllegalStateException("Login succeeded but UID is null")
    }

    fun getCurrentUserId(): String? = firebaseAuth.currentUser?.uid

    fun logout() = firebaseAuth.signOut()
}