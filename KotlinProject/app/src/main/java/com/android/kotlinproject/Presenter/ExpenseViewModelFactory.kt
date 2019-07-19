package com.android.kotlinproject.Presenter

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ExpenseViewModelFactory  (private val dataSource: dao_interface,
                                private val application: Application ) : ViewModelProvider.Factory
{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)) {
            return ExpenseViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}