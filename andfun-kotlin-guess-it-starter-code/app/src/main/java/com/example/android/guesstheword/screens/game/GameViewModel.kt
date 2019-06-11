package com.example.android.guesstheword.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel()
{    // The current word
     private val _word = MutableLiveData<String>()
    val word : LiveData<String> get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int> get() = _score

    private val _gamefinished = MutableLiveData<Boolean>()
    val gamefinished : LiveData<Boolean> get() = _gamefinished
    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>
    init {

        _gamefinished.value = false
        resetList()
        nextWord()
        _score.value = 0
        _word.value = " "
    }

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
//            gameFinished()
            _gamefinished.value = true
        } else {
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