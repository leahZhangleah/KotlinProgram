package com.leahhumlelu.kotlinprogram.devbyte

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DevByteViewModelFactory(val application:Application):ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DevByteOverviewViewModel::class.java)){
            return DevByteOverviewViewModel(application) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}