package com.android.kotlinproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Student")
 data class Student(

    @PrimaryKey(autoGenerate = true)
    var Id : Long = 0L,

    @ColumnInfo(name = "StudentName")
    var name : String = " ",

    @ColumnInfo(name = "StudentMarks")
    var marks : Int = 0
 )