package com.android.kotlinproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "expense")
data class Expense
    (

    @PrimaryKey(autoGenerate = true)
    var Id : Long = 0L,

    @ColumnInfo(name = "ExpenseName")
    var name : String = " ",

    @ColumnInfo(name = "ExpenseAmount")
    var amount : Int = 0,

    @ColumnInfo(name = "ExpenseDate")
    var expenseDate : String = " ",

    @ColumnInfo(name = "ExpenseDetail")
    var details : String = " "

)