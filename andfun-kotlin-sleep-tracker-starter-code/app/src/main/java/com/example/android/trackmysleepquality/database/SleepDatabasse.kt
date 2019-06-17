package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.sql.Types.NULL

@Database(entities = [SleepNight :: class] , version = 1, exportSchema = false)
abstract class SleepDatabasse : RoomDatabase()
{

    abstract val sleepDatabaseDao : SleepDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE : SleepDatabasse ?= null

        fun getInstance(context : Context) : SleepDatabasse{
            synchronized(this )
            {
                var instance = INSTANCE

                if(instance == null)
                {
                  instance = Room.databaseBuilder(
                      context.applicationContext,
                      SleepDatabasse :: class.java,
                      "Sleep histroy database"
                      )
                          .fallbackToDestructiveMigration()
                          .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}