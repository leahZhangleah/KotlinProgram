package com.leahhumlelu.kotlinprogram.quiz

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)

class QuizViewModel: ViewModel() {

    enum class BuzzType(val pattern:LongArray){
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }

    private val _word =MutableLiveData<String?>()
    val word:LiveData<String?>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score:LiveData<Int>
        get() = _score

    private val _gameFinished = MutableLiveData<Boolean>()
    val gameFinished:LiveData<Boolean>
        get() = _gameFinished
    private lateinit var wordList:MutableList<String>

    private val timer:CountDownTimer
    private val _currentTime = MutableLiveData<Long>()
    private val currentTime:LiveData<Long>
        get() = _currentTime
    val currentTimeString = Transformations.map(currentTime){time->
        DateUtils.formatElapsedTime(time)
    }

    private val _eventBuzz = MutableLiveData<BuzzType>()
    val eventBuzz:LiveData<BuzzType>
        get() = _eventBuzz
    companion object{
        const val DONE = 0L
        const val COUNTDOWN_PANIC_SECONDS = 10L
        const val ONE_SECOND = 1000L
        const val COUNTDOWN_TIME = 60000L
    }
    init {
        Log.i("QuizViewModel","QuizViewModel created")
        _score.value = 0
        _gameFinished.value=false
        timer = object:CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value=millisUntilFinished / ONE_SECOND
                if(millisUntilFinished / ONE_SECOND <= COUNTDOWN_PANIC_SECONDS){
                    _eventBuzz.value = BuzzType.COUNTDOWN_PANIC
                }
            }

            override fun onFinish() {
                _currentTime.value= DONE
                _gameFinished.value = true
                _eventBuzz.value = BuzzType.GAME_OVER
            }

        }
        resetList()
        nextWord()
        timer.start()
    }

    private fun nextWord() {
        if(wordList.isEmpty()){
            resetList()
        }else{
            _word.value = wordList.removeAt(0)
        }
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("QuizViewModel","QuizViewModel cleared")
        timer.cancel()
    }


    fun onSkip(){
        _score.value = score.value?.minus(1)
        nextWord()
    }

    fun onCorrect(){
        _score.value=score.value?.plus(1)
        _eventBuzz.value = BuzzType.CORRECT
        nextWord()
    }

    fun onGameFinishComplete(){
        _gameFinished.value=false
    }

    fun onBuzzComplete(){
        _eventBuzz.value= BuzzType.NO_BUZZ
    }
}