package com.android.kotlinproject.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Income")
data class Income
    (

    @PrimaryKey(autoGenerate = true)
    var Id : Long = 0L,

    @ColumnInfo(name = "IncomeName")
    var name : String = " ",

    @ColumnInfo(name = "IncomeAmount")
    var amount : Int = 0,

    @ColumnInfo(name = "IncomeDate")
    var incomeDate : String = " "



)