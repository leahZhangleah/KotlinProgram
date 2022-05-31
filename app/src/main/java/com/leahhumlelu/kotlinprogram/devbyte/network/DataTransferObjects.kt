package com.leahhumlelu.kotlinprogram.devbyte.network

import android.provider.ContactsContract
import com.leahhumlelu.kotlinprogram.devbyte.database.DatabaseVideo
import com.leahhumlelu.kotlinprogram.devbyte.domain.Video
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos:List<NetworkVideo>)

fun NetworkVideoContainer.asDomainModel():List<Video>{
    return videos.map {
        Video(
            title = it.title,
            description = it.description,
            videoUrl = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}

fun NetworkVideoContainer.asDatabaseModel():Array<DatabaseVideo>{
    return videos.map {
        DatabaseVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }.toTypedArray()
}

@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title:String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?
    )