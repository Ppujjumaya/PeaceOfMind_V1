// data/di/AppModule.kt
package com.pjmy.project.peaceofmind.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pjmy.project.peaceofmind.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideImageRepository(firestore: FirebaseFirestore): ImageRepository {
        return ImageRepositoryImpl(firestore)
    }

    // TODO: 나중에 실제 구현체로 바꿔야 함
    @Provides
    @Singleton
    fun provideUserProgressRepository(): UserProgressRepository {
        return UserProgressRepositoryImpl()
    }

    // TODO: 나중에 실제 구현체로 바꿔야 함
    @Provides
    @Singleton
    fun provideCommunityRepository(): CommunityRepository {
        return CommunityRepositoryImpl()
    }
}