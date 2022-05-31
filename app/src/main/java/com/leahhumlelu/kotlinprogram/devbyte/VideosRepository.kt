package com.leahhumlelu.kotlinprogram.devbyte

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.leahhumlelu.kotlinprogram.devbyte.database.VideoDatabase
import com.leahhumlelu.kotlinprogram.devbyte.database.asDomainModel
import com.leahhumlelu.kotlinprogram.devbyte.domain.Video
//import com.leahhumlelu.kotlinprogram.devbyte.network.NetworkVideoContainer
import com.leahhumlelu.kotlinprogram.devbyte.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository(private val database: VideoDatabase) {
    val videos:LiveData<List<Video>> = Transformations.map(database.videoDao.getVideos()){
        it.asDomainModel()
    }
    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
            val networkVideos = DevByteApi.service.getDevByteVideos()
            database.videoDao.insertVideos(*networkVideos.asDatabaseModel())
        }
    }
}