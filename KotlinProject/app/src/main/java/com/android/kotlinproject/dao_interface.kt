package com.android.kotlinproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface dao_interface
{
    @Insert
    fun insertStudent(myStudent : Student)

    @Query( "SELECT * FROM Student")
    fun getAllStudents() : LiveData<List<Student>>

    @Insert
    fun insertExpense(my_expense : Expense)

    @Query( "SELECT * FROM expense")
    fun getExpenses() : LiveData<List<Expense>>
}