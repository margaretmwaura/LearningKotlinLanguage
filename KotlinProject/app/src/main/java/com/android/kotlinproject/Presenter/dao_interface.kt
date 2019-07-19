package com.android.kotlinproject.Presenter

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.kotlinproject.Model.Expense
import com.android.kotlinproject.Model.Income
import com.android.kotlinproject.Model.Student

@Dao
interface dao_interface
{
    @Insert
    fun insertStudent(myStudent : Student)

    @Query( "SELECT * FROM Student")
    fun getAllStudents() : LiveData<List<Student>>

    @Insert
    fun insertExpense(my_expense : Expense)

    @Query( "SELECT * FROM Expense")
    fun getExpenses() : LiveData<List<Expense>>

    @Insert
    fun insertIncome(my_income : Income)

    @Query( "SELECT * FROM Income")
    fun getIncome() : LiveData<List<Income>>
}