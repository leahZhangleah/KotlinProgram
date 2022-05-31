package com.leahhumlelu.kotlinprogram.quiz

import android.os.Looper
import android.util.Log
import androidx.lifecycle.*

class PlayTimer(lifecycle:Lifecycle):LifecycleEventObserver {
    var secondsCount = 0
    private var handler = android.os.Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    init {
        lifecycle.addObserver(this)
    }

    private fun startTimer(){
        runnable = Runnable {
            secondsCount++
            Log.i("PlayTimer","Timer is at: $secondsCount")
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)
    }
    private fun stopTimer(){
        handler.removeCallbacks(runnable)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_START->startTimer()
            Lifecycle.Event.ON_STOP->stopTimer()
            //else->Log.i("PlayTimer","No monitor required")
        }
    }
}