package com.leahhumlelu.kotlinprogram.sleep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SleepQualityViewModel(private val dataSource:SleepDatabaseDao, private val sleepNightKey:Long) : ViewModel() {
    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()

    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    fun doneNavigating(){
        _navigateToSleepTracker.value=null
    }

    fun onSetSleepQuality(quality:Int){
        viewModelScope.launch {
            val tonight = dataSource.get(sleepNightKey)?:return@launch
            tonight.sleepQuality = quality
            dataSource.update(tonight)
            _navigateToSleepTracker.value=true
        }
    }
}