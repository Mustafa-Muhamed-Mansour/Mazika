package com.music.mazika.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val boolean: MutableLiveData<Boolean>
) : ViewModel() {

    fun delayOfTime(): LiveData<Boolean> {
        viewModelScope.launch {
            delay(3000)
            boolean.postValue(true)
        }
        return boolean
    }

}