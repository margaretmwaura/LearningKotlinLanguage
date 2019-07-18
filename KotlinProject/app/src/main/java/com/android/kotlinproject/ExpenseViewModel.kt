package com.android.kotlinproject

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ExpenseViewModel (val database : dao_interface , application: Application) : AndroidViewModel(application)
{


    //    This is needed by the couroutines
    public var viewModelJob = Job()
    public var myExpense = MutableLiveData<Expense?>()
    public var expenseList = database.getExpenses()

    //    This is the block to be executed first
    init {
        Log.i("ExpenseModel","ViewModel created");
    }

    //The scope determines what thread the couroutine will run on
//    The Dispatcher.Main means the main thread
//    It is running on the main thread cause we need the updating of the UI after the processing
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun onCreateNewExpense( mynewExpense : Expense)
    {
        uiScope.launch {

            insert(mynewExpense)

        }

    }

    private suspend fun insert(myExpense: Expense )
    {
        withContext(Dispatchers.IO)
        {
            database.insertExpense(myExpense)
            Log.i("add","Student has been added")
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}