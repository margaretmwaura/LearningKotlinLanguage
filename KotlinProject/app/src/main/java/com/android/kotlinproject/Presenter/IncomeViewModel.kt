package com.android.kotlinproject.Presenter

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.kotlinproject.Model.Income
import com.android.kotlinproject.Presenter.dao_interface
import kotlinx.coroutines.*

class IncomeViewModel (val database : dao_interface, application: Application) : AndroidViewModel(application)
{


    //    This is needed by the couroutines
    public var viewModelJob = Job()
    public var myIncome = MutableLiveData<Income?>()
    public var incomeList = database.getIncome()

    //    This is the block to be executed first
    init {
        Log.i("IncomeViewModel","ViewModel created");
    }

    //The scope determines what thread the couroutine will run on
//    The Dispatcher.Main means the main thread
//    It is running on the main thread cause we need the updating of the UI after the processing
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun onCreateNewIncome( myNewIncome : Income)
    {
        uiScope.launch {

            insertIncome(myNewIncome)

        }

    }

    private suspend fun insertIncome(myNewIncome: Income)
    {
        withContext(Dispatchers.IO)
        {
            database.insertIncome(myNewIncome)
            Log.i("add","Student has been added")
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}