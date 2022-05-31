package com.leahhumlelu.kotlinprogram.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuizFinishViewModelFactory(private val finalScore:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(QuizFinishViewModel::class.java)){
            return QuizFinishViewModel(finalScore) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}