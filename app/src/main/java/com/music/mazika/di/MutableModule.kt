package com.music.mazika.di

import androidx.lifecycle.MutableLiveData
import com.music.mazika.model.SongModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
class MutableModule {


    @Provides
    @ViewModelScoped
    fun provideBoolean(): MutableLiveData<Boolean> {
        return MutableLiveData()
    }


    @Provides
    @ViewModelScoped
    fun provideString(): MutableLiveData<String> {
        return MutableLiveData()
    }

    @Provides
    @ViewModelScoped
    fun provideListSongsModel(): MutableLiveData<ArrayList<SongModel>> {
        return MutableLiveData()
    }

    @Provides
    @ViewModelScoped
    fun provideSongsModel(): ArrayList<SongModel> {
        return ArrayList()
    }


}