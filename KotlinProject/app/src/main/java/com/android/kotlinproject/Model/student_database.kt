package com.android.kotlinproject.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.kotlinproject.Presenter.dao_interface

@Database(entities = [Student:: class , Expense:: class , Income::class] , version = 1 , exportSchema = false)
abstract class student_database: RoomDatabase()
{

    abstract val dao_interface_interface : dao_interface
    companion object {

        @Volatile
        private var INSTANCE  : student_database? = null

        fun getInstance(context: Context) : student_database
        {
            synchronized(this)
            {
                var instance = INSTANCE

                if(instance == null)
                {

                    instance = Room.databaseBuilder(

                        context.applicationContext,
                        student_database:: class.java,
                        "student database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return instance
            }
        }
    }
}