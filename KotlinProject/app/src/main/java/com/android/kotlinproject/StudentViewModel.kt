package com.android.kotlinproject

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class StudentViewModel(val database : dao_interface, application: Application ) : AndroidViewModel(application)
{


//    This is needed by the couroutines
    public var viewModelJob = Job()
    public var myStudent = MutableLiveData<Student?>()
    public var studentList = database.getAllStudents()

//    This is the block to be executed first
  init {
      Log.i("GameViewModel","ViewModel created");
  }

//The scope determines what thread the couroutine will run on
//    The Dispatcher.Main means the main thread
//    It is running on the main thread cause we need the updating of the UI after the processing
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun onCreateNewStudent( mynewStudent : Student)
    {
        uiScope.launch {

            insert(mynewStudent)

        }

    }

    private suspend fun insert(myStudent: Student )
    {
        withContext(Dispatchers.IO)
        {
            database.insertStudent(myStudent)
            Log.i("add","Student has been added")
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}