package com.music.mazika.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class FirebaseModule {

    @ViewModelScoped
    @Provides
    fun provideRealTime(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }
}