package com.khalil.foodkonnect.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.khalil.foodkonnect.data.model.UserDto
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

// Thin wrapper around Firestore calls specific to the "users" collection.
// Keeps raw Firestore query/document syntax out of the repository layer.
class UserFirestoreSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val usersCollection = firestore.collection("users")

    suspend fun saveUser(dto: UserDto) {
        // .set() overwrites the whole document at this UID - fine for initial creation
        usersCollection.document(dto.uid).set(dto).await()
    }

    suspend fun getUser(uid: String): UserDto? {
        val snapshot = usersCollection.document(uid).get().await()
        // toObject() uses the no-arg constructor + field names to deserialize
        return snapshot.toObject(UserDto::class.java)
    }
}