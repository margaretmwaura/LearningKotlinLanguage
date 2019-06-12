package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScoreGiven : Int): ViewModel()
{
    private val _finalscore = MutableLiveData<Int>()
    val score : LiveData<Int> get() = _finalscore
    init {

        _finalscore.value = finalScoreGiven
    }
}