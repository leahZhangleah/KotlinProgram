package com.leahhumlelu.kotlinprogram

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.leahhumlelu.kotlinprogram.sleep.SleepDatabase
import com.leahhumlelu.kotlinprogram.sleep.SleepDatabaseDao
import com.leahhumlelu.kotlinprogram.sleep.SleepNight
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SleepDatabaseTest {
    private lateinit var sleepDatabaseDao: SleepDatabaseDao
    private lateinit var db:SleepDatabase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        //db = SleepDatabase.getInstance(context)
        db = Room.inMemoryDatabaseBuilder(context,SleepDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        sleepDatabaseDao =db.sleepDatabaseDao

    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertAndGetNight() = runBlocking {
        val night = SleepNight()
        sleepDatabaseDao.insert(night)
        val tonight = sleepDatabaseDao.getTonight()
        assertEquals(tonight?.sleepQuality,-1)
    }

    @Test
    @Throws(IOException::class)
    fun updateAndGetNight()= runBlocking {
        val night = SleepNight()
        sleepDatabaseDao.insert(night)
        night.sleepQuality = 0
        sleepDatabaseDao.update(night)
        assertEquals(sleepDatabaseDao.getTonight()!!.sleepQuality,0)
    }


}