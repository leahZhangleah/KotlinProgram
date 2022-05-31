package com.leahhumlelu.kotlinprogram.devbyte.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VideoDao{

    @Query("select * from databasevideo")
    fun getVideos():LiveData<List<DatabaseVideo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideos(vararg video: DatabaseVideo)

}

@Database(entities = [DatabaseVideo::class],exportSchema = false,version = 1)
abstract class VideoDatabase():RoomDatabase(){
    abstract val videoDao:VideoDao
}

private lateinit var INSTANCE: VideoDatabase

fun getDatabaseInstance(context: Context):VideoDatabase{
    if (!::INSTANCE.isInitialized){
        INSTANCE = Room.databaseBuilder(context,VideoDatabase::class.java,"videos").build()
    }
    return INSTANCE
}