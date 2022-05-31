package com.leahhumlelu.kotlinprogram.devbyte

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leahhumlelu.kotlinprogram.devbyte.database.VideoDatabase
import com.leahhumlelu.kotlinprogram.devbyte.database.getDatabaseInstance
import kotlinx.coroutines.launch

class DevByteOverviewViewModel(application: Application) : ViewModel() {
    private val database = getDatabaseInstance(application)
    private val repository = VideosRepository(database)
    val playlist = repository.videos
    init {
        viewModelScope.launch {
            repository.refreshVideos()
        }
    }

}