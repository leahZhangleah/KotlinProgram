package com.leahhumlelu.kotlinprogram.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizFinishViewModel(private val finalScore:Int):ViewModel() {
    val score:LiveData<Int>
        get() = _score
    private val _score = MutableLiveData<Int>()
    init {
        _score.value = finalScore
    }
}