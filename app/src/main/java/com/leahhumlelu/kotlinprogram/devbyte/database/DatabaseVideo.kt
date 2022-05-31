package com.leahhumlelu.kotlinprogram.devbyte.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leahhumlelu.kotlinprogram.devbyte.domain.Video

@Entity
data class DatabaseVideo constructor(
    @PrimaryKey
    val url:String,
    val updated:String,
    val title:String,
    val description:String,
    val thumbnail:String
)

fun List<DatabaseVideo>.asDomainModel():List<Video>{
    return map {
        Video(
            videoUrl = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}
