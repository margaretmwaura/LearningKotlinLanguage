package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel()
{    // The current word

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000L
    }

    private val _word = MutableLiveData<String>()
    val word : LiveData<String> get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int> get() = _score

    private val _gamefinished = MutableLiveData<Boolean>()
    val gamefinished : LiveData<Boolean> get() = _gamefinished
    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>

    private val timer : CountDownTimer

    private val _time = MutableLiveData<Long>()
    val time : LiveData<Long> get() = _time


    init {

        _gamefinished.value = false
        resetList()
        nextWord()
        _score.value = 0


        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long)
            {
                // TODO implement what should happen each tick of the timer
                _time.value = (millisUntilFinished/ ONE_SECOND)
            }

            override fun onFinish() {

                _time.value = DONE
                _gamefinished.value = true
                // TODO implement what should happen when the timer finishes
            }
        }

        timer.start()
    }

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
//            gameFinished()
            _gamefinished.value = true
        } else {
            _word.value = wordList.removeAt(0)
            resetList()
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

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }
    fun gamefinishComplete()
    {
        _gamefinished.value = false
    }
}