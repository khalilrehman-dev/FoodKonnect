package com.khalil.foodkonnect.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.khalil.foodkonnect.data.repository.AuthRepositoryImpl
import com.khalil.foodkonnect.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Provides Firebase instances (FirebaseAuth, FirebaseFirestore) so Hilt can inject
// them anywhere they're needed (like into FirebaseAuthSource / UserFirestoreSource).
@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
}

// Binds the AuthRepository interface to its real implementation.
// Whenever something asks Hilt for an AuthRepository, it hands over an AuthRepositoryImpl.
@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository
}