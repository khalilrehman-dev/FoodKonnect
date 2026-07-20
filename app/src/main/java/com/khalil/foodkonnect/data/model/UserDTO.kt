package com.khalil.foodkonnect.data.model

// Firestore needs a no-arg constructor and mutable/nullable fields
// to deserialize documents automatically — that's why this looks
// "looser" than the domain User model.
data class UserDto(
    val uid: String = "",
    val email: String = "",
    val name: String = "",
    val role: String = "",          // stored as raw String in Firestore
    val businessType: String? = null,
    val phone: String? = null,
    val createdAt: com.google.firebase.Timestamp? = null
) {
    // Empty constructor Firestore's deserializer sometimes needs explicitly
    constructor() : this("", "", "", "", null, null, null)
}